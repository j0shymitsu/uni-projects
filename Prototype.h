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
        explicit Prototype(const std::string& file_name);
        virtual ~Prototype() = default;
        virtual std::string getCity(char start_letter);
        bool checkCity(const std::string& city);            // Checks if valid
        void markUsed(const std::string& city);
        void restart();
        void seed(int value);
    protected:
        // Removed Clang warnings for these as they are intentional behaviour and flagging false positive
        std::vector<std::string> all_cities;            // NOLINT
        std::vector<std::string> used_cities;           // NOLINT
        std::mt19937 rng;                               // NOLINT
};