package com.github.mutare.adventcalendar2019.day18;

import com.github.mutare.adventcalendar2019.day17.FileViewInputSource;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class KeyFinderTest {
    @Test
    public void test() throws IOException, InterruptedException {
        KeyFinder2 keyFinder = new KeyFinder2(new FileViewInputSource().getView("/day18/test"));
        assertEquals(8, keyFinder.getShortestPathSteps());
    }

    @Test
    public void test2() throws IOException, InterruptedException {
        KeyFinder2 keyFinder = new KeyFinder2(new FileViewInputSource().getView("/day18/test2"));
        assertEquals(86, keyFinder.getShortestPathSteps());
    }


    @Test
    public void test3() throws IOException, InterruptedException {
        KeyFinder2 keyFinder = new KeyFinder2(new FileViewInputSource().getView("/day18/test3"));
        assertEquals(132, keyFinder.getShortestPathSteps());
    }


    @Test
    public void test4() throws IOException, InterruptedException {
        KeyFinder2 keyFinder = new KeyFinder2(new FileViewInputSource().getView("/day18/test4"));
        assertEquals(136, keyFinder.getShortestPathSteps());
    }

    @Test
    public void test5() throws IOException, InterruptedException {
        KeyFinder2 keyFinder = new KeyFinder2(new FileViewInputSource().getView("/day18/test5"));
        assertEquals(81, keyFinder.getShortestPathSteps());
    }


    @Test
    public void one() throws IOException, InterruptedException {
        KeyFinder2 keyFinder = new KeyFinder2(new FileViewInputSource().getView("/day18/data"));
        assertEquals(5406, keyFinder.getShortestPathSteps());
    }

    @Test
    public void test6() throws IOException {
        KeyFinder3 keyFinder3 = new KeyFinder3(new FileViewInputSource().getView("/day18/test6"));
        assertEquals(8, keyFinder3.getShortestPathSteps());
    }

    @Test
    public void test7() throws IOException {
        KeyFinder3 keyFinder3 = new KeyFinder3(new FileViewInputSource().getView("/day18/test7"));
        assertEquals(24, keyFinder3.getShortestPathSteps());
    }

    @Test
    public void test8() throws IOException {
        KeyFinder3 keyFinder3 = new KeyFinder3(new FileViewInputSource().getView("/day18/test8"));
        assertEquals(32, keyFinder3.getShortestPathSteps());
    }

    @Test
    public void test9() throws IOException {
        KeyFinder3 keyFinder3 = new KeyFinder3(new FileViewInputSource().getView("/day18/test9"));
        assertEquals(72, keyFinder3.getShortestPathSteps());
    }

    @Test
    public void test10() throws IOException {
        KeyFinder4 keyFinder4 = new KeyFinder4(new FileViewInputSource().getView("/day18/test"));
        assertEquals(8, keyFinder4.getShortestPathSteps());
    }

    @Test
    public void test11() throws IOException {
        KeyFinder4 keyFinder4 = new KeyFinder4(new FileViewInputSource().getView("/day18/test2"));
        assertEquals(86, keyFinder4.getShortestPathSteps());
    }

    @Test
    public void test12() throws IOException {
        KeyFinder4 keyFinder4 = new KeyFinder4(new FileViewInputSource().getView("/day18/test3"));
        assertEquals(132, keyFinder4.getShortestPathSteps());
    }

    @Test
    public void test13() throws IOException {
        KeyFinder4 keyFinder4 = new KeyFinder4(new FileViewInputSource().getView("/day18/test4"));
        assertEquals(136, keyFinder4.getShortestPathSteps());
    }

    @Test
    public void test14() throws IOException {
        KeyFinder4 keyFinder4 = new KeyFinder4(new FileViewInputSource().getView("/day18/test5"));
        assertEquals(81, keyFinder4.getShortestPathSteps());
    }

    @Test
    public void test15() throws IOException {
        KeyFinder4 keyFinder4 = new KeyFinder4(new FileViewInputSource().getView("/day18/data"));
        assertEquals(5406, keyFinder4.getShortestPathSteps());
    }

    @Test
    public void test16() throws IOException {
        KeyFinder4 keyFinder4 = new KeyFinder4(new FileViewInputSource().getView("/day18/test6"));
        assertEquals(8, keyFinder4.getShortestPathSteps());
    }

    @Test
    public void test17() throws IOException {
        KeyFinder4 keyFinder4 = new KeyFinder4(new FileViewInputSource().getView("/day18/test7"));
        assertEquals(24, keyFinder4.getShortestPathSteps());
    }

    @Test
    public void test18() throws IOException {
        KeyFinder4 keyFinder4 = new KeyFinder4(new FileViewInputSource().getView("/day18/test8"));
        assertEquals(32, keyFinder4.getShortestPathSteps());
    }

    @Test
    public void test19() throws IOException {
        KeyFinder4 keyFinder4 = new KeyFinder4(new FileViewInputSource().getView("/day18/test9"));
        assertEquals(72, keyFinder4.getShortestPathSteps());
    }

    @Test
    public void test20() throws IOException {
        KeyFinder4 keyFinder4 = new KeyFinder4(new FileViewInputSource().getView("/day18/data2"));
        assertEquals(1938, keyFinder4.getShortestPathSteps());
    }
}
