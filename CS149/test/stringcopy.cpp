#include <iostream>
#include <string.h>

using namespace std;

struct somestruct {
    char * someChar;
}; somestruct structarr[5];

void strempty(char* data) {
    if (strlen(data) == 0)
        cout << "empty\n";
    else
        cout << "not empty\n";

    cout << strlen(data);
}

int main () {
    cout << "Start\n";

    char *text;

    text = (char*)"a";
    printf("%s\n", text);

    text = "a";
    printf("%s\n", text);

    char *other = "abc";
    text = other;
    printf("%s\n", text);

    somestruct a;
    somestruct b;
    a.someChar = "aaa";
    b.someChar = "bbb";
    printf("%s\n", a.someChar);
    printf("%s\n", b.someChar);
    a = b;
    printf("%s\n", a.someChar);
    printf("%s\n", b.someChar);

    printf("array test\n");

    structarr[0].someChar = "a";
    structarr[1].someChar = "bb";
    structarr[2].someChar = "ccc";
    printf("%s\n", structarr[0].someChar);

    // structarr[0] = structarr[1];
    char * testchar = "abcdefg";
    structarr[0].someChar = (char *)malloc(strlen(testchar)+1);
    strcpy(structarr[0].someChar, testchar);
    printf("%s\n", structarr[0].someChar);
    cout << "End\n";
    return 0;
}
