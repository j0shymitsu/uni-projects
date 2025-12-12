#pragma once
#include "Prototype.h"

#define Competition_class Prototype 		//change to Competition once you've implemented your Competition class, defaults to Prototype

class Competition : public Prototype
{
    public:
        Competition() : Prototype("Cities.txt") {};
        // std::string getCity(char start_letter) override { return Prototype::getCity(start_letter); };
};