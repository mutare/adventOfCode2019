package com.github.mutare.adventcalendar2019.day14;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class StoichiometryTest {

    @Test
    public void test() throws IOException {
        int ore = new NanoFactory(new FileReactionsInputSource().getReactions("/day14/test")).findOreQuantity();
        assertEquals(31, ore);
    }

    @Test
    public void test2() throws IOException {
        int ore = new NanoFactory(new FileReactionsInputSource().getReactions("/day14/test2")).findOreQuantity();
        assertEquals(165, ore);
    }

    @Test
    public void test3() throws IOException {
        int ore = new NanoFactory(new FileReactionsInputSource().getReactions("/day14/test3")).findOreQuantity();
        assertEquals(13312, ore);
    }

    @Test
    public void test4() throws IOException {
        int ore = new NanoFactory(new FileReactionsInputSource().getReactions("/day14/test4")).findOreQuantity();
        assertEquals(180697, ore);
    }

    @Test
    public void test5() throws IOException {
        int ore = new NanoFactory(new FileReactionsInputSource().getReactions("/day14/test5")).findOreQuantity();
        assertEquals(2210736, ore);
    }

    @Test
    public void one() throws IOException {
        int ore = new NanoFactory(new FileReactionsInputSource().getReactions("/day14/data")).findOreQuantity();
        assertEquals(628586, ore);
    }


}
