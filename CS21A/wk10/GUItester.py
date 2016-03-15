""" 
The main program creates a MyFrame object and waits for the user to push a Button on it.
"""

import tkinter
import MyFrame

if __name__ == "__main__":
    root = tkinter.Tk()     # starts up the tkinter framework
    view = MyFrame.MyFrame()
    view.mainloop()     # starts the event loop
    root.destroy()    # kills the event loop and takes the Frame down
