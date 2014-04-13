#include <iostream>
#include <thread>
#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include "key.h"
#include "symbol.h"

using namespace std;


int main(int argc, char *argv[]){
   
   if (argc != 2) exit(EXIT_FAILURE);
   
   // Create a Key from the input password
   Symbol symKey((char*) argv[1]);
   
   //initialize the table
   symKey.initializeTable();

   //Add code here
   symKey.decrypt(symKey.get_encrypted());
}

Symbol::Symbol(char s[]){
 
   char buffer[C+1];
   
   //initialze to encrypted key
   encrypted.keySetString(s);
   
}

void Symbol::initializeTable(){
   
   char buffer[C+1];   

   // Read in table T
   for (int i = 0; i < N; i++) {
      cin.getline(buffer,C+1,'\n');
      buffer[C]=0;
      T[i].keySetString(buffer);
   }
}

Key Symbol::get_encrypted() {
    return (encrypted);
}

void get_subset_sum(char ***pass_array, int length);

Key Symbol::decrypt(Key key){
    Key decryptedKey;

    int frst_subset_size = (int) pow(R, C/2); //size of alphabet raised to floor of C/2
    int snd_subset_size = (int) pow(R, C - C/2); //the rest of the partial password

    char **frst_pass_array = (char **) malloc(sizeof(char *)*frst_subset_size);
    char **snd_pass_array = (char **) malloc(sizeof(char *)*snd_subset_size);

    printf("frst_subset_size: %d\tsnd_subset_size: %d\n", frst_subset_size, snd_subset_size);

    std::thread frst_thread(get_subset_sum, &frst_pass_array, C/2); //get subset sums
    std::thread snd_thread(get_subset_sum, &snd_pass_array, C - C/2); //get subset sums

    frst_thread.join();
    snd_thread.join();

    return decryptedKey;
}

void get_subset_sum(char ***pass_array, int length) {

    int pass_count = 0;
    int *indeces = (int *) calloc(length, sizeof(int));

    while(1) {
        char *possiblePass = (char *) malloc((length+1)*sizeof(char));
        for(int j = 0; j < length; j++)
            possiblePass[j] = ALPHABET[indeces[j]];
        possiblePass[length] = '\0';
        (*pass_array)[pass_count] = possiblePass;

        for(int i = length - 1;;i--) {
            if (i < 0)
                goto end;
            indeces[i]++;
            if (indeces[i] == R)
                indeces[i] = 0;
            else
                break;
        }
        pass_count++;
    }
end:
    free(indeces);
}

