package day16;

import common.Ansi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

public class Packet {
    private final long version;
    private final long typeId;

    private final long value;
    private List<Packet> subPackets = new ArrayList<>();

    public Packet(PacketStream packetStream) {
//        System.out.println();
        this.version = packetStream.readVersion();
        this.typeId = packetStream.readTypeId();

        if (typeId == 4) {
            this.value = packetStream.readLiteralValue();
        }
        else {
            this.value = 0;
            long lengthTypeId = packetStream.readLengthTypeId();
            if (lengthTypeId == 0) {
                long totalLength = packetStream.readLength();
                while (totalLength > 0) {
                    int subPacketStartPosition = packetStream.getPosition();
                    Packet subPacket = new Packet(packetStream);
                    subPackets.add(subPacket);
                    totalLength -= (packetStream.getPosition() - subPacketStartPosition);
                }
            }
            else {
                long subPacketCount = packetStream.readSubPacketCount();
                for (int i=0; i<subPacketCount; i++) {
                    subPackets.add(new Packet(packetStream));
                }
            }
        }

        //        System.out.println();
    }

    public long totalVersions() {
        long versionTotal = version;

        versionTotal += subPackets.stream().map(Packet::totalVersions).mapToLong(Long::longValue).sum();

        return versionTotal;
    }

    public long calculatedValue() {
        LongStream valueStream = subPackets.stream().mapToLong(Packet::calculatedValue);

        return switch ((int) typeId) {
            case 0 -> valueStream.sum();
            case 1 -> valueStream.reduce(1, (x,y) -> x * y);
            case 2 -> valueStream.min().orElseThrow();
            case 3 -> valueStream.max().orElseThrow();
            case 5 -> subPackets.get(0).calculatedValue() > subPackets.get(1).calculatedValue() ? 1 : 0;
            case 6 -> subPackets.get(0).calculatedValue() < subPackets.get(1).calculatedValue() ? 1 : 0;
            case 7 -> subPackets.get(0).calculatedValue() == subPackets.get(1).calculatedValue() ? 1 : 0;

            default -> value;
        };

    }

    public void printStructure(int level) {
        String leftPad = String.format("%1$" + (level*2) + "s", " ");
        System.out.println(leftPad + Ansi.ANSI_GREEN + "Version: " + version + ", TypeId: " + typeId + " - " + typeIdText(typeId) + Ansi.ANSI_RESET);
        if (typeId == 4)
            System.out.println(leftPad + "* Value: " + value);
        System.out.println(leftPad + "* Calculated Value: " + calculatedValue());
        System.out.println(leftPad + "* Sub packets: " + subPackets.size());

        subPackets.forEach(sp -> sp.printStructure(level+1));
    }

    private String typeIdText(long typeId) {
        return switch ((int) typeId) {
            case 0 -> "SUM";
            case 1 -> "PRODUCT";
            case 2 -> "MIN";
            case 3 -> "MAX";
            case 4 -> "VALUE";
            case 5 -> "GREATER THAN";
            case 6 -> "SMALLER THAN";
            case 7 -> "EQUAL TO";

            default -> "UNKNOWN";
        };
    }
}
