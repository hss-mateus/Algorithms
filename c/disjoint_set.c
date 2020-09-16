#include <stdlib.h>

typedef struct {
  int *parent;
  int *weight;
} disjoint_set;

disjoint_set create(int size) {
  disjoint_set set;

  set.parent = malloc(sizeof(int) * size);
  set.weight = malloc(sizeof(int) * size);

  for (int i = 0; i < size; i++) {
    set.parent[i] = i;
    set.weight[i] = 1;
  }

  return set;
}

int root(disjoint_set set, int index) {
  int old_root = index;
  int root = set.parent[index];

  while (old_root != root) {
    old_root = root;
    root = set.parent[root];
  }

  return root;
}

void union_elements(disjoint_set set, int a, int b) {
  if (a == b) return;

  int root_a = root(set, a);
  int root_b = root(set, b);

  if (root_a == root_b) return;

  int weight_a = set.weight[root_a];
  int weight_b = set.weight[root_b];

  if (weight_a < weight_b) {
    set.parent[root_a] = root_b;
    set.weight[root_b] += weight_a;
  } else {
    set.parent[root_b] = root_a;
    set.weight[root_a] += weight_b;
  }
};

void destroy(disjoint_set set) {
  free(set.parent);
  free(set.weight);
}
