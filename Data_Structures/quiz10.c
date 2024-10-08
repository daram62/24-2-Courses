// Minseo Kim
// 2021312602

#include <stdio.h>

int strnchar(char *s, char p) {
    int count = 0;
    while (*s) {
        if (*s == p) {
            count++;
        }
        s++;
    }
    return count;
}

int main() {
    char str[] = "Hello, world!";
    char ch = 'o';
    int result = strnchar(str, ch);
    printf("%d\n", result);
    return 0;
}