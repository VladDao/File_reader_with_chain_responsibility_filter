package com.plietnov.task.filter;

import java.io.File;

public class DefaultFilter extends MyFilter {

    public DefaultFilter() {
        super(null);
    }

    @Override
    public boolean filter(File file) {
        return file.isFile();
    }
}
