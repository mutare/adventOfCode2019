package com.github.mutare.adventcalendar2019.day13;

public interface Game {
    void setDelay(int value);

    void next();

    void next(int input);

    int getParameter(int no);
}
