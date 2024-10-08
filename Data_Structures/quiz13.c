// 2021312602
// Minseo Kim

#include <stdio.h>
#include <stdlib.h>

#define MAX_STACK_SIZE 100
typedef struct {
    int key;
} element;

element stack[MAX_STACK_SIZE];
int top = -1;

void stackFull() {
    fprintf(stderr, "Stack is full, cannot add element");
    exit(EXIT_FAILURE);
}

element stackEmpty() {
    fprintf(stderr, "Stack is empty, cannot pop element");
    element empty = { -1 };
    return empty;
}

void push(element item) {
    if (top >= MAX_STACK_SIZE - 1) {
        stackFull();
        return;
    }
    stack[++top] = item;
}

element pop() {
    if (top == -1) {
        return stackEmpty();
    }
    return stack[top--];
}

int main() {
    element e;
    element tempStack[10];  
    int i;
    
    for (i = 0; i < 10; i++) {
        e.key = rand() % 100;  
        printf("Pushing %d \n", e.key);
        push(e);
    }
    
    for (i = 0; i < 10; i++) {
        tempStack[i] = pop();
    }
    
    printf("\nFIFO :\n");
    for (i = 9; i >= 0; i--) {
        printf("%d ", tempStack[i].key);
    }

    printf("\n");

    return 0;
}