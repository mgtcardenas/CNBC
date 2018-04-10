# CNBC

This is a complex version of the Naive Bayes Classifier. It is intended to learn from any given classifications of text read from a csv file. After learning from said file, the CNBC will make a prediction of the type of content that resides in Test.txt based on what it learned from Learn.csv

Here is a small glossary of this programm:

* Classificator:Also known as this programm.
* Example:      Each instance of a unit with which the classificator is trained.
* Kin:          Also known as a classification, in this program 'positive' and 'negative' would be the kins. This name was chosen for its small size and for the sake of convenience.

In order for the classifier to work, we need the following elements (the ingredients):

* The total number of examples overall, i.e., no matter the kin [e]
* The total number of examples for each kin. [n]
* The total number of times a single word occurs in each kin overall. That means: If 'the' appears in 5 examples of the positive case, then this is the number that what we want. [nk]
* The size of the vocabulary [v]
