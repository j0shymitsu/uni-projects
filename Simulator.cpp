#include "Simulator.h"
#include "Prototype.h"
#include <chrono>
#include <random>
using namespace std;

Simulator::Simulator()
    : rng(0), letters('a', 'z') {}

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

    all_results.push_back(all_cities);

    return all_cities;
}

double Simulator::batch(string file_name, int k, int seed)
{
    all_results.clear();
    rng.seed(seed);
    letters.param(decltype(letters)::param_type('a', 'z'));

    // Timer start
    auto start = std::chrono::high_resolution_clock::now();

    for (int i = 1; i <= k; i++)
    {
        char start_letter = letters(rng);
        run(file_name, start_letter, seed + i);
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

