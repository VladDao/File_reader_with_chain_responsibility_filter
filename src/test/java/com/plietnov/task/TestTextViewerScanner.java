package com.plietnov.task;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class TestTextViewerScanner {
    private static final String TEST = "Test.txt";
    private TextViewerScanner textViewerScanner;

    @Before
    public void setUp() throws Exception {
        textViewerScanner = new TextViewerScanner(TEST);
    }

    @Test
    public void read_ReadByLine() {
        Iterator itr = textViewerScanner.iterator();
        System.out.println(itr.next() + "1");
        System.out.println(itr.next() + "2");
        System.out.println(itr.next() + "3");
        System.out.println(itr.next() + "4");
    }

    @Test
    public void read_ReadByWhile() {
        Iterator itr = textViewerScanner.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

    @Test
    public void read_ReadByFor() {
        textViewerScanner.forEach(System.out::println);
    }
}
