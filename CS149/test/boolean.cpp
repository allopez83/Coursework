#include <iostream>
using namespace std;

void boolTest(bool b) {
    if (b) {
        cout << "true!\n";
    } else if (!b) {
        cout << "false!\n";
    } else {
        cout << "unknown\n";
    }
}

int main () {
    bool a = true,
         b = false;
    bool c = 0,
         d = 1,
         e = 2;

    boolTest(a);
    boolTest(b);
    boolTest(0);
    boolTest(1);
    boolTest(c);
    boolTest(d);
    boolTest(e);

    return 0;
}

// true!
// false!
// false!
// true!
// false!
// true!
// true!
