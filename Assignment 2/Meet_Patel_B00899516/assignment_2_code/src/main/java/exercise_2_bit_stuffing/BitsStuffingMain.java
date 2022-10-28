package exercise_2_bit_stuffing;

/**
 * @author Meet Patel (B00899516 | mt631537@dal.ca)
 */

import java.util.Scanner;

public class BitsStuffingMain {
    public static void main(String[] args) {
        BitsConversion bitsConversion = new BitsConversion();
        BitsStuffingAndUnstuffing bitsStuffingAndUnstuffing = new BitsStuffingAndUnstuffing();

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter hexadecimal string.");
        String hexadecimalInput = scanner.nextLine();
        try {
            for (int i = 0; i < hexadecimalInput.toUpperCase().length(); ++i) {
                char currentHexadecimalInputChar = hexadecimalInput.toUpperCase().charAt(i);
                if ("0123456789ABCDEF".indexOf(currentHexadecimalInputChar) == -1) {
                    throw new Exception("Error: Invalid hexadecimal string!");
                }
            }
            System.out.println("Input: " + hexadecimalInput.toUpperCase());
            String hexadecimalToBinary = bitsConversion.convertHexadecimalToBinary(hexadecimalInput);
            String binaryInputStuffed = bitsStuffingAndUnstuffing.bitStuffing(hexadecimalToBinary);
            String binaryInputUnStuffed = bitsStuffingAndUnstuffing.bitUnStuffing(binaryInputStuffed);
            String hexadecimalOutput = bitsConversion.convertBinaryToHexadecimal(binaryInputUnStuffed);
            System.out.println("Output: " + hexadecimalOutput.toUpperCase());

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
