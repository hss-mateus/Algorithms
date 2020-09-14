module Stack (Stack, empty, isEmpty, push, pop) where

newtype Stack a = Stack [a]

empty :: Stack a
empty = Stack []

isEmpty :: Stack a -> Bool
isEmpty (Stack []) = True
isEmpty _ = False

push :: a -> Stack a -> Stack a
push x (Stack xs) = Stack $ x:xs

pop :: Stack a -> Maybe (a, Stack a)
pop (Stack (x:xs)) = Just (x, Stack xs)
pop _ = Nothing
