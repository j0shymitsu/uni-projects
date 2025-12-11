#include "Simulator.h"
#include "Prototype.h"
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

    return all_cities;
}

// TODO
double Simulator::batch(string filename, int k, int seed)
{
    return 0.0;
}

vector<list<string>> Simulator::getResults()
{
    return all_results;
}

