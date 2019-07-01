package cc.oobootcamp.parking;

import static java.util.Comparator.comparing;

import java.util.List;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy {

  public SmartParkingBoy(List<ParkingLot> parkingLots) {
    super(parkingLots);
  }

  @Override
  protected Optional<Ticket> tryPark(Car car) {
    return getParkingLots()
        .stream()
        .max(comparing(ParkingLot::getAvailableLots))
        .filter(ParkingLot::hasAvailableLots)
        .map(parkInto(car));
  }

  @Override
  protected Optional<Car> tryPick(Ticket ticket) {
    return getParkingLots()
        .stream()
        .filter(isValidTicket(ticket))
        .findFirst()
        .map(pickFrom(ticket));
  }
}
