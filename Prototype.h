#pragma once
#include <string>
#include <vector>
#include <array>
//#define ASSIGNMENT_2		//Uncomment to switch to feedback mode for assignment 2

/*
	O notation: fill in your time complexities here
		-	Constructor:	_____
		-	getCity:		_____
		-	checkCity:		_____
		-	markUsed		_____
		-	restart			_____
*/

class Prototype
{
    public:
        Prototype(std::string& file_name);
        std::string getCity(char& start_letter);
        bool checkCity(std::string& city);          // Checks if valid
        void markUsed(std::string& city);
        static void restart();
    private:
        std::vector<std::string> all_cities;
        std::vector<std::string> used_cities;
};