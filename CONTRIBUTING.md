Readability
Code needs to be readable to both you and a knowledgeable third party. This involves:
Using indentation consistently (e.g., every function's body is indented to the same level)

Adding whitespace (blank lines, spaces) where appropriate to help separate distinct parts of the code

Giving variables meaningful names. Variables named A, B, and C or foo, bar, and baz give the reader
no information whatsoever about their purpose or what information they may hold. Names
like principal, maximum, and counter are much more useful. Loop variables are a common
exception to this idea, and loop variables named i, j, etc. are okay.

The code should be well organized. Fields, constructors, and methods should be grouped appropriately
and given meaningful names.

Documentation
Every file containing code should start with a header comment. At the very least, this header should contain
the name of the file, a description of what the included code does, and the name of its author (you).
All code should also be well-commented. This requires striking a balance between commenting everything,
which adds a great deal of unneeded noise to the code, and commenting nothing, in which case the reader of
the code (or you, when you come back to it later) has no assistance in understanding the more complex or less
obvious sections of code. In general, aim to put a comment on any line of code that you might not understand
yourself if you came back to it in a month without having thought about it in the interim.

Code Efficiency
There are often many ways to write a particular program, and several of them are often poor choices. They
may be poor choices because they take many more lines of code (and thus your effort and time) than needed,
or they may take much more of the computer's time to execute than needed.
