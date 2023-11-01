#include <iostream>
#include <list>

using namespace std;

int main() {

    list<int> test = {1, 2, 3, 4, 5};

    for(list<int>::iterator i = test.begin(); i != test.end(); i++){
        cout << *i << endl;
        if (*i % 2 == 0){
            test.remove(3);
            continue;
        }
    }

    cout << "-------" << endl;

    for (auto const &i: test) {
        std::cout << i << std::endl;
    }

    return 0;
}