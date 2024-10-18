// Minseo Kim
// 2021312602

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void stringremove(char *s, int i, int j) {
    int len = strlen(s);
    if (i < 0 || i >= len) {
        fprintf(stderr, "Error: Starting index %d is out of bounds (length = %d).\n", i, len);
        exit(1);
    }
    if (j < 0) {
        fprintf(stderr, "Error: Number of characters to remove (%d) cannot be negative.\n", j);
        exit(1);
    }

    if (i + j > len) {
        fprintf(stderr, "Error: Cannot remove %d characters from index %d (string length = %d).\n", j, i, len);
        exit(1);
    }

    memmove(&s[i], &s[i + j], len - i - j + 1); 

}

int main() {
    char str[100] = "Hello, world!";
    int start = 7; 
    int num = 5;   

    printf("Before: %s\n", str);
    stringremove(str, start, num);
    printf("After: %s\n", str);

    return 0;
}