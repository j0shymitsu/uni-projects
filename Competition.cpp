#include "Competition.h"
#include <algorithm>
using namespace std;

int Competition::countRemainingCities(char letter)
{
    int count = 0;

    for (size_t i = 0; i < all_cities.size(); ++i)
    {
        if (all_cities[i][0] == letter && checkCity(all_cities[i]))
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

    for (size_t i = 0; i < all_cities.size(); i++)
    {
        if (all_cities[i][0] == start_letter && checkCity(all_cities[i]))
        {
            valid_cities.push_back(all_cities[i]);
        }
    }

    if (valid_cities.size() == 0)
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

