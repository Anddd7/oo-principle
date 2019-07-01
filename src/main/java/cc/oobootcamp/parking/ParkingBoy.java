package cc.oobootcamp.parking;

import java.util.List;
import java.util.Optional;
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

  abstract protected Optional<Ticket> tryPark(Car car);

  abstract protected Optional<Car> tryPick(Ticket ticket);

  public Ticket park(Car car) {
    return tryPark(car).orElseThrow(ParkingLotIsFullException::new);
  }

  public Car pick(Ticket ticket) {
    return tryPick(ticket).orElseThrow(InvalidTicketException::new);
  }

  Function<ParkingLot, Ticket> parkInto(Car car) {
    return parkingLot -> parkingLot.park(car);
  }

  Predicate<ParkingLot> isValidTicket(Ticket ticket) {
    return parkingLot -> parkingLot.isValidTicket(ticket);
  }

  Function<ParkingLot, Car> pickFrom(Ticket ticket) {
    return parkingLot -> parkingLot.pick(ticket);
  }
}
