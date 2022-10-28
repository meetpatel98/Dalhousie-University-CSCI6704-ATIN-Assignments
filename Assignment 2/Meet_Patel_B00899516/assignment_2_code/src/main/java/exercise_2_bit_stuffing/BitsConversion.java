package exercise_2_bit_stuffing;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Meet Patel (B00899516 | mt631537@dal.ca)
 */

public class BitsConversion {
    private static final Map<Character, String> HEXADECIMAL_TO_BINARY = new HashMap<>()
    {{
        put('0', "0000");
        put('1', "0001");
        put('2', "0010");
        put('3', "0011");
        put('4', "0100");
        put('5', "0101");
        put('6', "0110");
        put('7', "0111");
        put('8', "1000");
        put('9', "1001");
        put('A', "1010");
        put('B', "1011");
        put('C', "1100");
        put('D', "1101");
        put('E', "1110");
        put('F', "1111");
    }};
    private static final Map<String, Character> BINARY_TO_HEXADECIMAL = new HashMap<>()
    {{
        put("0000", '0');
        put("0001", '1');
        put("0010", '2');
        put("0011", '3');
        put("0100", '4');
        put("0101", '5');
        put("0110", '6');
        put("0111", '7');
        put("1000", '8');
        put("1001", '9');
        put("1010", 'A');
        put("1011", 'B');
        put("1100", 'C');
        put("1101", 'D');
        put("1110", 'E');
        put("1111", 'F');
    }};

    public String convertBinaryToHexadecimal(String binaryInput) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < binaryInput.length(); i = i + 4) {
            String fourBit = binaryInput.substring(i, i + 4);
            sb.append(BINARY_TO_HEXADECIMAL.get(fourBit));
        }
        return sb.toString();
    }

    public String convertHexadecimalToBinary(String hexadecimalInput) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hexadecimalInput.toUpperCase().length(); i++) {
            sb.append(HEXADECIMAL_TO_BINARY.get(hexadecimalInput.toUpperCase().charAt(i)));
        }
        System.out.println("Conversion to binary: " + sb.toString());
        return sb.toString();
    }
}
