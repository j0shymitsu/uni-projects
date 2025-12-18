#include "Competition.h"
#include <cstddef>
#include <string>
#include <vector>
using namespace std;

int Competition::countRemainingCities(char letter)
{
    int count = 0;
    const auto& cities = getAllCities();

    for (size_t i = 0; i < cities.size(); ++i)
    {
        if (cities[i][0] == letter && checkCity(cities[i]))
        {
            count++;
        }
    }

    return count;
}

string Competition::getCity(char start_letter)
{
    // Get all valid cities with the required letter
    vector<string> valid_cities;
    const auto& cities = getAllCities();

    for (size_t i = 0; i < cities.size(); i++)
    {
        if (cities[i][0] == start_letter && checkCity(cities[i]))
        {
            valid_cities.push_back(cities[i]);
        }
    }

    if (valid_cities.empty())
    {
        return "";
    }

    // Find the city whose last letter gives the least options
    string best_city = valid_cities.front();
    int options = countRemainingCities(valid_cities[0].back());

    for (size_t i = 1; i < valid_cities.size(); i++)
    {
        char last_letter = valid_cities[i].back();
        int remaining_options = countRemainingCities(last_letter);

        if (remaining_options < options)
        {
            options = remaining_options;
            best_city = valid_cities[i];
        }
    }

    return best_city;
}

