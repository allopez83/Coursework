The focus of this lab is to introduce us to Ruby.

First make sure that Ruby is installed on your system.  For Linux and OSX users, it should be included already.  For Windows users, try http://rubyinstaller.org/.

Note that irb is the Ruby command for interactive mode, which can be useful for experimenting.

Download `eliza.rb` from the course website and extend it.
Note that if you call `ruby eliza.rb -test`, you will get some cases to consider.

If the patient says **always**, **never**, or something similar, Eliza should respond **CAN YOU BE MORE SPECIFIC?**

If the patient asks a question beginning with **Are you** or something similar, ELIZA should respond with **IS IT IMPORTANT IF I AM <original statement>**

Filter out words like **Well** or **Perhaps** from the beginning of the sentence.

Add an additional memory for Eliza to support **they**, following the pattern for **he** and **she**.

Finally, add one change of your own to make Eliza seem more human.

Submit your solution through Canvas.
