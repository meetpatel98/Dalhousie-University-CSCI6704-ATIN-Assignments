package question_1_crc;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CRCComputation {
    public CRCSender senderSideComputing(String Mx, String Gx) {
        StringBuilder subResult = new StringBuilder();
        StringBuilder quotient = new StringBuilder();
        String MDashX = Mx + String.valueOf((Character) '0').repeat(Math.max(0, Gx.length() - 1));
        String zeros = String.valueOf((Character) '0').repeat(Gx.length());
        String subMDashX = MDashX.substring(0, Gx.length());
        quotient.append((Character) '1');

        for (int i = 0; i < Gx.length(); i++) {
            subResult.append((subMDashX.charAt(i) ^ Gx.charAt(i)));
        }
        sendersSubResultComputation(Gx, subResult, quotient, MDashX, zeros, subMDashX);
        subResult.replace(0, 1, "");
        String remainder = subResult.toString();
        String Px = Mx + remainder;
        return new CRCSender(Mx, Gx,MDashX, quotient.toString(), remainder, Px);
    }

    private static void sendersSubResultComputation(String Gx, StringBuilder subResult, StringBuilder quotient, String MDashX, String zeros, String subMDashX) {
        for (int i = subMDashX.length(); i < MDashX.length(); i++) {
            char subResultFirstBit = subResult.replace(0, 1, "").append(MDashX.charAt(i)).toString().charAt(0);
            if (subResultFirstBit == '1') {
                quotient.append((Character) '1');
                String intermediateComputation = subResult.toString();
                subResult.setLength(0);
                for (int x = 0; x < Gx.length(); x++) {
                    int XOROperation = intermediateComputation.charAt(x) ^ Gx.charAt(x);
                    subResult.append(XOROperation);
                }
            } else if (subResultFirstBit == '0') {
                quotient.append((Character) '0');
                String intermediateComputation = subResult.toString();
                subResult.setLength(0);
                for (int x = 0; x < Gx.length(); x++) {
                    int XOROperation = intermediateComputation.charAt(x) ^ zeros.charAt(x);
                    subResult.append(XOROperation);
                }
            }
        }
    }

    public CRCReceiver receiverSideComputing(String Px, String Gx) {
        String message;
        StringBuilder quotient = new StringBuilder();
        String zeros = String.valueOf((Character) '0').repeat(Gx.length());
        String subMDashX = Px.substring(0, Gx.length());
        quotient.append((Character) '1');
        StringBuilder subResult = new StringBuilder();

        for (int x = 0; x < Gx.length(); x++) {
            subResult.append((subMDashX.charAt(x) ^ Gx.charAt(x)));
        }
        receiverSubResultComputation(Px, Gx, quotient, zeros, subMDashX, subResult);
        String remainder = subResult.replace(0, 1, "").toString();

        if (Long.parseLong(remainder) == 0) {
            message = "Hurreh! Received message is error free";
        } else {
            message = "Ohh no! Received message contains error";
        }
        return new CRCReceiver(Px, Gx, remainder, quotient.toString(), message);
    }

    private static void receiverSubResultComputation(String Px, String Gx, StringBuilder quotient, String zeros, String subMDashX, StringBuilder subResult) {
        for (int i = subMDashX.length(); i < Px.length(); i++) {
            char subResultFirstBit = subResult.replace(0, 1, "").append(Px.charAt(i)).toString().charAt(0);
            if (subResultFirstBit == '1') {
                quotient.append((Character) '1');
                String intermediateComputation = subResult.toString();
                subResult.setLength(0);
                for (int j = 0; j < Gx.length(); j++) {
                    int XOROperation = intermediateComputation.charAt(j) ^ Gx.charAt(j);
                    subResult.append((XOROperation));
                }
            } else if (subResultFirstBit == '0') {
                quotient.append((Character) '0');
                String intermediateComputation = subResult.toString();
                subResult.setLength(0);
                for (int j = 0; j < Gx.length(); j++) {
                    int XOROperation = (intermediateComputation.charAt(j) ^ zeros.charAt(j));
                    subResult.append((XOROperation));
                }
            }
        }
    }
    public String introduceRandomError(String Px) {
        int min = 1;
        int max = Px.length() - 1;
        int x = ThreadLocalRandom.current().nextInt(min, max + 1);
        return Px.substring(0, x) + ((Px.charAt(x) == '0') ? '1' : '0' ) + Px.substring(x + 1);
    }
}
