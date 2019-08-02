package com.plietnov.task.filter;

import java.io.File;

public abstract class MyFilter {

    private MyFilter nextFilter;

    public MyFilter(MyFilter nextFilter) {
        this.nextFilter = nextFilter;
    }

    public abstract boolean filter(File file);

    public boolean doFilter(File file) {
        if (this.filter(file)) {
            return nextFilter == null || nextFilter.doFilter(file);
        }
        return false;
    }
}