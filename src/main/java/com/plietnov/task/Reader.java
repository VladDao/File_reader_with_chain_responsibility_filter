package com.plietnov.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {

    public String read() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bf.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
