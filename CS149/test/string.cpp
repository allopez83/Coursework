#include <iostream>
#include <string.h>

using namespace std;

void strcompare(char* data) {
    if (strlen(data) ==     0)
        cout << "empty\n";
    else
        cout << "not empty\n";

    cout << strlen(data);
}

int main () {
    cout << "Start\n";

    char *text = (char*)"a";

    strcompare(text);

    cout << "End\n";
    return 0;
}
