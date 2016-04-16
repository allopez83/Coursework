#include <iostream>

int main() {
    wchar_t buffer[MAX_PATH]; 
    GetModuleBaseName(NULL, buffer, MAX_PATH);
}