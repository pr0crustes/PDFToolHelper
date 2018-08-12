# PDFToolHelper
PDF Tool Helper is a aggregation of PDF utils in a GUI (made with JavaFX).  
I started this project because i was in need of something simple and powerful. 
Since i did not find any Free software, with a GUI, able to fulfill my needs, i created one.  

PDF Tool Helper is not more than a GUI (made with JavaFX) wrapper around pdfbox (pdfbox.apache.org).  
As said, this project makes use of pdfbox.  
  
Currently, Convert, Crop, Insert, Merge and Change Quality are supported operations.  

### What is RangeEx?
A few TextFields around the program expects a valid RangeEx as input, but what is it?  
  
RangeEx, short of Range Expression, is a simple expression system, by me designed, 
that represents a finite amount of numbers.  
  
##### RangeEx has 3 basics operations that can be concatenated:  

### UNDERSCORE ('_')  
An underscore represents an interval of numbers, going from the number on the left to the number on the right of the underscore.  
Generalizing, an input "X_Y", with X and Y being positive integers and X < Y, will result in all the numbers from X to Y, including Y.  
Should always be used between two numbers.  
  
Examples:  
"1_4" will result in [1, 2, 3, 4].  
"10_15" will result in [10, 11, 12, 13, 14, 15].  
"_6" or "3_" will result in an exception.  
  
### PLUS ('+')  
A plus represents an addition of the number that follows it.  
Generalizing, an input "+X", with X being a positive integer, will result in an interval with only the value X.  
Every plus signal should be followed by a valid number.  
  
Examples:  
"+1" will result in [1].  
"+7" will result in [7].  
"+" or "++0" will result in an exception.  
  
### MINUS ('-')  
A minus represents a subtraction of the number that follows it from a interval.  
Generalizing, an input "-X", with X being a positive integer, will result in the current input interval but without X.  
Every minus signal should be followed by a valid number.
  
Example:  
"1_4-3" will result in [1, 2, 4].  
"5_7-6" will result in [5, 7].  

### More on Concatenation 
One or more spaces (" ") are a valid input to separate things.  
This way, "1_3 6_8" will result in [1, 2, 3, 6, 7, 8].  
The same result can also be achieved with "1_3+6_8".  
  
Keeping in mind the order of the operations is important:  
Every range (underscore) is evaluated first;  
Then every addition (plus) is added to the previous interval;  
Then every subtraction (minus) is removed from the interval. 
  
Because of this order, are valid unintuitive RangeEx:  
"+13 +10", the same as "+13+10", resulting in [10, 13].  
"-2 1_3", the same as "=2+1_3", resulting in [1, 3].  
"-5 +5 +5 +3", the same as "-5+5+5+3", resulting in [3], since subtraction is the last thing that happens.  
"3_7 -4 +20 25_28 +50", the same as "3_7-4+20+25_28+50", resulting in [3, 5, 6, 7, 20, 25, 26, 27, 28, 50].  
  
Because of this, RangeEx allows the user to specifically select pages of a file, even not continuous ones.
