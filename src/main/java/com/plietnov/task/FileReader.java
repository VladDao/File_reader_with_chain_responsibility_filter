package com.plietnov.task;

import com.plietnov.task.filter.DateFilter;
import com.plietnov.task.filter.DefaultFilter;
import com.plietnov.task.filter.ExtensionFilter;
import com.plietnov.task.filter.MyFilter;
import com.plietnov.task.filter.NameFilter;
import com.plietnov.task.filter.SizeFilter;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class FileReader {

    private static final String DATE_TIME_FORMAT_PATTERN = "dd-MM-yyyy HH:mm";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_PATTERN);
    private Set<String> allFiles = new HashSet<>();
    private Reader reader = new Reader();
    private MyFilter rootFilter = new DefaultFilter();

    public void start() {
        System.out.println("Please enter filepath");
        File directory = new File(reader.read());
        while (!directory.exists() && !directory.isDirectory()) {
            System.out.println("Please enter correct filepath, it mast be folder");
            directory = new File(reader.read());
        }
        showNameFilterDialog();
        showExtensionFilterDialog();
        showSizeFilterDialog();
        showDateFilterDialog();
        findAll(directory.listFiles());
        allFiles.forEach(System.out::println);
    }

    private void showNameFilterDialog() {
        System.out.println("Find by name?  1/0");
        String condition = reader.read();
        if (isInValidInput(condition)) {
            showNameFilterDialog();
        }
        if ("1".equals(condition)) {
            System.out.println("Please enter file name");
            rootFilter = new NameFilter(rootFilter, reader.read());
        }
    }

    private void showExtensionFilterDialog() {
        System.out.println("Find by extension?  1/0");
        String condition = reader.read();
        if (isInValidInput(condition)) {
            showExtensionFilterDialog();
        }
        if ("1".equals(condition)) {
            System.out.println("Please enter file extension");
            rootFilter = new ExtensionFilter(rootFilter, reader.read());
        }
    }

    private boolean isInValidInput(String condition) {
        return !("1".equals(condition) || "0".equals(condition));
    }

    private long parseDate() {
        return LocalDateTime.parse(reader.read(), formatter).toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    private int parseInt() {
        return Integer.parseInt(reader.read());
    }

    private void showSizeFilterDialog() {
        System.out.println("Find by size range?  1/0");
        String condition = reader.read();
        if (isInValidInput(condition)) {
            showSizeFilterDialog();
        }
        if ("1".equals(condition)) {
            System.out.println("Please enter start size");
            int start = parseInt();
            System.out.println("Please enter end size");
            rootFilter = new SizeFilter(rootFilter, start, parseInt());
        }
    }

    private void showDateFilterDialog() {
        System.out.println("Find by date range?  1/0");
        String condition = reader.read();
        if (isInValidInput(condition)) {
            showDateFilterDialog();
        }
        if ("1".equals(condition)) {
            System.out.println("Please enter start date (dd-MM-yyyy HH:mm)");
            long start = parseDate();
            System.out.println("Please enter end date (dd-MM-yyyy HH:mm)");
            rootFilter = new DateFilter(rootFilter, start, parseDate());
        }
    }

    private void findAll(File[] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                findAll(file.listFiles());
            }
            if (rootFilter.doFilter(file)) {
                allFiles.add(file.getPath());
            }
        }
    }
}