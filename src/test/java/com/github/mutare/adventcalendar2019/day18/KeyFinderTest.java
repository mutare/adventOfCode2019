package com.github.mutare.adventcalendar2019.day18;

import com.github.mutare.adventcalendar2019.day17.FileViewInputSource;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class KeyFinderTest {
    @Test
    public void test() throws IOException, InterruptedException {
        KeyFinder keyFinder = new KeyFinder(new FileViewInputSource().getView("/day18/test"));
        assertEquals(8, keyFinder.getShortestPathSteps());
    }

    @Test
    public void test2() throws IOException, InterruptedException {
        KeyFinder keyFinder = new KeyFinder(new FileViewInputSource().getView("/day18/test2"));
        assertEquals(86, keyFinder.getShortestPathSteps());
    }


    @Test
    public void test3() throws IOException, InterruptedException {
        KeyFinder keyFinder = new KeyFinder(new FileViewInputSource().getView("/day18/test3"));
        assertEquals(132, keyFinder.getShortestPathSteps());
    }


    @Test
    public void test4() throws IOException, InterruptedException {
        KeyFinder keyFinder = new KeyFinder(new FileViewInputSource().getView("/day18/test4"));
        //assertEquals(136, keyFinder.getShortestPathSteps());
    }

    @Test
    public void test5() throws IOException, InterruptedException {
        KeyFinder keyFinder = new KeyFinder(new FileViewInputSource().getView("/day18/test5"));
        //assertEquals(81, keyFinder.getShortestPathSteps());
    }


    @Test
    public void one() throws IOException, InterruptedException {
        KeyFinder keyFinder = new KeyFinder(new FileViewInputSource().getView("/day18/data"));
        //assertEquals(-1, keyFinder.getShortestPathSteps());
    }

}
