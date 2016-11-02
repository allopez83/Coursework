edge(a, b, 2).
edge(b, a, 2).
edge(a, c, 3). 
edge(c, a, 3).
edge(a, f, 4).
edge(f, a, 4).
edge(b, c, 2).
edge(c, b, 2).
edge(c, d, 3).
edge(d, c, 3).
edge(c, e, 1).
edge(e, c, 1).
edge(d, f, 5).
edge(f, d, 5).

find_path(Start, End, TotalCost, Path) :-
  find_path(Start, End, TotalCost, Path, [Start]).

find_path(Start, End, Cost, Path, Traveled) :-
  edge(Start, End, Cost),
  \+ member(End, Traveled),
  Path = [Start, End].

find_path(Start, End, TotalCost, Path, Traveled) :-
  edge(Start, X, InitCost),
  \+ member(X, Traveled),
  append(Traveled, [X], T),
  find_path(X, End, RestCost, TailPath, T),
  TotalCost is InitCost + RestCost,
  Path = [Start|TailPath].

% exists(E, [E|_]).
% exists(E, [_|L]) :-
%   exists(E, L)
%   .

% Sample run

% ?- find_path(a, c, TC, P).
% TC = 3,
% P = [a, c] ;
% TC = 4,
% P = [a, b, c] ;
% TC = 12,
% P = [a, f, d, c] ;
% false.

% ?- 