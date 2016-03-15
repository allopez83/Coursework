import tkinter

class MyFrame(tkinter.Frame):

    """
    class MyFrame is a tkinter.Frame that contains two Buttons and a Label. One Button increments a counter and the other Button quits. The Label is used to give the user information.
    """

    def __init__(self):
        """ 
        Places the controls onto the Frame. 
        """
        tkinter.Frame.__init__(self)   # initializes the superclass
        self.pack()   #  required in order for the Buttons to show up properly

        #set up the increment Button
        self.incrementButton = tkinter.Button(self)   
        self.incrementButton["text"] = "Increment"
        self.incrementButton.pack({"side": "left"})

        #set up the Label
        self.labelForOutput = tkinter.Label(self)
        self.labelForOutput["text"] = 0
        self.labelForOutput.pack({"side": "left"})

        #set up the quit Button
        self.quitButton = tkinter.Button(self)
        self.quitButton["text"] = "Quit"
        self.quitButton.pack({"side": "left"})
