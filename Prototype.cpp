#include "Prototype.h"
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <cstdlib>
using namespace std;

// Init
Prototype::Prototype(std::string file_name)
{
    ifstream reader(file_name);

    string city;
    all_cities = vector<string>();
    used_cities = vector<string>();

    if (!reader)
    {
        cout << "Error opening file." << '\n';
    }

    for (int i = 0; !reader.eof(); i++)
    {
        getline(reader, city);
        all_cities.push_back(city);
    }

    reader.close();
}

// Return string containing capital city beginning with given character
string Prototype::getCity(char start_letter)
{
    vector<string> valid_cities = {};

    // Populate valid cities by starting letter
    for (int i = 0; i < all_cities.size(); i++)
    {
        string current_city = all_cities[i];
        char current_char = current_city[0];

        if (current_char == start_letter)
        {
            valid_cities.push_back(current_city);
        }
    }

    // Remove used cities from list
    for (int i = 0; i < valid_cities.size(); i++)
    {
        for (int j = 0; j < used_cities.size(); j++)
        {
            if (valid_cities[i] == used_cities[j])
            {
                valid_cities.erase(valid_cities.begin() + i);
                --i;
                break;
            }
        }
    }

    if (valid_cities.empty())
    {
        return "";
    }

    // Return random city from valid cities
    int random_index = rand() % valid_cities.size();
    return valid_cities[random_index];
}

// Check if given city is valid and unused
bool Prototype::checkCity(std::string city)
{
    for (int i = 0; i < used_cities.size(); i++)
    {
        if (city == used_cities[i])
        {
            return false;
        }
    }

    for (int i = 0; i < all_cities.size(); i++)
    {
        if (city == all_cities[i])
        {
            return true;
        }
    }

    return false;
}

// Mark given city as used
void Prototype::markUsed(std::string city)
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
