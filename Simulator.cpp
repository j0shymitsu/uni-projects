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
    // New approach: Store where current batch starts and make space for k new results
    // Removed Clang warning from this index as it was showing false posititve
    size_t starting_index = all_results.size();     // NOLINT(clang-analyzer-deadcode.DeadStores)
    all_results.resize(all_results.size() + k);

    mt19937 rng(seed);
    uniform_int_distribution<int> letters('a', 'z');
    vector<thread> threads;

    // Generate start letters first for reproducibility (still 'not reproducible'??)
    vector<char> start_letters(k);
    for (size_t i = 0; i < k; i++)
    {
        start_letters[i] = static_cast<char>(letters(rng));
    }

    // Timer start
    auto start = std::chrono::high_resolution_clock::now();

    for (size_t i = 0; i < k; i++)
    {
        // Implements multithreading
        threads.push_back(thread([this, file_name, start_letters, seed, i, starting_index]()
        {
            auto result = run(file_name, start_letters[i], seed + static_cast<int>(i));
            unique_lock<mutex> lock(all_results_mutex);
            all_results[starting_index + i] = result;       // Now writes to correct position
        }));
    }

    // Join threads together
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

