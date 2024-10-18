#include <stdio.h>
#define MAX_SIZE 7

typedef struct {
    int row;   
    int col;   
} Move;

typedef struct {
    int row;
    int col;
    int dir;
} Position;

int maze[MAX_SIZE][MAX_SIZE] = {
    {1,1,1,1,1,1,1},
    {1,0,0,1,0,0,1},
    {1,0,0,1,0,0,1},
    {1,0,0,0,0,0,1},
    {1,0,1,0,1,0,1},
    {1,0,1,0,0,0,1},
    {1,1,1,1,1,1,1}
};

int mark[MAX_SIZE][MAX_SIZE] = {0};

Move move[4] = {
    {-1, 0},  // North
    {0, 1},   // East
    {1, 0},   // South
    {0, -1}   // West
};

Position stack[MAX_SIZE * MAX_SIZE];
int top = -1;

void push(Position pos) {
    if (top >= MAX_SIZE * MAX_SIZE - 1) {
        printf("Stack overflow\n");
        return;
    }
    stack[++top] = pos;
}

Position pop() {
    if (top < 0) {
        Position nullPos = {-1, -1, -1};
        return nullPos;
    }
    return stack[top--];
}

int main() {
    int EXIT_ROW = 5;
    int EXIT_COL = 5;
    int found = 0;

    int row = 1;
    int col = 1;
    int dir = 0;
    mark[row][col] = 1;

    Position start;
    start.row = row;
    start.col = col;
    start.dir = dir;
    push(start);

    while (top >= 0 && !found) {
        Position current = pop();
        row = current.row;
        col = current.col;
        dir = current.dir;

        while (dir < 4 && !found) {
            int nextRow = row + move[dir].row;
            int nextCol = col + move[dir].col;

            if (nextRow == EXIT_ROW && nextCol == EXIT_COL && maze[nextRow][nextCol] == 0) {
                found = 1;

                Position nextPos;
                nextPos.row = row;
                nextPos.col = col;
                nextPos.dir = dir;
                push(nextPos);

                nextPos.row = nextRow;
                nextPos.col = nextCol;
                nextPos.dir = -1;  
                push(nextPos);
            }

            else if (maze[nextRow][nextCol] == 0 && mark[nextRow][nextCol] == 0) {
                mark[nextRow][nextCol] = 1;  

                Position newPos;
                newPos.row = row;
                newPos.col = col;
                newPos.dir = dir + 1;
                push(newPos);

                row = nextRow;
                col = nextCol;
                dir = 0; 
            }
            else {
                ++dir;
            }
        }
    }

    if (found) {
        printf("Path found:\n");
        for (int i = 0; i <= top; i++) {
            printf("(%d, %d)\n", stack[i].row, stack[i].col);
        }
        printf("(%d, %d)\n", EXIT_ROW, EXIT_COL);
    } else {
        printf("No path found.\n");
    }

    return 0;
}