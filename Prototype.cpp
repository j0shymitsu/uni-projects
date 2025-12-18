#include "Prototype.h"
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <cstddef>      // Header for size_t
#include <random>
using namespace std;

// Init
// Removed Clang warnings from rng on constructor as this is intentional behaviour
// NOLINTNEXTLINE
Prototype::Prototype(const std::string& file_name) : rng(0)
{
    ifstream reader(file_name);

    string city;
    all_cities.clear();
    used_cities.clear();

    if (!reader)
    {
        cout << "Error opening file." << '\n';
        return;
    }

    // for (int i = 0; !reader.eof(); i++)
    // {
    //     getline(reader, city);
    //     all_cities.push_back(city);
    // }

    // Switching to while loop as index not used in previous loop
    while (getline(reader, city))
    {
        if (!city.empty())
        {
            all_cities.push_back(city);
        }
    }

    reader.close();
}

// Return string containing capital city beginning with given character
string Prototype::getCity(char start_letter)
{
    vector<string> valid_cities = {};

    // Populate valid cities by starting letter
    for (size_t i = 0; i < all_cities.size(); i++)
    {
        string current_city = all_cities[i];
        char current_char = current_city[0];

        if (current_char == start_letter)
        {
            valid_cities.push_back(current_city);
        }
    }

    // Remove used cities from list
    for (size_t i = 0; i < valid_cities.size(); i++)
    {
        for (size_t j = 0; j < used_cities.size(); j++)
        {
            if (valid_cities[i] == used_cities[j])
            {
                valid_cities.erase(valid_cities.begin() + static_cast<int>(i));
                --i;
                break;
            }
        }
    }

    if (valid_cities.empty())
    {
        return "";
    }

    // Return random city from valid cities: Updated using new seed method and uniform distribution
    uniform_int_distribution<int> dist(0, static_cast<int>(valid_cities.size()) - 1);
    int index = dist(rng);

    return valid_cities[index];
}

// Check if given city is valid and unused
bool Prototype::checkCity(const std::string& city)
{
    for (size_t i = 0; i < used_cities.size(); i++)
    {
        if (city == used_cities[i])
        {
            return false;
        }
    }

    for (size_t i = 0; i < all_cities.size(); i++)
    {
        if (city == all_cities[i])
        {
            return true;
        }
    }

    return false;
}

// Mark given city as used
void Prototype::markUsed(const std::string& city)
{
    if (checkCity(city))
    {
        used_cities.push_back(city);
    }
}

// Mark all cities unused
void Prototype::restart()
{
    used_cities = vector<string>();
}

// Seeding method
void Prototype::seed(int value)
{
    rng.seed(value);
}


