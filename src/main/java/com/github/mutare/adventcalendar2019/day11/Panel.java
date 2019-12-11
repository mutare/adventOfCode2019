package com.github.mutare.adventcalendar2019.day11;

class Panel {
    private int x;
    private int y;

    Panel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + x;
        hash = hash * 31 + y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Panel)) {
            return false;
        }
        return this.x == ((Panel) obj).x && this.y == ((Panel) obj).y;
    }

}
