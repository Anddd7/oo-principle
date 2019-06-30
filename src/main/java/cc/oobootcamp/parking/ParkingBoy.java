package cc.oobootcamp.parking;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class ParkingBoy {

  private List<ParkingLot> parkingLots;

  ParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  abstract Optional<ParkingLot> parkingStrategy(Stream<ParkingLot> parkingLots);

  public Ticket park(Car car) {
    return parkingStrategy(parkingLots.stream())
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

  private Function<ParkingLot, Ticket> parkWithCar(Car car) {
    return parkingLot -> parkingLot.park(car);
  }

  private Predicate<ParkingLot> isValidTicket(Ticket ticket) {
    return parkingLot -> parkingLot.isValidTicket(ticket);
  }

  private Function<ParkingLot, Car> pickWithTicket(Ticket ticket) {
    return parkingLot -> parkingLot.pick(ticket);
  }
}
