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
    Prototype::all_cities = vector<string>();

    if (!reader)
    {

    }



}

// Return string containing capital city beginning with given character
string Prototype::getCity(std::string& start_letter)
{

}

// Check if given city is valid and unused
bool Prototype::checkCity(std::string& city)
{

}

// Mark given city as used
void Prototype::markUsed(std::string& city)
{

}

// Mark all cities unused
void Prototype::restart()
{

}
