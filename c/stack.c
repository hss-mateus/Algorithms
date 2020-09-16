#include "linked_list.c"
#include <stdlib.h>

typedef struct {
  linked_list *head;
} stack;

void push(stack *s, int x) {
  linked_list *new_head = malloc(sizeof(linked_list));

  new_head->next = s->head;
  new_head->value = x;

  s->head = new_head;
}

int pop(stack *s) {
  int value = s->head->value;

  linked_list *new_head = s->head->next;

  free(s->head);

  s->head = new_head;

  return value;
}
