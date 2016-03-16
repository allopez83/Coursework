import tkinter

class View(tkinter.Frame):
    """
    User interface for celsius and fahreheit conversion application. Text box for temperature entry, two buttons for converting, depending on if user input is fahreheit or celsius, and a button to quit the application
    """
    def __init__(self, controller):
        tkinter.Frame.__init__(self)
        self.pack()
        self.controller = controller

        # Take input

        self.enterTemperature = tkinter.Entry()
        self.enterTemperature.insert(0, "What is the temperature?")
        self.enterTemperature.pack({"side": "left"})

        # Conversion buttons

        self.convertToFahrenheit = tkinter.Button(self)
        self.convertToFahrenheit["text"] = "Convert Celsius to Fahrenheit"
        self.convertToFahrenheit["command"] = self.controller.celsiusToFahrenheit
        self.convertToFahrenheit.pack({"side": "left"})

        self.convertToCelsius = tkinter.Button(self)
        self.convertToCelsius["text"] = "Convert Fahrenheit to Celsius"
        self.convertToCelsius["command"] = self.controller.fahrenheitToCelsius
        self.convertToCelsius.pack({"side": "left"})

        # Output

        self.outputLabel = tkinter.Label(self)
        self.outputLabel["text"] = str(0)
        self.outputLabel.pack({"side": "left"})

        # Quit

        self.quitButton = tkinter.Button(self)
        self.quitButton["text"] = "Quit"
        self.quitButton["command"] = self.quit
        self.quitButton.pack({"side": "left"})
