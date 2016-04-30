# CPP

## Dir command
- Should give output similar to `dir` command
- Format stuff, have things line up
    - i.e. directory tag, file size, etc.
- Ability to sort things according to parameter: timestamp, file size, etc.
- Size in just bytes, not kb

## Compare command
- Masking character: ignore the same position in the second file
- Ex: `shell compare f1 f2 @`, wherever @ occurs in f1, that same position is skipped in f2
- Output: `files compare OK`, `Files are different sizes`, or `Files are different sizes`

# Java

## GUI
- Goal is to replicate windows explorer
- Sorting capabilities
- List files and go into subdirectory
- CPP side only allows sorting in one direction, just read down to top to sort other way

## C/C++ Headers
- Wrap system calls and commands such as `FindNextFile` so it can be called from Java using `System.loadLibrary($DLL_Name)`
- Note that pointers are used in the DLL, so it needs to be "flattened" for Java to use
