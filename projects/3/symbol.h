#ifndef _SYMBOL_H_
#define _SYMBOL_H_

class Symbol {
    private:
    	Key subsetSum;
	Key encrypted;
	Key T[N];

    public:
	Symbol(char []);
    Key get_encrypted();
	void initializeTable();
        Key decrypt(Key);
};

#endif
