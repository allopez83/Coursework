"""
Creates two Rectangle objects and stores them in a binary file on disk. Reads them back in again to show that it worked.
"""

import pickle
from rectangle import Rectangle

if __name__ == "__main__":
    r1 = Rectangle()
    print(r1)
    r2 = Rectangle()
    r2.setData(5, 6)
    print(r2)

    datafile = open('data.pkl', 'wb')
    pickle.dump(r1, datafile)
    pickle.dump(r2, datafile)
    datafile.close()

    datafile = open('data.pkl', 'rb')
    r3 = pickle.load(datafile)
    r4 = pickle.load(datafile)
    print(r3)
    print(r4)
