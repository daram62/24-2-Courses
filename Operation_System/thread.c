#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

// run thread flag
int running = 1;

// 1st thread fuction: print second
void *print_time(void *arg) {
    int elapsed_seconds = 0;
    while (running) {
        elapsed_seconds++;
        printf("Time: %d seconds\n", elapsed_seconds);
        sleep(1);
    }
    printf("Time Thread exit\n");
    return NULL;
}

// 2nd thread fuction: print msg
void *print_message(void *arg) {
    while (running) {
        sleep(3);
        printf("Clap!\n");
        
    }
    printf("Message Thread exit\n");
    return NULL;
}

int main() {
    pthread_t thread1, thread2;

    // create Thread
    if (pthread_create(&thread1, NULL, print_time, NULL) != 0) {
        perror("Failed to create Time Thread");
        return 1;
    }

    if (pthread_create(&thread2, NULL, print_message, NULL) != 0) {
        perror("Failed to create Message Thread");
        return 1;
    }

    // waiting 10 secs
    sleep(10);

    // flag for end thread
    running = 0;

    // waiting for ends
    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);

    printf("Main thread exit\n");
    return 0;
}