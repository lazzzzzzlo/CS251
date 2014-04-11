#include <iostream>
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

Key Symbol::decrypt(Key key){


}
