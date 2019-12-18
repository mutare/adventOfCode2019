package com.github.mutare.adventcalendar2019.day16;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FlawedFrequencyTransmissionTest {

    @Test
    public void test() {
        FlawedFrequencyTransmission flawedFrequencyTransmission = new FlawedFrequencyTransmission("12345678", 1);
        flawedFrequencyTransmission.decode(1);
        assertEquals("48226158", flawedFrequencyTransmission.getOutput());

        flawedFrequencyTransmission = new FlawedFrequencyTransmission("12345678", 1);
        flawedFrequencyTransmission.decode(2);
        assertEquals("34040438", flawedFrequencyTransmission.getOutput());

        flawedFrequencyTransmission = new FlawedFrequencyTransmission("12345678", 1);
        flawedFrequencyTransmission.decode(3);
        assertEquals("03415518", flawedFrequencyTransmission.getOutput());
    }

    @Test
    public void test2() {
        FlawedFrequencyTransmission flawedFrequencyTransmission = new FlawedFrequencyTransmission("80871224585914546619083218645595", 1);
        flawedFrequencyTransmission.decode(100);
        assertEquals("24176176", flawedFrequencyTransmission.getOutput().substring(0, 8));
    }

    @Test
    public void test3() {
        FlawedFrequencyTransmission flawedFrequencyTransmission = new FlawedFrequencyTransmission("19617804207202209144916044189917", 1);
        flawedFrequencyTransmission.decode(100);
        assertEquals("73745418", flawedFrequencyTransmission.getOutput().substring(0, 8));
    }

    @Test
    public void test4() {
        FlawedFrequencyTransmission flawedFrequencyTransmission = new FlawedFrequencyTransmission("69317163492948606335995924319873", 1);
        flawedFrequencyTransmission.decode(100);
        assertEquals("52432133", flawedFrequencyTransmission.getOutput().substring(0, 8));
    }

    @Test
    public void one() {
        FlawedFrequencyTransmission flawedFrequencyTransmission = new FlawedFrequencyTransmission("59719811742386712072322509550573967421647565332667367184388997335292349852954113343804787102604664096288440135472284308373326245877593956199225516071210882728614292871131765110416999817460140955856338830118060988497097324334962543389288979535054141495171461720836525090700092901849537843081841755954360811618153200442803197286399570023355821961989595705705045742262477597293974158696594795118783767300148414702347570064139665680516053143032825288231685962359393267461932384683218413483205671636464298057303588424278653449749781937014234119757220011471950196190313903906218080178644004164122665292870495547666700781057929319060171363468213087408071790", 1);
        flawedFrequencyTransmission.decode(100);
        assertEquals("30550349", flawedFrequencyTransmission.getOutput().substring(0, 8));
    }

    @Test
    public void test5() {
        FlawedFrequencyTransmission flawedFrequencyTransmission = new FlawedFrequencyTransmission("03036732577212944063491565474664", 10000);
        flawedFrequencyTransmission.decode(100);
        assertEquals("84462026", flawedFrequencyTransmission.getOutput());
    }

    @Test
    public void test6() {
        FlawedFrequencyTransmission flawedFrequencyTransmission = new FlawedFrequencyTransmission("02935109699940807407585447034323", 10000);
        flawedFrequencyTransmission.decode(100);
        assertEquals("78725270", flawedFrequencyTransmission.getOutput());
    }

    @Test
    public void test7() {
        FlawedFrequencyTransmission flawedFrequencyTransmission = new FlawedFrequencyTransmission("03081770884921959731165446850517", 10000);
        flawedFrequencyTransmission.decode(100);
        assertEquals("53553731", flawedFrequencyTransmission.getOutput());
    }

    @Test
    public void two() {
        FlawedFrequencyTransmission flawedFrequencyTransmission = new FlawedFrequencyTransmission("59719811742386712072322509550573967421647565332667367184388997335292349852954113343804787102604664096288440135472284308373326245877593956199225516071210882728614292871131765110416999817460140955856338830118060988497097324334962543389288979535054141495171461720836525090700092901849537843081841755954360811618153200442803197286399570023355821961989595705705045742262477597293974158696594795118783767300148414702347570064139665680516053143032825288231685962359393267461932384683218413483205671636464298057303588424278653449749781937014234119757220011471950196190313903906218080178644004164122665292870495547666700781057929319060171363468213087408071790", 10000);
        flawedFrequencyTransmission.decode(100);
        assertEquals("62938399", flawedFrequencyTransmission.getOutput());
    }
}
