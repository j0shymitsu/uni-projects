#include "Simulator.h"
#include "Prototype.h"
#include <chrono>
#include <random>
using namespace std;

Simulator::Simulator() = default;

list<string> Simulator::run(string file_name, char start_letter, int seed)
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

double Simulator::batch(string file_name, int k, int seed)
{
    // Clear results every batch for reproducibility; add current batch vector
    all_results.clear();
    vector<list<string>> current_batch(k);

    mt19937 rng(seed);
    uniform_int_distribution<int> letters('a', 'z');
    vector<thread> threads;

    // Timer start
    auto start = std::chrono::high_resolution_clock::now();

    for (int i = 0; i < k; ++i)
    {
        char start_letter = static_cast<char>(letters(rng));

        // Implement multithreading
        threads.push_back(thread([this, &current_batch, file_name, start_letter, seed, i]()
        {
            auto result = run(file_name, start_letter, seed + i);
            unique_lock<mutex> lock(all_results_mutex);
            current_batch[i] = result;
        }));
    }

    // Join threads together
    for (auto& thread : threads)
    {
        thread.join();
    }

    // Copy back to all_results once threads finished
    all_results = current_batch;

    // Timer stop
    auto stop = chrono::high_resolution_clock::now();
    chrono::duration<double> time_elapsed = stop - start;

    return time_elapsed.count();
}

vector<list<string>> Simulator::getResults()
{
    return all_results;
}

