# Getting Started

## Reference Documentation

This program will merge a given set of intervals to a unique, non overlapping set of intervals.

Example:
```sh
gradlew bootRun --args "[25,30] [2,19] [14, 23] [4,8]" 
 
> [2,23][25,30]
```
The result will be ordered.

## Requirements

You need to have Java 11 installed on your machine and set the Path variable JAVA_HOME to your java directory.

## Usage

Pull the repository, cd into the folder, and execute following command:

Unix
```sh
gradlew bootRun --args "2,8"
```
Windows
```sh
./gradlew.bat bootRun --args "2,8"
```

### Arguments

An Interval is defined by 2 numbers, divided by a comma. 
Two Intervals are separated by a space 
```sh
gradlew bootRun --args "2,8 15,28"
```

You can also encapsulate an interval in brackets
```sh
gradlew bootRun --args "2,8 (15,28) [25,30]"
```

If you enter the numbers in the wrong order, the program will change the order before usage.
```sh
gradlew bootRun --args "8,2"

> [2,8]
```

## Limitations
- Honestly, I don't know how many Intervals can be used, nevertheless the maximum amount of Intervals can not exceed 2.147.483.647.
- You can not use floating point numbers.
- You can not use numbers > 2.147.483.647 or negative numbers.
- If an argument can not be parsed, it will be skipped.




