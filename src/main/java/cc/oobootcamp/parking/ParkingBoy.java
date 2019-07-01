package cc.oobootcamp.parking;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class ParkingBoy {

  List<ParkingLot> getParkingLots() {
    return parkingLots;
  }

  private List<ParkingLot> parkingLots;

  ParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  abstract public Ticket park(Car car);

  public Car pick(Ticket ticket) {
    return parkingLots.stream()
        .filter(isValidTicket(ticket))
        .findFirst()
        .map(pickFrom(ticket))
        .orElseThrow(InvalidTicketException::new);
  }

  Function<ParkingLot, Ticket> parkInto(Car car) {
    return parkingLot -> parkingLot.park(car);
  }

  private Predicate<ParkingLot> isValidTicket(Ticket ticket) {
    return parkingLot -> parkingLot.isValidTicket(ticket);
  }

  private Function<ParkingLot, Car> pickFrom(Ticket ticket) {
    return parkingLot -> parkingLot.pick(ticket);
  }
}
