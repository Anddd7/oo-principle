package cc.oobootcamp.parking;

import java.util.List;
import java.util.Optional;

public class GraduateParkingBoy extends ParkingBoy {

  public GraduateParkingBoy(List<ParkingLot> parkingLots) {
    super(parkingLots);
  }

  @Override
  public Ticket park(Car car) {
    return getParkingLots()
        .stream()
        .filter(ParkingLot::hasAvailableLots)
        .findFirst()
        .map(parkInto(car))
        .orElseThrow(ParkingLotIsFullException::new);
  }

  @Override
  Optional<Ticket> tryPark(Car car) {
    return getParkingLots()
        .stream()
        .filter(ParkingLot::hasAvailableLots)
        .findFirst()
        .map(parkInto(car));
  }
}
