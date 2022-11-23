import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * {@code RouterSimulation} class simulates the routing decisions taken by the router.
 * This class read two files namely "RandomPackets.txt" and "RoutingTable.txt" and decides where to route each packet.
 *
 * @author Dhrumil Amish Shah (B00857606 | dh416386@dal.ca)
 */
public final class RouterSimulation {
  // Path to "files" folder.
  private static final String FILES_PATH;

  // Path to "RandomPackets.txt" file.
  private static final String RANDOM_PACKETS_FILE_PATH;

  // Path to "RoutingTable.txt" file.
  private static final String ROUTING_TABLE_FILE_PATH;

  // Path to "RoutingOutput.txt" file.
  private static final String ROUTING_OUTPUT_FILE_PATH;

  // Loopback address
  private static final String LOOPBACK_ADDRESS;

  // Default address
  private static final String DEFAULT_ADDRESS;

  // Masking addresses
  private static final String[] MASKING_ADDRESSES;

  static {
    FILES_PATH = "./src/main/java/files/";
    RANDOM_PACKETS_FILE_PATH = FILES_PATH + "RandomPackets.txt";
    ROUTING_TABLE_FILE_PATH = FILES_PATH + "RoutingTable.txt";
    ROUTING_OUTPUT_FILE_PATH = FILES_PATH + "RoutingOutput.txt";
    LOOPBACK_ADDRESS = "127.0.0.1";
    DEFAULT_ADDRESS = "0.0.0.0";
    MASKING_ADDRESSES = new String[]{"255.255.255.255", "255.255.255.0", "255.255.0.0", "255.0.0.0"};
  }

  /**
   * Stores the decision made by router in file {@code ROUTING_OUTPUT_FILE_PATH}.
   *
   * @param message message to be stored in file {@code ROUTING_OUTPUT_FILE_PATH}.
   */
  private void storeRoutingDecisionInFile(final String message) {
    try (final FileWriter routingOutputFileReader = new FileWriter(ROUTING_OUTPUT_FILE_PATH, true)) {
      routingOutputFileReader.append(message);
      routingOutputFileReader.append("\n");
    } catch (final IOException e) {
      e.printStackTrace();
      System.err.println(e.getMessage());
    }
  }

  /**
   * Gets the routing message based on the entries in routing table {@code routingTableHashMap}.
   *
   * @param newAddress               Host-specific or network specific address.
   * @param randomDestinationAddress address for which routing decision is to be made.
   * @param routingTableHashMap      routing table.
   *
   * @return routing message.
   */
  private String getRoutingMessage(final String newAddress,
                                   final String randomDestinationAddress,
                                   final Map<String, RoutingTableModel> routingTableHashMap) {
    String message = "";
    if (routingTableHashMap.containsKey(newAddress)) {
      final RoutingTableModel routingTableEntry = routingTableHashMap.get(newAddress);
      if (routingTableEntry.getNextHopAddress().equals("-")) {
        message = String.format("%s will be forwarded on the directly connected network on interface %s.",
            randomDestinationAddress, routingTableEntry.getOutgoingInterface());
      } else {
        message = String.format("%s will be forwarded to %s out on interface %s.",
            randomDestinationAddress, routingTableEntry.getNextHopAddress(), routingTableEntry.getOutgoingInterface());
      }
    }
    return message;
  }

  /**
   * Routing algorithm. This algorithm decided where to route each packet when received by the router.
   *
   * @param randomDestinationAddress address for which routing decision is to be made.
   * @param routingTableHashMap      routing table.
   *
   * @return routing message.
   */
  private String getRoutingDecision(final String randomDestinationAddress,
                                    final Map<String, RoutingTableModel> routingTableHashMap) {
    // Routing message if address is a loopback address.
    if (randomDestinationAddress.equals(LOOPBACK_ADDRESS)) {
      return String.format("%s is loopback; discarded.", LOOPBACK_ADDRESS);
    }

    // Routing message if address is a false address.
    for (String maskingAddress : MASKING_ADDRESSES) {
      if (randomDestinationAddress.equals(maskingAddress)) {
        return String.format("%s is malformed; discarded.", maskingAddress);
      }
    }

    // Host-specific and network-specific addresses.
    for (int i = 0; i < 4; ++i) {
      switch (i) {
        case 0: {
          //Host-specific address. (X.X.X.X)
          final String message = getRoutingMessage(randomDestinationAddress, randomDestinationAddress, routingTableHashMap);
          if (!message.isEmpty()) {
            return message;
          }
          break;
        }
        case 1: {
          //Network-specific address. (X.X.X.0)
          String[] addressParts = randomDestinationAddress.split("\\.");
          final String newAddress = addressParts[0] + "." + addressParts[1] + "." + addressParts[2] + "." + "0";
          final String message = getRoutingMessage(newAddress, randomDestinationAddress, routingTableHashMap);
          if (!message.isEmpty()) {
            return message;
          }
          break;
        }
        case 2: {
          //Network-specific address. (X.X.0.0)
          String[] addressParts = randomDestinationAddress.split("\\.");
          final String newAddress = addressParts[0] + "." + addressParts[1] + "." + "0" + "." + "0";
          final String message = getRoutingMessage(newAddress, randomDestinationAddress, routingTableHashMap);
          if (!message.isEmpty()) {
            return message;
          }
          break;
        }
        case 3: {
          //Network-specific address. (X.0.0.0)
          String[] addressParts = randomDestinationAddress.split("\\.");
          final String newAddress = addressParts[0] + "." + "0" + "." + "0" + "." + "0";
          final String message = getRoutingMessage(newAddress, randomDestinationAddress, routingTableHashMap);
          if (!message.isEmpty()) {
            return message;
          }
          break;
        }
      }
    }

    //Default address. (0.0.0.0)
    final RoutingTableModel routingTableEntry = routingTableHashMap.get(DEFAULT_ADDRESS);
    return String.format("%s will be forwarded to %s out on interface %s.",
        randomDestinationAddress, routingTableEntry.getNextHopAddress(), routingTableEntry.getOutgoingInterface());
  }

  /**
   * Reads the routing table from file {@code ROUTING_TABLE_FILE_PATH} in memory.
   *
   * @return routing table read from the file {@code ROUTING_TABLE_FILE_PATH}.
   */
  private Map<String, RoutingTableModel> readRoutingTableInMemory() {
    final Map<String, RoutingTableModel> routingTableHashMap = new LinkedHashMap<>();
    try (final FileReader routingTableFileReader = new FileReader(ROUTING_TABLE_FILE_PATH);
         final BufferedReader routingTableBufferedReader = new BufferedReader(routingTableFileReader)) {
      String destinationAddress;
      int onesInMask;
      String nextHopAddress;
      String outgoingInterface;
      while ((destinationAddress = routingTableBufferedReader.readLine()) != null) {
        onesInMask = Integer.parseInt(destinationAddress.split("/")[1]);
        nextHopAddress = routingTableBufferedReader.readLine();
        outgoingInterface = routingTableBufferedReader.readLine();
        routingTableHashMap.put(destinationAddress.split("/")[0],
            new RoutingTableModel(destinationAddress.split("/")[0],
                onesInMask,
                nextHopAddress,
                outgoingInterface));
      }
      System.out.println("<========= Routing Table In Memory =========>");
      System.out.printf("%-20s | %-12s | %-20s | %-20s%n",
          "Destination Address",
          "Ones In Mask",
          "Next Hop Address",
          "Outgoing Interface");
      for (Map.Entry<String, RoutingTableModel> entry : routingTableHashMap.entrySet()) {
        System.out.printf("%-20s | %-12s | %-20s | %-20s%n",
            entry.getKey(),
            entry.getValue().getOnesInMask(),
            entry.getValue().getNextHopAddress(),
            entry.getValue().getOutgoingInterface());
      }
    } catch (final IOException e) {
      e.printStackTrace();
      System.err.println(e.getMessage());
    }
    return routingTableHashMap;
  }

  /**
   * Performs routing simulation.
   */
  public void execute() {
    final Map<String, RoutingTableModel> routingTableHashMap = readRoutingTableInMemory();
    try (final FileReader randomPacketsFileReader = new FileReader(RANDOM_PACKETS_FILE_PATH);
         final BufferedReader randomPacketsBufferedReader = new BufferedReader(randomPacketsFileReader)) {
      System.out.println("\n<========= Output - Routing Decisions =========>");
      String randomDestinationAddress;
      while ((randomDestinationAddress = randomPacketsBufferedReader.readLine()) != null) {
        final String routingDecision = getRoutingDecision(randomDestinationAddress, routingTableHashMap);
        System.out.println(routingDecision);
        storeRoutingDecisionInFile(routingDecision);
      }
    } catch (final IOException e) {
      e.printStackTrace();
      System.err.println(e.getMessage());
    }
  }
}