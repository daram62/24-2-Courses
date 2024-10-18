// Minseo Kim
// 2021312602

#include <stdio.h>

#define MAX_TERMS 10000 

typedef struct {
    int row;
    int col;
    int value;
} Term;

int main() {
    int numRows = 500;
    int numCols = 500;
    int numTerms; 
    Term a[MAX_TERMS]; 
    int startingPos[500]; 

    for(int i = 0; i < numCols; i++) {
        startingPos[i] = 0;
    }

    for(int i = 1; i <= numTerms; i++) {
        startingPos[a[i].col]++;
    }

    int previousCount = startingPos[0];
    startingPos[0] = 1;

    for(int i = 1; i < numCols; i++) {
        int currentCount = startingPos[i];
        startingPos[i] = startingPos[i - 1] + previousCount;
        previousCount = currentCount;
    }

    return 0;
}