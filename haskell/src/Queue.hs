module Queue (Queue, empty, isEmpty, enqueue, dequeue) where

newtype Queue a = Queue [a]

empty :: Queue a
empty = Queue []

isEmpty :: Queue a -> Bool
isEmpty (Queue []) = True
isEmpty _ = False

enqueue :: a -> Queue a -> Queue a
enqueue x (Queue xs) = Queue $ x:xs

dequeue :: Queue a -> Maybe (a, Queue a)
dequeue (Queue []) = Nothing
dequeue (Queue xs) = Just (last xs, Queue (init xs))
