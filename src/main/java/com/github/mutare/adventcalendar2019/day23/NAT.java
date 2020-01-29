package com.github.mutare.adventcalendar2019.day23;

import java.util.List;

class NAT {
    private final List<NetworkComputer> computerList;
    Packet packet;

    NAT(List<NetworkComputer> computerList) {
        this.computerList = computerList;
    }


    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    public boolean isIdle() {
        for(NetworkComputer networkComputer : computerList) {
            if (!networkComputer.isIdle()) return false;
        }
        return true;
    }
}
