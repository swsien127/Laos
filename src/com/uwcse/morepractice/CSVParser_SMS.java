package com.uwcse.morepractice;
import java.io.*;
import java.util.*;

import android.util.Log;
import au.com.bytecode.opencsv.CSVReader;

public class CSVParser_SMS {
    private static final char DELIMITER = ',';
    private static final char QUOTE = '"';
    private static final int LINE_NUM = 1;
    private static final int NUM_COLUMNS = 2;
    
    private CSVReader reader;
    
    // the SMS answer to pictures[i] is answers[i]
    private String[] pictures;
    private String[] answers;
    
    // true if csv file has content
    private boolean hasContent;
    
    /**
     * @param path - absolute path of SMS Application folder
     * @param filename - absolute path of csv file in SMS Applicationfolder
     */
    public CSVParser_SMS(String path, String filename) {
        try {
            reader = new CSVReader(new FileReader(filename), DELIMITER, QUOTE, LINE_NUM);
        } catch (FileNotFoundException e) {
            reader = null;
            System.err.println("The file " + filename + " was not found.");
        }
        
        List<String[]> lines;
        try {
            lines = reader.readAll();
        } catch (IOException e) {
            lines = null;
            System.err.println("Unable to read the file " + filename);
        }
        
        int numPictures = lines.size();
        System.out.println("number of pictures in csv is " + numPictures);
        if (numPictures == 0)
            hasContent = false;
        else {
            hasContent = true;
            pictures = new String[numPictures];
            answers = new String[numPictures];
                    
            for (int i = 0; i < numPictures; i++) {
                String[] line = lines.get(i);
                if (line.length < 2) {
                    System.err.println("Error in row " + (i+1) + ": There must be two columns.");
                }
                
                pictures[i] = path + "/" + line[0];
                answers[i] = line[1];
            }
        }
    }
    
    public String[] getPictures() {
        return pictures;
    }
    
    public String[] getAnswers() {
        return answers;
    }
    
    public boolean hasContent() {
        return hasContent;
    }
    
}
