#include <iostream>
#include <string>
#include <stdio.h>
#include <time.h>

// Get current date/time, format is YYYY-MM-DD.HH:mm:ss
const std::string currentDateTime() {
    time_t     now = time(0);
    struct tm  tstruct;
    char       buf[80];
    tstruct = *localtime(&now);
    // Visit http://en.cppreference.com/w/cpp/chrono/c/strftime
    // for more information about date/time format
    strftime(str, sizeof(buf), "%Y%m%d, %H%M%S", &tstruct);

    return buf;
}

int main() {
    std::cout << "currentDateTime()=" << currentDateTime() << std::endl;
}
