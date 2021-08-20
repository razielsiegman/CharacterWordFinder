# CharacterWordFinder
Author: Raziel Siegman

## Details
----------
Given a dictionary of all the words in the English language, the CharacterWordFinder (CWF) is a tool that allows one to find the words with the highest freuquency of a given letter(s):
- Given a set of characters, the program can either find the words with the total highest freuqency of the inputted characters, or the words that have the highest proportion of said characters, relative to the length of the word.
- The algorithm runs in O(nlogn) time, allowing it to find results for a user performing a single request in inconsequential time, given a list of all the words in the English language.
    - This assumes that each individual word is small enough to be sorted in constant time, which may not be true if the program is generalized for other purposes.
- By default, the [txt file](https://github.com/razielsiegman/CharacterWordFinder/blob/main/WordList.txt) contains a list of all the words in the [English language](http://www.mieliestronk.com/wordlist.html).
    - The CWF can be generalized to other languages, a subset of the English language, or other purposes entirely.  Doing so would involve replacing the text file, or making a trivial alteration to the code.


## Usage
----------
- The System will prompt the user to provide parameters of whether the program should find words with the highest total count or highest proportion of the inputted characters, the characters to be used, and the number of desired words.
- To allow for generalizing the CWF in other contexts, the inputs are case sensitive, and as such, lower case characters should be used.
- Sample input, for finding the 50 words that have the highest proportion (p) of the characters "a", "b", and "c".
    ```
    java CharacterWordFinder
    p
    abc
    50
    ```
