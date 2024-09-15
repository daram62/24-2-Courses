// Minseo Kim
// 2021312602

#include <stdio.h>
#include <stdlib.h>

int** makeJaggedArray(int* len, int row) {
    int** array = (int**) malloc (row * sizeof(int*));

    for (int i = 0; i < row; i++) {
        array[i] = (int*)malloc(len[i] * sizeof(int));
    }
    return array;
}

void freeArray(int** array, int row) {
    for (int i = 0; i < row; i++) {
        free(array[i]);
    }
    free(array);
}

int main() {
    int row = 5;
    int len[] = {1,2,3,4,5};

    int** J_Array = makeJaggedArray(len, row);

    J_Array[0][0] = 10;
    J_Array[1][1] = 20;
    J_Array[2][2] = 30;
    J_Array[3][3] = 40;
    J_Array[4][4] = 50;

    printf("%d\n", J_Array[0][0]);
    printf("%d\n", J_Array[1][1]);
    printf("%d\n", J_Array[2][2]);
    printf("%d\n", J_Array[3][3]);

    freeArray(J_Array, row);

    return 0;
}