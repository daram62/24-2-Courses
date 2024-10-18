#include <stdio.h>
#include <stdlib.h>

#define MAX_QUEUE_SIZE 100
typedef struct {
    int key;
} element;

element queue[MAX_QUEUE_SIZE];
int front = 0, rear = 0;

void queueFull() {
    fprintf(stderr, "Queue is full");
    exit(EXIT_FAILURE);
}

element queueEmpty() {
    fprintf(stderr, "Queue is empty");
    element empty = { -1 };
    return empty;
}

void addq(element item) {
    int nextRear = (rear + 1) % MAX_QUEUE_SIZE;
    
    if (nextRear == front) {
        queueFull();
    }
    rear = nextRear;
    queue[rear] = item;
}

element deleteq() {
    if (front == rear) {
        return queueEmpty();
    }
    front = (front + 1) % MAX_QUEUE_SIZE;
    return queue[front];
}

int main() {
    element e;

    for (int i = 0; i < 10; i++) {
        e.key = i + 1;
        printf("Adding %d \n", e.key);
        addq(e);
    }
    
    printf("\n");
    for (int i = 0; i < 10; i++) {
        e = deleteq();
        if (e.key != -1) {
            printf("Deleted %d\n", e.key);
        }
    }

    return 0;
}