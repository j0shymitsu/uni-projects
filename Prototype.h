#pragma once
#include <string>
#include <vector>
#include <random>
#define ASSIGNMENT_2		//Uncomment to switch to feedback mode for assignment 2

/*
    O notation: fill in your time complexities here
        -	Constructor:    O(n)
        -	getCity:		O(n^2) (worst case)
        -	checkCity:      O(n)
        -	markUsed		O(n)
        -	restart			O(n)
*/

class Prototype
{
    public:
        explicit Prototype(std::string file_name);
        std::string getCity(char start_letter);
        bool checkCity(std::string city);          // Checks if valid
        void markUsed(std::string city);
        void restart();
        void seed(int value);
    private:
        std::vector<std::string> all_cities;
        std::vector<std::string> used_cities;
        std::mt19937 rng;
};