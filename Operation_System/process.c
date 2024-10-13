#include <stdio.h>    
#include <stdlib.h>   
#include <unistd.h>   
#include <sys/wait.h> 

int main() {
    pid_t parent_pid = getpid(); 
    printf("Parent PID: %d\n", parent_pid);
    
    // 생성할 child process의 개수
    int num_children = 10; 

    for (int i = 0; i < num_children; i++) {
        pid_t pid = fork(); 

        if (pid < 0) {
            perror("fork failed");
            exit(1);
        } else if (pid == 0) {
            printf("Child %d PID: %d\n", i + 1, getpid());
            sleep(2);
            exit(0);
        } else { // pid > 0
            // Parent Process
        }
    }

    for (int i = 0; i < num_children; i++) {
        pid_t child_pid = wait(NULL);
        printf("Child with PID %d has terminated\n", child_pid);
    }

    printf("Parent process exiting.\n");
    return 0;
}