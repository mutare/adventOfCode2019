package com.github.mutare.adventcalendar2019.day14;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class StoichiometryTest {

    @Test
    public void test() throws IOException {
        long ore = new NanoFactory(new FileReactionsInputSource().getReactions("/day14/test")).findOreQuantity();
        assertEquals(31, ore);
    }

    @Test
    public void test2() throws IOException {
        long ore = new NanoFactory(new FileReactionsInputSource().getReactions("/day14/test2")).findOreQuantity();
        assertEquals(165, ore);
    }

    @Test
    public void test3() throws IOException {
        long ore = new NanoFactory(new FileReactionsInputSource().getReactions("/day14/test3")).findOreQuantity();
        assertEquals(13312, ore);
    }

    @Test
    public void test4() throws IOException {
        long ore = new NanoFactory(new FileReactionsInputSource().getReactions("/day14/test4")).findOreQuantity();
        assertEquals(180697, ore);
    }

    @Test
    public void test5() throws IOException {
        long ore = new NanoFactory(new FileReactionsInputSource().getReactions("/day14/test5")).findOreQuantity();
        assertEquals(2210736, ore);
    }

    @Test
    public void one() throws IOException {
        long ore = new NanoFactory(new FileReactionsInputSource().getReactions("/day14/data")).findOreQuantity();
        assertEquals(628586, ore);
    }


    @Test
    public void test3_2() throws IOException {
        assertEquals(82892753, new NanoFactory(new FileReactionsInputSource().getReactions("/day14/test3")).findMaxFuel(1000000000000L));
    }

    @Test
    public void test4_2() throws IOException {
        assertEquals(5586022, new NanoFactory(new FileReactionsInputSource().getReactions("/day14/test4")).findMaxFuel(1000000000000L));
    }

    @Test
    public void test5_2() throws IOException {
        assertEquals(460664, new NanoFactory(new FileReactionsInputSource().getReactions("/day14/test5")).findMaxFuel(1000000000000L));
    }

    @Test
    public void two() throws IOException {
        assertEquals(3209254, new NanoFactory(new FileReactionsInputSource().getReactions("/day14/data")).findMaxFuel(1000000000000L));
    }

}
