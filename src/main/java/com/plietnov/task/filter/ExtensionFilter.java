package com.plietnov.task.filter;

import java.io.File;

public class ExtensionFilter extends MyFilter {

    private String extension;

    public ExtensionFilter(MyFilter filter, String extension) {
        super(filter);
        this.extension = extension;
    }

    @Override
    public boolean filter(File file) {
        return file.getName().endsWith(extension.toLowerCase());
    }
}