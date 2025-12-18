#include "Simulator.h"
#include "Prototype.h"
#include <chrono>
#include <random>
#include <list>
#include <string>
#include <vector>
#include <thread>
#include <cstddef>
#include <mutex>
using namespace std;

Simulator::Simulator() = default;

list<string> Simulator::run(const string& file_name, char start_letter, int seed)
{
    Prototype prototype(file_name);
    prototype.seed(seed);

    list<string> all_cities;
    char current_letter = start_letter;

    while (true)
    {
        string city = prototype.getCity(current_letter);

        if (city.empty())
        {
            break;
        }

        prototype.markUsed(city);
        all_cities.push_back(city);
        current_letter = city.back();
    }

    return all_cities;
}

double Simulator::batch(const string& file_name, int k, int seed)
{
    // Store where current batch starts and make space for k new results
    size_t starting_index = all_results.size();
    all_results.resize(all_results.size() + k);

    // Moved start letters and seeds gen outside loop for reproducibility across batches (still says no reproducibility??)
    mt19937 rng(seed);
    uniform_int_distribution<int> letters('a', 'z');
    vector<char> start_letters(k);
    for (int i = 0; i < k; i++)
    {
        start_letters[i] = static_cast<char>(letters(rng));
    }

    // Timer start
    auto start = chrono::high_resolution_clock::now();

    // Implement multithreading
    vector<thread> threads;
    for (int i = 0; i < k; i++)
    {
        threads.push_back(thread([this, file_name, starting_index, i, start_letter = start_letters[i], sim_seed = seed + i]()
        {
            auto result = run(file_name, start_letter, sim_seed);
            lock_guard<mutex> lock(all_results_mutex);
            all_results[starting_index + i] = result;
        }));
    }

    // Wait to complete then join
    for (auto& thread : threads)
    {
        thread.join();
    }

    // Timer stop
    auto stop = chrono::high_resolution_clock::now();
    chrono::duration<double> time_elapsed = stop - start;

    return time_elapsed.count();
}

vector<list<string>> Simulator::getResults()
{
    return all_results;
}

