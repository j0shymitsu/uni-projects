#pragma once
#include "Prototype.h"
#include <string>
#include <vector>
#include <list>

class Simulator
{
    public:
        explicit Simulator();
        std::vector<std::string> run(std::string file_name, char start_letter, int seed);   // Return words used in sim game
        double batch(std::string file_name, int k, int seed);   // Run k sims, return time taken in s
        std::vector<std::list<std::string>> getResults();
    private:
        std::vector<std::list<std::string>> all_results;
        std::mt19937 rng;
        std::uniform_int_distribution<> letters;
};