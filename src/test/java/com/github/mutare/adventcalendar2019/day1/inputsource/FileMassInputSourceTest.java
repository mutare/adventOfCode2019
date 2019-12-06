package com.github.mutare.adventcalendar2019.day1.inputsource;

import com.github.mutare.adventcalendar2019.day1.inputsource.MassInputSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.IOException;

@RunWith(BlockJUnit4ClassRunner.class)
public class FileMassInputSourceTest {
    private MassInputSource massInputSource = new FileMassInputSource();

    @Test
    public void test() throws IOException {
        massInputSource.getMasses()
                .forEach(System.out::println);
    }
}
