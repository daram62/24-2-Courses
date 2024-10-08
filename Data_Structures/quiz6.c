// Minseo Kim
// 2021312602

#include <stdio.h>
#include <stdlib.h>
#define MAX_DEGREE 101

typedef struct {
    int degree;
    int coef[MAX_DEGREE];
} polynomial;

void attachPoly(polynomial *p, float coeff, int expo) {
    
    if (expo >= MAX_DEGREE) {
        printf("error\n");
        return;
    }

    p->coef[expo] = coeff;

    if (expo > p->degree) {
        p->degree = expo;
    }
}

void removePoly(polynomial *p, int expo) {
    
    if (expo >= MAX_DEGREE) {
        printf("error\n");
        return;
    }

    p->coef[expo] = 0;

    if (expo == p->degree) {
        int newDegree = expo - 1;

        while (newDegree >= 0 && p->coef[newDegree] == 0){
            newDegree --;
        }
        p->degree = newDegree;
    }
}

void PrintPoly(polynomial p) {

    for (int i = p.degree; i >= 0; i--) {
        if (p.coef[i] != 0) {
            if (i == 0) 
                printf("%d", p.coef[i]);
            else {
                printf("%dx^%d ", p.coef[i], i);
                if (i != 0) printf("+ ");
            }
        }
    }

    printf("\n");
}

int main() {
    polynomial p = {0};
    p.degree = 0;

    attachPoly(&p, 3, 6); // 3x^6
    attachPoly(&p, 2, 5); // 2x^5
    attachPoly(&p, 4, 0); // 4

    PrintPoly(p);

    removePoly(&p, 5);

    printf("After delete -> \n");

    PrintPoly(p);

    return 0;
}