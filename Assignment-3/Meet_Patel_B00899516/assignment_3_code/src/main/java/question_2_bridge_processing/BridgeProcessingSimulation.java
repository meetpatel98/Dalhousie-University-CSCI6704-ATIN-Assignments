package question_2_bridge_processing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class BridgeProcessingSimulation {
    private static final String BRIDGE_DATABASE_PATH = "./src/main/java/question_2_bridge_processing/BridgeFDB.txt";
    private static final String RANDOM_FRAMES_PATH = "./src/main/java/question_2_bridge_processing/RandomFrames.txt";
    private static final String BRIDGE_OUTPUT_PATH = "./src/main/java/question_2_bridge_processing/BridgeOutput.txt";

    private static String getCorrectAction(String destinationAddress, String portNumber, Map<String, String> bridgeDatabase) {
        String actionTook;
        if (bridgeDatabase.containsKey(destinationAddress)) {
            if (bridgeDatabase.get(destinationAddress).equalsIgnoreCase(portNumber)) {
                actionTook = "Discarded";
            } else {
                actionTook = "Forwarded on port " + bridgeDatabase.get(destinationAddress);
            }
        } else {
            actionTook = "Broadcast";
        }
        return actionTook;
    }

    private static void checkMACIfExists(String sourceAddress, String portNumber, Map<String, String> bridgeDatabase) {
        if (bridgeDatabase.containsKey(sourceAddress)) {
            if (!bridgeDatabase.get(sourceAddress).equalsIgnoreCase(portNumber)) {
                bridgeDatabase.put(sourceAddress, portNumber);
            }
        } else {
            bridgeDatabase.put(sourceAddress, portNumber);
        }
    }

    public void execute() throws Exception {
        BufferedReader frameReader = new BufferedReader(new FileReader(RANDOM_FRAMES_PATH));
        BufferedReader bridgeDatabseReader = new BufferedReader(new FileReader(BRIDGE_DATABASE_PATH));

        String addressInDatabase;
        String sourceAddress;
        String destinationAddress;
        String port;
        StringBuilder sb = new StringBuilder();
        Map<String, String> bridgeDatabase = new LinkedHashMap<>();

        while ((addressInDatabase = bridgeDatabseReader.readLine()) != null) {
            bridgeDatabase.put(addressInDatabase, bridgeDatabseReader.readLine());
        }
        System.out.println("Generating output file....");
        while ((sourceAddress = frameReader.readLine()) != null) {

            destinationAddress = frameReader.readLine();
            port = frameReader.readLine();
            checkMACIfExists(sourceAddress, port, bridgeDatabase);
            String action = getCorrectAction(destinationAddress, port, bridgeDatabase);

            try (FileWriter bridgeOutputFileWriter = new FileWriter(BRIDGE_OUTPUT_PATH, true)) {
                bridgeOutputFileWriter
                        .append(sourceAddress)
                        .append("      ")
                        .append(destinationAddress)
                        .append("      ")
                        .append(port)
                        .append("      ")
                        .append(action)
                        .append('\n');
            } catch (Exception e) {
                System.out.println("Error Message: " + e.getMessage());
            }
        }

        for (Map.Entry<String, String> databaseEntry : bridgeDatabase.entrySet()) {
            sb.append(databaseEntry.getKey())
                    .append('\n')
                    .append(databaseEntry.getValue())
                    .append('\n');
        }

        try (FileWriter bridgeOutputFile = new FileWriter(BRIDGE_DATABASE_PATH)) {
            bridgeOutputFile.write(sb.toString());
        } catch (Exception e) {
            System.out.println("Error Message: ");
        }

        System.out.println("Output file generated Successfully!");
    }

}
