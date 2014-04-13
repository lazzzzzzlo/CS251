#include <iostream>
#include <thread>
#include <stdlib.h>
#include <stdio.h>
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

void get_subset_sum(char ***pass_array, int length);

Key Symbol::decrypt(Key key){
    Key decryptedKey;

    int frst_subset_size = C/2; //floor(C/2) because of integer division
    int snd_subset_size = C - frst_subset_size; //the rest of the partial password

    char **frst_pass_array = (char **) malloc(sizeof(char *)*frst_subset_size);
    char **snd_pass_array = (char **) malloc(sizeof(char *)*snd_subset_size);

    std::thread frst_thread(get_subset_sum, &frst_pass_array, frst_subset_size); //get subset sums
    std::thread snd_thread(get_subset_sum, &snd_pass_array, snd_subset_size); //get subset sums

    frst_thread.join();
    snd_thread.join();

    for(int i = 0; i < frst_subset_size; i++)
        printf("%s\n", frst_pass_array[i]);

    printf("\n\n\n");

    for(int i = 0; i < snd_subset_size; i++)
        printf("%s\n", snd_pass_array[i]);

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

