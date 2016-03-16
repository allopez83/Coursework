import tkinter
import view
import model

class Controller:
    """
    Controller at the center of the MVC temperature conversion application, which converts a user input float to celsius or fahrenheit
    """
    def __init__(self):
        """
        Initializes MVC components
        """
        root = tkinter.Tk()
        root.wm_title("Temperature Converter")
        self.model = model.Model()
        self.view = view.View(self)
        self.view.mainloop()
        root.destroy()

    def celsiusToFahrenheit(self):
        """
        Interprets user input to text field as celsius and converts to equivalent fahrenheit temperature
        """
        resultTemperature = ""
        try:
            celsius = float(self.view.enterTemperature.get())
        except:
            resultTemperature = "Not a number!"
        else:
            resultTemperature = str(self.model.celsiusToFahrenheit(celsius))
        self.view.outputLabel["text"] = resultTemperature

    def fahrenheitToCelsius(self):
        """
        Interprets user input to text field as fahrenheit and converts to equivalent celsius temperature
        """
        resultTemperature = ""
        try:
            fahrenheit = float(self.view.enterTemperature.get())
        except:
            resultTemperature = "Not a number!"
        else:
            resultTemperature = str(self.model.fahrenheitToCelsius(fahrenheit))
        self.view.outputLabel["text"] = resultTemperature

if __name__ == "__main__":
    print("Doin some mvc temp converting")
    c = Controller()
