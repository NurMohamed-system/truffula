# Truffula Notes
As part of Wave 0, please fill out notes for each of the below files. They are in the order I recommend you go through them. A few bullet points for each file is enough. You don't need to have a perfect understanding of everything, but you should work to gain an idea of how the project is structured and what you'll need to implement. Note that there are programming techniques used here that we have not covered in class! You will need to do some light research around things like enums and and `java.io.File`.

PLEASE MAKE FREQUENT COMMITS AS YOU FILL OUT THIS FILE.

## App.java
Main entry point for the application

Likely handles command line arguments and initializes the TruffulaPrinter

coordinate the overall flow

## ConsoleColor.java
Enum that defines ANSI color codes for terminal output

Contains colors: BLACK, RED, GREEN, YELLOW, BLUE, PURPLE, CYAN, WHITE, RESET

Each color has an associated ANSI escape code

## ColorPrinter.java / ColorPrinterTest.java
Utility class for printing colored text to a PrintStream

Maintains current color state

Provides print() and println() methods with color control

## TruffulaOptions.java / TruffulaOptionsTest.java
Handles configuration options for directory tree display

Parses command line arguments in format: [-h] [-nc] path

Throws exceptions for invalid arguments or missing directory

## TruffulaPrinter.java / TruffulaPrinterTest.java
Main class that prints directory tree structure

Uses TruffulaOptions for configuration

Supports color cycling through directories

## AlphabeticalFileSorter.java
Utility class for sorting files alphabetically

Uses case-insensitive comparison of filenames

Implements sorting via Arrays.sort with lambda comparator