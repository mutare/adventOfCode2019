package com.github.mutare.adventcalendar2019.day23;

import com.github.mutare.adventcalendar2019.day2.IntcodeComputer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class NetworkComputer {
    private IntcodeComputer computer;
    private Deque<Packet> input = new LinkedList<>();
    private Deque<Packet> output = new LinkedList<>();
    private Deque<Long> buffer = new LinkedList<>();

    NetworkComputer(long[] program) {
        computer = new IntcodeComputer(program);
    }

    public void setAddress(long address) {
        result(computer.proccess(address));
    }

    private IntcodeComputer.Result result(IntcodeComputer.Result result) {
        if (result.type == IntcodeComputer.Result.Type.OUTPUT) {
            buffer.add(result.value);
        }
        if (buffer.size() >= 3) {
            output.add(new Packet(buffer.poll(), buffer.poll(), buffer.poll()));
        }
        return result;
    }

    public Deque<Packet> go() {
        do {
            while (result(this.computer.proccess()).type != IntcodeComputer.Result.Type.INPUT);
            if (!input.isEmpty()) {
                Packet received = input.poll();
                result(computer.proccess(received.x));
                result(computer.proccess(received.y));
            } else {
                result(computer.proccess(-1L));
            }

        } while (!input.isEmpty());
        return output;
    }

    public void addInput(Packet packet) {
        input.add(packet);
    }
}
