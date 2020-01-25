package com.github.mutare.adventcalendar2019.day20;

import com.github.mutare.adventcalendar2019.day17.FileViewInputSource;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PortalDungeonTest {
    @Test
    public void test1() throws IOException {
        PortalDungeon portalDungeon = new PortalDungeon(new FileViewInputSource().getView("/day20/test1"));
        assertEquals(23, portalDungeon.findShortestPath(false));
    }

    @Test
    public void test2() throws IOException {
        PortalDungeon portalDungeon = new PortalDungeon(new FileViewInputSource().getView("/day20/test2"));
        assertEquals(58, portalDungeon.findShortestPath(false));
    }

    @Test
    public void one() throws IOException {
        PortalDungeon portalDungeon = new PortalDungeon(new FileViewInputSource().getView("/day20/data"));
        assertEquals(484, portalDungeon.findShortestPath(false));
    }

    @Test
    public void test3() throws IOException {
        PortalDungeon portalDungeon = new PortalDungeon(new FileViewInputSource().getView("/day20/test3"));
        assertEquals(396, portalDungeon.findShortestPath(true));
    }

    @Test
    public void two() throws IOException {
        PortalDungeon portalDungeon = new PortalDungeon(new FileViewInputSource().getView("/day20/data"));
        assertEquals(5754, portalDungeon.findShortestPath(true));
    }
}
