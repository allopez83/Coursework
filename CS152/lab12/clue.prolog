victim(mr_boddy).          % candlestick  hall
victim(cook).              % knife        kitchen
victim(motorist).          % wrench       lounge
victim(police_officer).    % lead_pipe    library
victim(yvette).            % rope         billiard_room
victim(singing_telegram).  % revolver     hall

suspect(professor_plum).
suspect(mrs_peacock).
suspect(mrs_white).
suspect(miss_scarlet).
suspect(colonel_mustard).
suspect(mr_green).
suspect(wadsworth).

weapon(wrench).
weapon(candlestick).
weapon(lead_pipe).
weapon(knife).
weapon(revolver).
weapon(rope).

room(hall).
room(kitchen).
room(lounge).
room(library).
room(billiard_room).

murder(mr_boddy,candlestick,hall).
murder(cook,knife,kitchen).
murder(motorist,wrench,lounge).
murder(police_officer,lead_pipe,library).
murder(singing_telegram,revolver,hall).
murder(yvette,rope,billiard_room).

% Clues

% Set 1

% X suspected perpetrator in killing Y
% Everyone wanted to kill mr_boddy
susp_perp(mrs_peacock, cook).
susp_perp(colonel_mustard, motorist).
susp_perp(miss_scarlet, yvette).
susp_perp(colonel_mustard, yvette).
susp_perp(miss_scarlet, police_officer).
susp_perp(professor_plum, singing_telegram).
susp_perp(wadsworth, singing_telegram).

susp_notkill(mrs_white, mr_boddy).
% mr_green has alibi
susp_notkill(mr_green, mr_boddy).
susp_notkill(mr_green, cook).
susp_notkill(mr_green, motorist).
susp_notkill(mr_green, police_officer).
susp_notkill(mr_green, yvette).
susp_notkill(mr_green, singing_telegram).

% Set 2

% X didn't use Y
susp_notuse(colonel_mustard, rope).
susp_notuse(professor_plum, revolver).
susp_notuse(mrs_peacock, candlestick).

% X wasn't at Y
susp_notat(miss_scarlet, billiard_room).
susp_notat(professor_plum, kitchen).
susp_notat(colonel_mustard, hall).
susp_notat(miss_scarlet, hall). % not in room with revolver


% Update accuse to solve the murders.
% Add more facts and rules as needed.
accuse(S,V)     :- murder(V,W,R),
                   victim(V),
                   weapon(W),
                   room(R),
                   suspect(S),
                   might_at(S,R),
                   might_use(S,W),
                   might_kill(S,V)
                   .

definitely_killed(S,V) :- accuse(S,V), susp_perp(S,V).

might_at(S,R)   :- \+ susp_notat(S, R).
might_use(S,W)  :- \+ susp_notuse(S, W).
might_kill(S,V) :- \+ susp_notkill(S, V).

killed_everyone(S) :- accuse(S, mr_boddy),
                      accuse(S, cook),
                      accuse(S, motorist),
                      accuse(S, police_officer),
                      accuse(S, yvette),
                      accuse(S, singing_telegram).

% Missed these stuff

alibi(mr_green, _).
alibi(mrs_white, mr_boddy).
alibi(miss_scarlet, V) :- murder(V, _, R),
                          murder(_, revolver, R).
motive(S, mr_boddy) :- suspect(S),
                       S \= wadsworth.



%% Run Output %%

% $ prolog -q clue.prolog 
% ?- killed_everyone(X).
% X = wadsworth.

% ?- 

% Wadsworth killed everyone!
