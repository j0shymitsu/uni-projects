#include "Simulator.h"
#include "Prototype.h"
#include <chrono>
#include <random>
using namespace std;

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

    all_results.push_back(all_cities);
    return all_cities;
}

double Simulator::batch(string filename, int k, int seed)
{
    double batch_time = 0.0;

    // Timer start
    auto start = chrono::high_resolution_clock::now();

    for (int i = 1; i <= k; i++)
    {
        run(filename, 'a', seed);
    }

    // Timer stop
    auto stop = chrono::high_resolution_clock::now();
    auto duration = chrono::duration_cast<chrono::milliseconds>(stop - start);
    batch_time += duration.count();

    return batch_time;
}

vector<list<string>> Simulator::getResults()
{
    vector<list<string>> output;
    output.reserve(all_results.size());

    for (auto result : output)
    {
        output.emplace_back(result.begin(), result.end());
    }

    return output;
}

