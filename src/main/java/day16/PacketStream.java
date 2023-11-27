package day16;

import java.math.BigInteger;

public class PacketStream {

    private final String sourceString;

    private int bitPosition = 0;

    public PacketStream(String sourceString) {
//        System.out.println(sourceString);
        this.sourceString = sourceString;
    }

    public int getPosition() {
        return this.bitPosition;
    }

    public long readVersion() {
        return readBitsAsInt(3);
    }

    public long readTypeId() {
        return readBitsAsInt(3);
    }

    public long readLiteralValue() {
        String valueText = "";
        boolean isLastNumber;
        do {
            isLastNumber = readBit() == 1;
            valueText += readBitsAsString(4);
        } while (isLastNumber);

        return Long.parseLong(new BigInteger(valueText, 2).toString(10));
    }

    private String readBitsAsString(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += readBit();
        }
        return result;
    }

    public long readLengthTypeId() {
        return readBitsAsInt(1);
    }

    public long readLength() {
        return readBitsAsInt(15);
    }

    public long readSubPacketCount() {
        return readBitsAsInt(11);
    }

    private long readBitsAsInt(int length) {
        long result = 0;
        for (int i=0; i<length; i++) {
            result = (result*2) + readBit();
        }
//        System.out.print(" ");
        return result;
    }

    private int readBit() {
        int charPos = bitPosition / 4;
        int charBitPos = bitPosition % 4;
        int bitValue = switch (charBitPos) {
            case 0 -> 8;
            case 1 -> 4;
            case 2 -> 2;
            case 3 -> 1;
            default -> 0;
        };
        bitPosition++;
        int result = (hexToInt(sourceString.charAt(charPos)) & bitValue) > 0 ? 1 : 0;
//        System.out.print(result);
        return result;
    }

    private int hexToInt(char c) {
        return switch (c) {
            case '1' -> 1;
            case '2' -> 2;
            case '3' -> 3;
            case '4' -> 4;
            case '5' -> 5;
            case '6' -> 6;
            case '7' -> 7;
            case '8' -> 8;
            case '9' -> 9;
            case 'A' -> 10;
            case 'B' -> 11;
            case 'C' -> 12;
            case 'D' -> 13;
            case 'E' -> 14;
            case 'F' -> 15;
            default -> 0;
        };
    }
}
