package com.github.mutare.adventcalendar2019.day20;

import com.github.mutare.adventcalendar2019.day17.FileViewInputSource;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PortalDungeonTest {
    @Test
    public void test1() throws IOException {
        PortalDungeon portalDungeon = new PortalDungeon(new FileViewInputSource().getView("/day20/test1"));
        assertEquals(23, portalDungeon.findShortestPath());
    }

    @Test
    public void test2() throws IOException {
        PortalDungeon portalDungeon = new PortalDungeon(new FileViewInputSource().getView("/day20/test2"));
        assertEquals(58, portalDungeon.findShortestPath());
    }

    @Test
    public void one() throws IOException {
        PortalDungeon portalDungeon = new PortalDungeon(new FileViewInputSource().getView("/day20/data"));
        assertEquals(484, portalDungeon.findShortestPath());
    }
}
