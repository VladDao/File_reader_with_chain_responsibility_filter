package com.plietnov.task;

import com.plietnov.task.filter.DateFilter;
import com.plietnov.task.filter.DefaultFilter;
import com.plietnov.task.filter.ExtensionFilter;
import com.plietnov.task.filter.MyFilter;
import com.plietnov.task.filter.NameFilter;
import com.plietnov.task.filter.SizeFilter;
import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestFilters {

    private static final String DATE_TIME_FORMAT_PATTERN = "dd-MM-yyyy HH:mm";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_PATTERN);
    private MyFilter df = new DefaultFilter();
    private long startDate = LocalDateTime.parse("10-10-2010 00:00", formatter).toInstant(ZoneOffset.UTC).toEpochMilli();
    private long modDate = LocalDateTime.parse("10-10-2015 00:00", formatter).toInstant(ZoneOffset.UTC).toEpochMilli();
    private long modDateFake = LocalDateTime.parse("10-10-2000 00:00", formatter).toInstant(ZoneOffset.UTC).toEpochMilli();
    private long endDate = LocalDateTime.parse("10-10-2020 00:00", formatter).toInstant(ZoneOffset.UTC).toEpochMilli();

    @Test
    public void nameFilter_FilterByName_ShouldBeTrue() {
        File file = new File("Test.txt");
        NameFilter nf = new NameFilter(df, "Test");
        assertTrue(nf.doFilter(file));
    }

    @Test
    public void nameFilter_FilterByName_ShouldBeFalse() {
        File file = new File("Test.txt");
        NameFilter nf = new NameFilter(df, "undef");
        assertFalse(nf.doFilter(file));
    }

    @Test
    public void extension_FilterByExtension_ShouldBeTrue() {
        File file = new File("Test.txt");
        ExtensionFilter nf = new ExtensionFilter(df, "txt");
        assertTrue(nf.doFilter(file));
    }

    @Test
    public void extension_FilterByExtension_ShouldBeFalse() {
        File file = new File("Test.txt");
        ExtensionFilter nf = new ExtensionFilter(df, "rar");
        assertFalse(nf.doFilter(file));
    }

    @Test
    public void lastMod_testFilterLastModInRange_ShouldBeFalse() {
        File file = new File("Test.txt");
        file.setLastModified(modDateFake);
        DateFilter nf = new DateFilter(df, startDate, endDate);
        assertFalse(nf.doFilter(file));
    }

    @Test
    public void lastMod_testFilterLastModInRange_ShouldBeTrue() {
        File file = new File("Test.txt");
        file.setLastModified(modDate);
        DateFilter nf = new DateFilter(df, startDate, endDate);
        assertTrue(nf.doFilter(file));
    }

    @Test
    public void sizeFilter_FilterBySize_ShouldBeTrue() {
        File file = new File("Test.txt");
        SizeFilter nf = new SizeFilter(df, 10, 5000);
        assertTrue(nf.doFilter(file));
    }

    @Test
    public void sizeFilter_FilterBySize_ShouldBeFalse() {
        File file = new File("Test.txt");
        SizeFilter nf = new SizeFilter(df, 10, 15);
        assertFalse(nf.doFilter(file));
    }
}
