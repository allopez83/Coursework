For this assignment, you will write an airline reservation system.

Download `flights.prolog` from the course website.

The top of the file lists airports and flight information.

One example flight:

> `flight(sfo, lax, 8:00, 9:20, 86.31).`

The above fact indicates that there is a flight leaving _SFO at 8am_
and arriving at _LAX 9:20am_, with a fare of _$86.31_.

For this assignment, implement a route rule:

> `route(Departing, Arriving, Visited, DepartureTime, ArrivalTime, Flights, TotalCost)`

- _Departing_: 3 letter code of the airport the traveler is departing from.
- _Arriving_: 3 letter code of the airport the traveler is traveling to.
- _Visited_: Airports **already visited** previously in the trip.
- _DepartureTime_: Time leaving the departing airport.
- _ArrivalTime_: Time arriving at the final destination.
- _Flights_: **List** of the airports to visit, in order.
- _TotalCost_: Cost of all flights.

For any transfer through an intermediary airport, the first flight must arrive at the intermediary airport **before the second flight departs**.
