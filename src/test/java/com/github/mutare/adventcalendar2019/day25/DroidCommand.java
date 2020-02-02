package com.github.mutare.adventcalendar2019.day25;

import com.github.mutare.adventcalendar2019.day15.FileProgramInputSource;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.String.format;

public class DroidCommand {
    public static void main(String[] args) throws IOException {
        Droid droid = new Droid(new FileProgramInputSource().getProgram("/day25/data"));
        while(true) {
            Scanner in = new Scanner(System.in);
            droid.command(in.nextLine());
            System.out.println(format("(%d, %d)"));
            System.out.println(droid.getOutput());
        }
    }
}
