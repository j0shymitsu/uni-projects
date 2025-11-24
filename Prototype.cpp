#include "Prototype.h"
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
using namespace std;

// Init
Prototype::Prototype(std::string& file_name)
{
    ifstream reader("file_name");

    string city;
    all_cities = vector<string>();
    used_cities = vector<string>();

    if (!reader)
    {
        cout << "Error opening file." << endl;
    }

    for (int i = 0; !reader.eof(); i++)
    {
        getline(reader, city);
        all_cities.push_back(city);
    }

    reader.close();
}

// Return string containing capital city beginning with given character
// UNFINISHED
string Prototype::getCity(char& start_letter)
{
    for (int i = 0; i < all_cities.size(); i++)
    {
        return all_cities[i];
    }

    return "";
}

// Check if given city is valid and unused
bool Prototype::checkCity(std::string& city)
{
    for (int i = 0; i < all_cities.size(); i++)
    {
        if (city == all_cities[i])
        {
            return false;
        }
    }

    return true;
}

// Mark given city as used
void Prototype::markUsed(std::string& city)
{
    if (!checkCity(city))
    {
        used_cities.push_back(city);
    }
}

// Mark all cities unused
void Prototype::restart()
{
    used_cities.empty();
}
