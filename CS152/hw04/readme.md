For this assignment, you will write an airline reservation system.

Download flights.prolog from the course website.

The top of the file lists airports and flight information.

One example flight:

> `flight(sfo, lax, 8:00, 9:20, 86.31).`

The above fact indicates that there is a flight leaving **SFO at 8am**
and arriving at **LAX 9:20am**, with a fare of **$86.31**.

For this assignment, implement a route rule:

> `route(Departing, Arriving, Visited, DepartureTime, ArrivalTime, Flights, TotalCost)`

- **Departing**: 3 letter code of the airport the traveler is departing from.
- **Arriving**: 3 letter code of the airport the traveler is traveling to.
- **Visited**: Airports already visited previously in the trip.
- **DepartureTime**: Time leaving the departing airport.
- **ArrivalTime**: Time arriving at the final destination.
- **Flights**: List of the airports to visit, in order.
- **TotalCost**: Cost of all flights.

For any transfer through an intermediary airport, the first flight must arrive at the intermediary airport before the second flight departs.
