% Get the facts written to KB.
:- include('kb').

% Put a dummy obstacle outside the grid, which any call in the upcoming
% predicates checks against.
obstacle(999, 998).

% element(i, L, v) is true if L[i] = v
element(1, [H|_], H).
element(Idx, [_|T], R):-
    Idx > 1,
    Idx2 is Idx - 1,
    element(Idx2, T, R).

% Get [1, -1, 0, 0][Idx]
dx(Idx, Dx):-
    (Idx = 1; Idx = 2; Idx = 3; Idx = 4),
    element(Idx, [1, -1, 0, 0], Dx).

% Get [0, 0, 1, -1][Idx]
dy(Idx, Dx):-
    (Idx = 1; Idx = 2; Idx = 3; Idx = 4),
    element(Idx, [0, 0, 1, -1], Dx).

% Check if all pads have rocks on them
rocks_on_pads(S):-
    forall(pad(A, B), rock(A, B, S)).

% =============== Successor State Axioms ===============

% Uncomment this to support invalid robot moves (like running into obstacles)
% robot(X, Y, result(A, S)):-
%     robot(X, Y, S),
%     dx(A, Dx), dy(A, Dy),
%     inside_grid(X, Y),
%     XM is X - Dx, YM is Y - Dy,
%     (\+ empty(XM, YM, S), \+ rock(XM, YM, S), (\+ rocks_on_pads(S) ; \+ teleport(XM, YM)) ; (rock(XM, YM, S), rock(XM, YM, result(A, S))); \+ inside_grid(XM, YM)).

% The robot will be in position (X, Y) after applying action A iff:
% - The robot was in position (X + dx[A], Y + dy[A])
% - At least one of the following is true:
%       * The (X, Y) was empty.
%       * there was a rock in (X, Y) in S, however it no longer exists in result(A, S).
%       * (X, Y) contains the teleport, given that all rocks lie on pads.
robot(X, Y, result(A, S)):-
    dx(A, Dx), dy(A, Dy),
    inside_grid(X, Y),
    XM is X + Dx, YM is Y + Dy,
    robot(XM, YM, S),
    (empty(X, Y, S) ; (rock(X, Y, S), \+ rock(X, Y, result(A, S))); teleport(X, Y), rocks_on_pads(S)).

% If the rock remains in the same position as in S:
rock(X, Y, result(A, S)):-
    rock(X, Y, S),
    dx(A, Dx), dy(A, Dy),
    XR is X - Dx, YR is Y - Dy,
    XM is X + Dx, YM is Y + Dy,
    % No robot on the other side, the rock is on a border or the next cell is blocked.
    (\+ robot(XM, YM, S) ; \+ inside_grid(XM, YM); \+ empty(XR, YR, S) ; \+ inside_grid(XR, YR)).


% If the rock moved from another postition to the current position
rock(X, Y, result(A, S)):-
    dx(A, Dx), dy(A, Dy),
    XR is X + Dx, YR is Y + Dy,
    XM is X + 2 * Dx, YM is Y + 2 * Dy,
    % The current cell was empty
    % There was a rock one step away
    % There was a robot two steps away
    robot(XM, YM, S), rock(XR, YR, S),
    empty(X, Y, S).


% Check wether (X, Y) lie within the grid boundaries.
inside_grid(X, Y):-
    X > 0, Y > 0,
    width(W), height(H),
    X =< H, Y =< W.

% Check whether the cell (X, Y) is empty in situation S
empty(X, Y, S):-
    inside_grid(X, Y),
    \+rock(X, Y, S),
    \+teleport(X, Y),
    \+obstacle(X, Y).

% Generate the search plan (Check whether S is a goal situation)
query(S):-
    teleport(X, Y), robot(X,Y,S), rocks_on_pads(S).

% query:
% call_with_depth_limit(query(S), 13, R).
