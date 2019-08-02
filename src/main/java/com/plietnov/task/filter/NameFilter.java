package com.plietnov.task.filter;

import java.io.File;

public class NameFilter extends MyFilter {

    private String name;

    public NameFilter(MyFilter filter, String name) {
        super(filter);
        this.name = name;
    }

    @Override
    public boolean filter(File file) {
        return file.getName().contains(name);
    }
}
