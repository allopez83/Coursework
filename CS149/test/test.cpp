#include <iostream>
#include <unistd.h>

using namespace std;

void someFunc(int* a) {
    printf("%i\n", *a);
}

int main()
{
    printf("Hello World!\n");
    
    int a = 1;
    int* b = &a;
    *b = 123;
    int* c = b;
    
    cout << a; printf("\n");
    cout << *b; printf("\n");
    cout << *c; printf("\n");
    
    someFunc(b);
	
	cout<< _getpid();
	
	system("pause");
}

