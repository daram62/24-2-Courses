// Minseo Kim
// 2021312602

#include <stdio.h>
#include <stdlib.h>

enum ShapeType { RECTANGLE, TRIANGLE, CIRCLE };

struct Shape {
    enum ShapeType type;

    union {
        struct {
            int length;
            int width;
        } rectangle;

        struct {
            int base;
            int height;
        } triangle;

        struct {
            float radius;
        } circle;
    } data;
};

int main() {

    struct Shape mishape;
    
    mishape.type = RECTANGLE;
    mishape.data.rectangle.length = 10;
    mishape.data.rectangle.width = 5;

    if (mishape.type == RECTANGLE) {
        printf("Rectangle's area : %d\n", mishape.data.rectangle.length * mishape.data.rectangle.width);
    }

        mishape.type = TRIANGLE;
    mishape.data.triangle.base = 6;
    mishape.data.triangle.height = 4;

    if (mishape.type == TRIANGLE) {
        printf("Triangle's area: %.2f\n", 0.5 * mishape.data.triangle.base * mishape.data.triangle.height);
    }

    mishape.type = CIRCLE;
    mishape.data.circle.radius = 7.0;

    if (mishape.type == CIRCLE) {
        printf("Circle's area: %.2f\n", 3.14 * mishape.data.circle.radius * mishape.data.circle.radius);
    }

    return 0;
}