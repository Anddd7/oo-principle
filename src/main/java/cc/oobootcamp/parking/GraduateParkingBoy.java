package cc.oobootcamp.parking;

import java.util.List;
import java.util.Optional;

public class GraduateParkingBoy extends ParkingBoy {

  public GraduateParkingBoy(List<ParkingLot> parkingLots) {
    super(parkingLots);
  }

  @Override
  protected Optional<Ticket> tryPark(Car car) {
    return getParkingLots()
        .stream()
        .filter(ParkingLot::hasAvailableLots)
        .findFirst()
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
