package com.plietnov.task.filter;

import java.io.File;

public class DateFilter extends MyFilter {

    private Long start;
    private Long end;

    public DateFilter(MyFilter filter, Long start, Long end) {
        super(filter);
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean filter(File file) {
        long lastMod = file.lastModified();
        return lastMod >= start && lastMod <= end;
    }
}