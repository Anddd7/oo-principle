package cc.oobootcamp.parking;

import static java.util.Comparator.comparing;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class SmartParkingBoy {

  private List<ParkingLot> parkingLots;

  public SmartParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  public Ticket park(Car car) {
    return parkingLots.stream()
        .max(comparing(ParkingLot::getAvailableLots))
        .map(parkWithCar(car))
        .orElseThrow(ParkingLotIsFullException::new);
  }

  public Car pick(Ticket ticket) {
    return parkingLots.stream()
        .filter(isValidTicket(ticket))
        .findFirst()
        .map(pickWithTicket(ticket))
        .orElseThrow(InvalidTicketException::new);
  }

  private Predicate<ParkingLot> isValidTicket(Ticket ticket) {
    return parkingLot -> parkingLot.isValidTicket(ticket);
  }

  private Function<ParkingLot, Ticket> parkWithCar(Car car) {
    return parkingLot -> parkingLot.park(car);
  }

  private Function<ParkingLot, Car> pickWithTicket(Ticket ticket) {
    return parkingLot -> parkingLot.pick(ticket);
  }
}
