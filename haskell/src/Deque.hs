module Deque where

newtype Deque a = Deque [a]

empty :: Deque a
empty = Deque []

isEmpty :: Deque a -> Bool
isEmpty (Deque []) = True
isEmpty _ = False

addFirst :: a -> Deque a -> Deque a
addFirst x (Deque xs) = Deque $ x:xs

addLast :: a -> Deque a -> Deque a
addLast x (Deque xs) = Deque $ xs ++ [x]

removeFirst :: Deque a -> Maybe (a, Deque a)
removeFirst (Deque (x:xs)) = Just (x, Deque xs)
removeFirst _ = Nothing

removeLast :: Deque a -> Maybe (a, Deque a)
removeLast (Deque []) = Nothing
removeLast (Deque xs) = Just (last xs, Deque (init xs))
