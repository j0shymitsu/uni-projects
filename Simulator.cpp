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
    mt19937 rng(seed);
    uniform_int_distribution<int> letters('a', 'z');

    // Timer start
    auto start = std::chrono::high_resolution_clock::now();

    for (int i = 0; i < k; ++i)
    {
        char start_letter = static_cast<char>(letters(rng));
        all_results.push_back(run(file_name, start_letter, seed + i));
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

