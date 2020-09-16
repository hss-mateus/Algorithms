#include "linked_list.c"
#include <stdlib.h>

typedef struct {
  linked_list *head;
  linked_list *tail;
} queue;

void enqueue(queue *q, int x) {
  linked_list *new_tail = malloc(sizeof(linked_list));
  new_tail->value = x;

  if (q->head == NULL) {
    q->head = q->tail = new_tail;
  } else {
    q->tail->next = new_tail;
    q->tail = q->tail->next;
  }

  if (q->head->next == NULL)
    q->head->next = q->tail;
}

int dequeue(queue *q) {
  int value = q->head->value;

  linked_list *new_head = q->head->next;
  free(q->head);
  q->head = new_head;

  return value;
}
