package com.plietnov.task.filter;

import java.io.File;

public class SizeFilter extends MyFilter {

    private int start;
    private int end;

    public SizeFilter(MyFilter filter, int start, int end) {
        super(filter);
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean filter(File file) {
        long length = file.length();
        return length >= start && length <= end;
    }
}