#include "Simulator.h"
#include "Prototype.h"
#include <chrono>
#include <random>
using namespace std;

// Fully qualified method to resolve compatible declaration error; still not working!
vector<string> Simulator::run(string file_name, char start_letter, int seed)
{
    Prototype prototype(file_name);
    prototype.seed(seed);

    vector<string> all_cities;
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
        current_letter = city.back();   // Make sure next word starts with last letter from previous
    }

    all_results.push_back(list<string>(all_cities.begin(), all_cities.end()));

    return all_cities;
}

double Simulator::batch(string file_name, int k, int seed)
{
    double batch_time = 0.0;
    mt19937 rng(seed);
    uniform_int_distribution<char> letters('a', 'z');

    // Timer start
    auto start = chrono::high_resolution_clock::now();

    for (int i = 1; i <= k; i++)
    {
        char start_letter = letters(rng);
        int current_seed = seed + i;
        run(file_name, start_letter, current_seed);
    }

    // Timer stop
    auto stop = chrono::high_resolution_clock::now();
    auto duration = chrono::duration_cast<chrono::milliseconds>(stop - start);
    batch_time += duration.count();

    return batch_time;
}

vector<list<string>> Simulator::getResults()
{
    return all_results;
}

