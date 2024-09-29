// Minseo Kim
// 2021312602

#include <stdio.h>

int main() {
    int rows = 500;
    int cols = 500;
    int element_size = sizeof(int); 

    int dense_storage = rows * cols * element_size;

    int storage_per_nonzero = 3 * element_size;

    int N = dense_storage / storage_per_nonzero + 1;

    printf("%d\n", N);

    return 0;
}