package com.github.mutare.adventcalendar2019.day17;

import com.github.mutare.adventcalendar2019.day15.FileProgramInputSource;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SetAndForgetTest {
    @Test
    public void test() throws IOException {
        ASCII ascii = new ASCII(new FileViewInputSource().getView("/day17/test"));
        ascii.markIntersections();
        ascii.printView(System.out);
        assertEquals(76, ascii.getIntersectionsAlignmentSum());
    }

    @Test
    public void one() throws IOException {
        ASCII ascii = new ASCII(new FileProgramInputSource().getProgram("/day17/data"));
        ascii.run(false);
        ascii.markIntersections();
        ascii.printView(System.out);
        assertEquals(14332, ascii.getIntersectionsAlignmentSum());
    }

    @Test
    public void two() throws IOException {
        ASCII ascii = new ASCII(new FileProgramInputSource().getProgram("/day17/data"));
        ascii.run(true);
        assertEquals(1034009, ascii.getOutputLongValue());
    }

}
