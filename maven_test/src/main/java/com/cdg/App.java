package com.cdg;

import input.Reader;
import org.apache.commons.lang3.StringUtils;
import output.Writer;
import parser.Parser;
import parser.Utils;
import input.Reader;

import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle error
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;
import java.util.stream.Collectors;

import static input.Reader.logCount;

public class App {
    public static void main(String[] args) {
        Reader.fileReader();
        Parser.parse();
        Writer.fileWriter();
    }
}
