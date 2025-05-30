import java.io.File;
import java.io.PrintStream;

public class App {
  
  /**
   * Application for printing a directory tree
   * 
   * Options include:
   * - Whether to show hidden files.
   * - Whether to use colored output.
   * - The root directory from which to begin printing the tree.
   * 
   * Hidden files are identified by names that start with a dot (e.g., ".hidden.txt").
   * Color output is enabled by default, but can be disabled using flags.
   * 
   * Usage Example:
   * 
   * Arguments Format: [-h] [-nc] path
   * 
   * Flags:
   * - -h   : Show hidden files (defaults to false).
   * - -nc  : Do not use color (color is enabled by default).
   * 
   * Path:
   * - The absolute or relative path to the directory whose contents will be printed.
   * 
   * Behavior:
   * - If color is disabled, all text will be printed in white.
   * - The order of flags is unimportant.
   * - The path argument is mandatory.
   * 
   * Examples:
   * 
   * 1. ['-nc', '-h', '/path/to/directory']
   *    → Don't use color, do show hidden files.
   * 
   * 2. ['-h', '-nc', '/path/to/directory']
   *    → Don't use color, do show hidden files (order of flags is ignored).
   * 
   * 3. ['/path/to/directory']
   *    → Use color, don't show hidden files.
   * 
   * Error messages will be shown for illegal arguments or a not found file
   */

   // ANSI color codes
   private static final String RESET = "\u001B[0m";
   private static final String BLUE = "\u001B[34m";  // For directories
   private static final String GREEN = "\u001B[32m"; // For files
   
  public static void main(String[] args) throws Exception {
    /// Default settings
    boolean showHidden = false;
    boolean useColor = true;
    String path = null;

    // Parse command line arguments
    for (String arg : args) {
        if (arg.equals("-h")) {
            showHidden = true;
        } else if (arg.equals("-nc")) {
            useColor = false;
        } else if (!arg.startsWith("-")) {
            if (path != null) {
                System.err.println("Error: Multiple path arguments provided");
                printUsage();
                System.exit(1);
            }
            path = arg;
        } else {
            System.err.println("Error: Unknown option '" + arg + "'");
            printUsage();
            System.exit(1);
        }
    }

    // Validate path
    if (path == null) {
        System.err.println("Error: No directory path provided");
        printUsage();
        System.exit(1);
    }

    File root = new File(path);
    if (!root.exists()) {
        System.err.println("Error: Directory does not exist: " + path);
        System.exit(1);
    }

    // Print the directory tree
    printDirectoryTree(root, "", showHidden, useColor);
}

private static void printUsage() {
    System.err.println("\nUsage: java App [-h] [-nc] <directory>");
    System.err.println("Options:");
    System.err.println("  -h    Show hidden files");
    System.err.println("  -nc   Disable colored output");
}

private static void printDirectoryTree(File folder, String indent, boolean showHidden, boolean useColor) {
    File[] files = folder.listFiles();
    if (files == null) return;

    for (int i = 0; i < files.length; i++) {
        File file = files[i];
        
        // Skip hidden files if not showing them
        if (file.isHidden() && !showHidden) {
            continue;
        }

        // Print indentation and tree structure
        System.out.print(indent);
        System.out.print(i == files.length - 1 ? "└── " : "├── ");

        // Apply color if enabled
        if (useColor) {
            System.out.print(file.isDirectory() ? BLUE : GREEN);
            System.out.print(file.getName());
            System.out.print(RESET);
        } else {
            System.out.print(file.getName());
        }
        System.out.println();

        // Recursively print subdirectories
        if (file.isDirectory()) {
            String newIndent = indent + (i == files.length - 1 ? "    " : "│   ");
            printDirectoryTree(file, newIndent, showHidden, useColor);
        }
    }
  }
}
