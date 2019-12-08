package com.github.mutare.adventcalendar2019.day6;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(BlockJUnit4ClassRunner.class)
public class OrbitingObjectTest {
    @Test
    public void test() {
        OrbitingObject xxx0 = new OrbitingObject("xxx");
        OrbitingObject xxx1 = new OrbitingObject("xxx");

        assertEquals(xxx0.hashCode(), xxx1.hashCode());
        assertEquals(xxx0, xxx1);

        Set<OrbitingObject> orbitingObjects = new HashSet<>();
        orbitingObjects.add(xxx0);
        orbitingObjects.add(xxx1);
        assertEquals(1, orbitingObjects.size());
    }


    @Test
    public void test0() {
        String xxx0 = "xxx";
        String xxx1 = "xxx";

        assertEquals(xxx0.hashCode(), xxx1.hashCode());
        assertEquals(xxx0, xxx1);

        Set<String> stringObjects = new HashSet<>();
        stringObjects.add(xxx0);
        stringObjects.add(xxx1);
        assertEquals(1, stringObjects.size());
    }

}
