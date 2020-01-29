package com.github.mutare.adventcalendar2019.day23;

import java.util.*;

import static java.lang.String.format;

public class NIC {
    List<NetworkComputer> network = new ArrayList<>();
    NAT nat;
    Long lastY;

    NIC(long[] program) {
        for (long i = 0 ; i < 50 ; i++) {
            NetworkComputer computer = new NetworkComputer(program);
            computer.setAddress(i);
            network.add(computer);
        }
        nat = new NAT(network);
    }

    long run(int part) {
        while(true) {
            for (long i = 0; i < 50; i++) {
                NetworkComputer computer = network.get((int) i);
                Deque<Packet> output = computer.go();
                if (output.size() > 0) {
                    Packet packet = output.poll();
                    if (packet.address == 255) {
                        if (part == 1) {
                            return packet.y;
                        } else {
                            nat.setPacket(packet);
                        }
                    } else {
                        network.get((int) packet.address).addInput(packet);
                    }
                    System.out.println(format("%d : (X=%d, Y=%d)", packet.address, packet.x, packet.y));
                }
            }
            if (nat.isIdle()) {
                if (lastY != null && nat.packet.y == lastY) {
                    return lastY;
                }
                network.get(0).addInput(nat.packet);
                lastY = nat.packet.y;
            }
        }
    }
}
