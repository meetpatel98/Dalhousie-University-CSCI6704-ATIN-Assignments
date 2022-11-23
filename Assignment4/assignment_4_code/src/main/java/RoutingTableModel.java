/**
 * {@code RoutingTableModel} class represents each entry inside the routing table.
 *
 * @author Dhrumil Amish Shah (B00857606 | dh416386@dal.ca)
 */
public final class RoutingTableModel {
  // Destination address (E.g., 192.168.1.0).
  private final String destinationAddress;

  // Number of ones in the mask (E.g. 24).
  private final int onesInMask;

  // Next hop address (E.g., 192.168.3.2).
  private final String nextHopAddress;

  // Outgoing interface to which the packet is routed. (E.g., E0).
  private final String outgoingInterface;

  /**
   * Constructs this model class.
   *
   * @param destinationAddress Destination address.
   * @param onesInMask         Number of ones in the mask.
   * @param nextHopAddress     Next hop address.
   * @param outgoingInterface  Outgoing interface to which the packet is routed.
   */
  public RoutingTableModel(final String destinationAddress,
                           final int onesInMask,
                           final String nextHopAddress,
                           final String outgoingInterface) {
    this.destinationAddress = destinationAddress;
    this.onesInMask = onesInMask;
    this.nextHopAddress = nextHopAddress;
    this.outgoingInterface = outgoingInterface;
  }

  /**
   * Gets the destination address stored in {@code destinationAddress}.
   *
   * @return destination address stored in {@code destinationAddress}.
   */
  public String getDestinationAddress() {
    return destinationAddress;
  }

  /**
   * Gets the number of ones in mask stored in {@code onesInMask}.
   *
   * @return number of ones in mask  stored in {@code onesInMask}.
   */
  public int getOnesInMask() {
    return onesInMask;
  }

  /**
   * Gets the next hop address stored in {@code nextHopAddress}.
   *
   * @return next hop address stored in {@code nextHopAddress}.
   */
  public String getNextHopAddress() {
    return nextHopAddress;
  }

  /**
   * Gets the outgoing interface id stored in {@code outgoingInterface}.
   *
   * @return outgoing interface id stored in {@code outgoingInterface}.
   */
  public String getOutgoingInterface() {
    return outgoingInterface;
  }
}