class Model:
    """
    Model is used for converting temperature that user inputs. Note that no data actually needs to be stored.
    """
    def celsiusToFahrenheit(self, celsius):
        return (celsius * 9 / 5) + 32

    def fahrenheitToCelsius(self, fahrenheit):
        return (fahrenheit - 32) * 5 / 9
