int pmatch(char* string, char* pat) {
    int i = 0, j = 0;
    int lens = strlen(string);
    int lenp = strlen(pat);
    
    // while to for
    for (i = 0, j = 0; i < lens && j < lenp; ) {
        if (string[i] == pat[j]) {
            i++;
            j++;
        } else if (j == 0) {
            i++;
        } else {
            j = failure[j - 1] + 1;
        }
    }
    
    return (j == lenp) ? i - lenp : -1;
}