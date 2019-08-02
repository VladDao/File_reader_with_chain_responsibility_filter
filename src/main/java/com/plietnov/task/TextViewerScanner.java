package com.plietnov.task;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

public class TextViewerScanner implements Iterable<String> {

    private Scanner scanner;

    public TextViewerScanner(String fileName) throws FileNotFoundException {
        scanner = new Scanner(new FileReader(fileName));
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                boolean hasNextLine = scanner.hasNextLine();
                if (!hasNextLine) {
                    scanner.close();
                }
                return hasNextLine;
            }

            @Override
            public String next() {
                return scanner.nextLine();
            }
        };
    }
}