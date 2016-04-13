#include <iostream>

struct memItem {
  int pid;
  char* addr;
  int requestSize;
  int actualSize;
};
memItem memTable[1000];

struct freeItem {
  char* addr;
  int size;
};
freeItem freeTable[1000];


// void someFunc(int* a) {
//     printf("%i\n", *a);
// }

int main()
{
    printf("Hello World!\n");
    char mem_start [8192] = {0};
    // int a = 1;
    // int* b = &a;
    // *b = 123;
    // std::cout << a;
    // std::cout << b;
    // someFunc(b);

    memItem item = memTable[0];

    item.pid = 101;
    item.addr = mem_start;
    item.requestSize = 10;
    item.actualSize = 16;

    printf("%i\n", memTable[0].pid);
    printf("%i\n", item.pid);

    memTable[0] = item;

    printf("%i\n", memTable[0].pid);
    printf("%i\n", item.pid);

    memTable[0].pid = 102;
    
    printf("%i\n", memTable[0].pid);
    printf("%i\n", item.pid);

    // if (memTable[1].addr == 0)
    //     printf("zero\n");
    // if (memTable[1].addr == NULL)
    //     printf("NULL\n");
    // if (memTable[1].addr == memTable[0].addr)
    //     printf("wrong\n");

    std::cout << memTable[1].addr;
    std::cout << memTable[1].pid;

    return 0;
}

