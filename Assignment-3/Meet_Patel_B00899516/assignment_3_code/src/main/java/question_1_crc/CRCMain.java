package question_1_crc;

import java.util.Scanner;

public class CRCMain {
    public static void main(String[] args) {
        CRCComputation crcComputation = new CRCComputation();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter M(x)");
        String Mx = scanner.nextLine();

        System.out.println("Enter G(x)");
        String Gx = scanner.nextLine();

        CRCSender crcSender = crcComputation.senderSideComputing(Mx, Gx);
        CRCReceiver crcReceiver = crcComputation.receiverSideComputing(crcSender.getPx(), Gx);
        String errorPx = crcComputation.introduceRandomError(crcSender.getPx());
        CRCReceiver crcReceiverError = crcComputation.receiverSideComputing(errorPx, Gx);

        System.out.println("\nSender side Encoding ---" + "\n"
                    + "Mx: " + crcSender.getMx() + "\n"
                    + "Gx: " + crcSender.getGx() + "\n"
                    + "MDashX: " + crcSender.getMDashX() + "\n"
                    + "Quotient: " + crcSender.getQuotient() + "\n"
                    + "Remainder: " + crcSender.getRemainder() + "\n"
                    + "This is the transmitted message Px: " + crcSender.getPx());

        System.out.println("\nReceiver side Decoding without errors --- " + "\n"
                    + "Px: " + crcReceiver.getPx() + "\n"
                    + "Gx: " + crcReceiver.getGx() + "\n"
                    + "Quotient: " + crcReceiver.getQx() + "\n"
                    + "Remainder: " + crcReceiver.getRx() + "\n"
                    + "Status: " + crcReceiver.getMessage());

        System.out.println("\nReceiver side Decoding with Single bit or Burst errors --- " + "\n"
                    + "Px: " + crcReceiverError.getPx() + "\n"
                    + "Gx: " + crcReceiverError.getGx() + "\n"
                    + "Quotient: " + crcReceiverError.getQx() + "\n"
                    + "Remainder: " + crcReceiverError.getRx() + "\n"
                    + "Status: " + crcReceiverError.getMessage());
    }
}
