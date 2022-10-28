package exercise_2_bit_stuffing;

/**
 * @author Meet Patel (B00899516 | mt631537@dal.ca)
 */

public class BitsStuffingAndUnstuffing {
    public String bitStuffing(String binaryInput) {
        StringBuilder sb = new StringBuilder();
        int inputLength = binaryInput.length();
        int consecutive1s = 0;
        for (int i = 0; i < inputLength; i++) {
            if (binaryInput.charAt(i) == '1') {
                consecutive1s++;
                if (consecutive1s == 5) {
                    sb.append(binaryInput.charAt(i)).append(0);
                    consecutive1s = 0;
                } else {
                    sb.append(binaryInput.charAt(i));
                }
            } else {
                sb.append(binaryInput.charAt(i));
                consecutive1s = 0;
            }
        }
        System.out.println("After bit stuffing: " + sb.toString());
        return sb.toString();
    }
    public String bitUnStuffing(String binaryStuffedInput) {
        StringBuilder sb = new StringBuilder();
        int inputLength = binaryStuffedInput.length();
        int consecutive1sO = 0;
        for (int i = 0; i < inputLength; i++) {
            if (binaryStuffedInput.charAt(i) == '1') {
                consecutive1sO++;
                if (consecutive1sO == 5) {
                    sb.append(binaryStuffedInput.charAt(i));
                    i++;
                    consecutive1sO = 0;
                } else {
                    sb.append(binaryStuffedInput.charAt(i));
                }
            } else {
                sb.append(binaryStuffedInput.charAt(i));
                consecutive1sO = 0;
            }
        }
        System.out.println("After bit unstuffing: " + sb.toString());
        return sb.toString();
    }
}
