module DisjointSet (DisjointSet, fromList, empty, singleton, union, isConnected) where

import qualified Data.Map as M

-- The set is represented as two maps:
-- Parents is a tree containing the connections between two elements, roots are
-- represented by equal key and value.
-- Weights contains the sub-nodes count in each tree node.
data DisjointSet a = DisjointSet { parents :: M.Map a a
                                 , weights :: M.Map a Int
                                 }
                   deriving (Eq, Show)

{-| Create a empty disjoint set. -}
empty :: DisjointSet a
empty = DisjointSet M.empty M.empty

{-| Create a one-element disjoint set. -}
singleton :: a -> DisjointSet a
singleton x = DisjointSet (M.singleton x x) (M.singleton x 1)

{-|
Insert an element into the set.
If it's already in, the original set is returned.
-}
insert :: Ord a => a -> DisjointSet a -> DisjointSet a
insert x (DisjointSet ps ws) =
  case M.lookup x ps of
    Nothing -> DisjointSet ps' ws'
    _ -> DisjointSet ps ws
  where ps' = M.insert x x ps
        ws' = M.insert x 1 ws

{-| Check if two elements are connected. -}
isConnected :: (Eq a, Ord a) => a -> a -> DisjointSet a -> Bool
isConnected a b = (==) <$> root a <*> root b

{-| Create a disjoint set based on a list containing connections. -}
fromList :: Ord a => [(a, a)] -> DisjointSet a
fromList = foldr (\(x, y) -> union x y . insert y . insert x) empty

{-|
Connect two elements in a disjoint set.
If some element don't exist, the original set is returned.
-}
union :: Ord a => a -> a -> DisjointSet a -> DisjointSet a
union a b xs =
  case (root a xs, root b xs) of
    (Just ra, Just rb) -> unionRoots ra rb xs
    _ -> xs

-- Given two roots, union them comparing their weights
unionRoots :: Ord a => a -> a -> DisjointSet a -> DisjointSet a
unionRoots ra rb (DisjointSet ps ws)
  | wa > wb = DisjointSet (updateRoot ra rb) (updateWeight ra)
  | otherwise = DisjointSet (updateRoot rb ra) (updateWeight rb)
  where wa = M.findWithDefault 1 ra ws
        wb = M.findWithDefault 1 rb ws
        updateRoot rx ry = M.adjust (const rx) ry ps
        updateWeight rx = M.adjust (const $ wa + wb) rx ws

-- Get the root of an element in the set.
root :: Ord a => a -> DisjointSet a -> Maybe a
root x xs =
  case (parent', Just x == parent') of
    (_, True) -> parent'
    (Just y, _) -> root y xs
    _ -> Nothing
  where parent' = M.lookup x (parents xs)
