// Let length[i] be the desired length of row i of a two-dimensional array. 
// Write a function similar to make2dArray() to create a two-dimensional array 
// such that row i has length[i] elements.

#include <iostream>

int** make2dArray(int rows, int cols) {
    
    int **array = new  int*[rows];
    
    for (int i = 0; i < rows; ++i) {
        array[i] = new int[cols];
    }

    return array;
}