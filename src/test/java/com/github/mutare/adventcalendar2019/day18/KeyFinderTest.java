package com.github.mutare.adventcalendar2019.day18;

import com.github.mutare.adventcalendar2019.day17.FileViewInputSource;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class KeyFinderTest {
    @Test
    public void test1() throws IOException {
        assertEquals(8, new KeyFinder(new FileViewInputSource().getView("/day18/test")).getShortestPathSteps());
    }

    @Test
    public void test2() throws IOException {
        assertEquals(86, new KeyFinder(new FileViewInputSource().getView("/day18/test2")).getShortestPathSteps());
    }

    @Test
    public void test3() throws IOException {
        assertEquals(132, new KeyFinder(new FileViewInputSource().getView("/day18/test3")).getShortestPathSteps());
    }

    @Test
    public void test4() throws IOException {
        assertEquals(136, new KeyFinder(new FileViewInputSource().getView("/day18/test4")).getShortestPathSteps());
    }

    @Test
    public void test5() throws IOException {
        assertEquals(81, new KeyFinder(new FileViewInputSource().getView("/day18/test5")).getShortestPathSteps());
    }

    @Test
    public void one() throws IOException {
        assertEquals(5406, new KeyFinder(new FileViewInputSource().getView("/day18/data")).getShortestPathSteps());
    }

    @Test
    public void test6() throws IOException {
        assertEquals(8, new KeyFinder(new FileViewInputSource().getView("/day18/test6")).getShortestPathSteps());
    }

    @Test
    public void test7() throws IOException {
        assertEquals(24, new KeyFinder(new FileViewInputSource().getView("/day18/test7")).getShortestPathSteps());
    }

    @Test
    public void test8() throws IOException {
        assertEquals(32, new KeyFinder(new FileViewInputSource().getView("/day18/test8")).getShortestPathSteps());
    }

    @Test
    public void test9() throws IOException {
        assertEquals(72, new KeyFinder(new FileViewInputSource().getView("/day18/test9")).getShortestPathSteps());
    }

    @Test
    public void two() throws IOException {
        assertEquals(1938, new KeyFinder(new FileViewInputSource().getView("/day18/data2")).getShortestPathSteps());
    }
}
