import tkinter
import view    # the VIEW
import model    # the MODEL

class Controller:
    """
    The Controller for an app that follows the Model/View/Controller architecture.
    When the user presses a Button on the View, this Controller calls the appropriate
    methods in the Model. The Controller handles all communication between the Model
    and the View.
    """
    def __init__(self):
        """
        This starts the Tk framework up, instantiates the Model (a Counter object),
        instantiates the View (a MyFrame object), and starts the event loop that waits
        for the user to press a Button on the View.
        """
        root = tkinter.Tk()
        self.model = model.Model()
        self.view = view.View(self)
        self.view.mainloop()
        root.destroy()

    def buttonPressed(self):
        """
        Python calls this method when the user presses the incrementButton in the View.
        """
        self.model.inc()
        self.view.outputLabel["text"] = str(self.model)

if __name__ == "__main__":
    c = Controller()
