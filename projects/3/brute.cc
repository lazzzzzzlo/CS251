#include <iostream>
#include <stdlib.h>
#include <math.h>
#include "key.h"
#include "brute.h"

using namespace std;


int main(int argc, char *argv[]){

    if (argc != 2) exit(EXIT_FAILURE);

    // Create a Key from the input password
    Brute bruteKey((char*) argv[1]);

    //initialize the table
    bruteKey.initializeTable();

    //Add code here
    bruteKey.decrypt(bruteKey.get_encrypted());

    return 0;
}

Brute::Brute(char s[]){

    char buffer[C+1];

    //initialze to encrypted key
    encrypted.keySetString(s);

}

Key Brute::get_encrypted() {
    return (encrypted);
}

void Brute::initializeTable(){

    char buffer[C+1];   

    // Read in table T
    for (int i = 0; i < N; i++) {
        cin.getline(buffer,C+1,'\n');
        buffer[C]=0;
        T[i].keySetString(buffer);
    }
}

Key Brute::decrypt(Key keyToDecrypt){

    Key decryptedKey;
    Key tempKey;
    int *indeces = (int *)calloc(C, sizeof(int));
    char possiblePass[C + 1];

    while(1) {
        for(int j = 0; j < C; j++) {
            possiblePass[j] = ALPHABET[indeces[j]];
        }
        possiblePass[C] = '\0';

        tempKey = Key(possiblePass);

        if (keyToDecrypt.keyEquals(tempKey.keySubsetSum(T))) {
            printf("%s\n", possiblePass);
            decryptedKey.keySet(tempKey);
        }

        for(int i = C - 1;;i--) {
            if (i < 0)
                goto end;
            indeces[i]++;
            if (indeces[i] == R)
                indeces[i] = 0;
            else
                break;
        }
    }

end:
    free(indeces);
    return decryptedKey;
}

