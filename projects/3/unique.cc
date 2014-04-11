#include <iostream>
#include "key.h"

using namespace std;

int main(int argc, char *argv[]){

    char **rows = (char **) malloc(C*sizeof(char *));

    for(int i = 0, j = C - 1; i < C;i++, j--) {
        rows[i] = (char *) malloc(C*sizeof(char));
        memset(rows[i], ALPHABET[0], C);
        rows[i][j] = ALPHABET[1];
        rows[i][C] = 0;
    }

    int freed = 0, random;
    while(freed <  C) {
        random = rand() % C;      
        if (rows[random] == NULL) {
            continue;
        }
        printf("%s\n", rows[random]);
        free(rows[random]);
        rows[random] = NULL;
        freed++;
    }

    free(rows);
}

