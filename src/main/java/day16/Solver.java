package day16;

import common.Input;

import java.io.IOException;
import java.util.List;

public class Solver {

    public long part1(String filepath) throws IOException {
        List<String> lines = Input.readLines(filepath);
        PacketStream packetStream = new PacketStream(lines.get(0));

        Packet packet = new Packet(packetStream);

        return packet.totalVersions();
    }

    public long part2(String filepathOrLiteralString) throws IOException {
        PacketStream packetStream;
        if (filepathOrLiteralString.endsWith(".txt"))
            packetStream = new PacketStream(Input.readLines(filepathOrLiteralString).get(0));
        else
            packetStream = new PacketStream(filepathOrLiteralString);

        Packet packet = new Packet(packetStream);
//        packet.printStructure(1);

        return packet.calculatedValue();
    }
}
