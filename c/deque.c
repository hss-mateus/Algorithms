#include "linked_list.c"
#include <stdlib.h>

typedef struct {
  linked_list *head;
  linked_list *tail;
} deque;

void add_first(deque *d, int x) {
  linked_list *new_head = malloc(sizeof(linked_list));
  new_head->value = x;
  new_head->next = d->head;

  if (d->head == NULL) {
    d->tail = d->head = new_head;
  } else {
    d->head->prev = new_head;
    d->head = new_head;
  }
}

void add_last(deque *d, int x) {
  linked_list *new_tail = malloc(sizeof(linked_list));
  *new_tail = (linked_list){ x, d->tail, NULL };

  if (d->head == NULL) {
    d->head = d->tail = new_tail;
  } else {
    d->tail->next = new_tail;
    d->tail = d->tail->next;
  }
}

int remove_first(deque *d) {
  int value = d->head->value;

  linked_list *old_head = d->head;

  d->head = d->head->next;

  if (old_head == d->tail)
    d->tail = NULL;

  if (d->head != NULL)
    d->head->prev = NULL;

  free(old_head);

  return value;
}

int remove_last(deque *d) {
  int value = d->tail->value;

  if (d->head == d->tail) {
    free(d->head);
    d->head = d->tail = NULL;
  } else {
    linked_list *new_tail = d->tail->prev;
    free(d->tail);
    d->tail = new_tail;
    d->tail->next = NULL;
  }

  return value;
}
