""" prompt
For this question you will be modifying the program that you downloaded in the Module for "Week 10: Source Code for the Greeting Application". I have attached the source code here also for your convenience.

Add another Button to the Frame for this application that has the text "Bye" on top of it. When the user clicks this button, your program must change the text on the greeting Label to say "Goodbye <name>" where name is the text that is in the nameEntry field. The other two buttons in this application must continue to function.

Hint: In order to add the Button to the Frame, you will add code to the createWidgets() method. You will also add a whole new method to class MyFrame called "sayBye()". This method is the one that Python will call when the user clicks on your new "Bye" button.

Find and select your modified greetingApp.py file from your computer to attach it here as your answer to this question.
"""

import tkinter
""" sets up a gui that accepts the user's name and greets them when a button is pressed
"""

class MyFrame(tkinter.Frame):
    def __init__(self):
        """ Initializes the Frame by putting the widgets on it """
        tkinter.Frame.__init__(self)
        self.pack()
        self.createWidgets()

    def createWidgets(self):
        """ Instantiates all of the widgets and places them onto the frame """
        self.nameEntry = tkinter.Entry()
        self.nameEntry.insert(0, "your name here")
        self.nameEntry.pack({"side": "left"})
        
        self.quitButton = tkinter.Button(self)
        self.quitButton["text"] = "Quit"
        self.quitButton["command"] =  self.quit
        self.quitButton.pack({"side": "left"})

        self.hiButton = tkinter.Button(self)
        self.hiButton["text"] = "Hello",
        self.hiButton["command"] = self.sayHi
        self.hiButton.pack({"side": "left"})

        # New button
        self.byeButton = tkinter.Button(self)
        self.byeButton["text"] = "Bye",
        self.byeButton["command"] = self.sayBye
        self.byeButton.pack({"side": "left"})

        self.greeting = tkinter.Label(self)
        self.greeting.pack({"side": "left"})

    def sayHi(self):
        """ greets the user by taking the text from the nameEntry field and putting it into the greeting field """
        self.greeting["text"] = "Hello " + self.nameEntry.get()

    # New method
    def sayBye(self):
        self.greeting["text"] = "Bye " + self.nameEntry.get()

if __name__ == "__main__":
    root = tkinter.Tk()
    app = MyFrame()
    app.mainloop()
    root.destroy()
