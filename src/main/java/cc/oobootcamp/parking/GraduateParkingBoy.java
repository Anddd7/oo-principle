package cc.oobootcamp.parking;

import java.util.List;
import java.util.Optional;

public class GraduateParkingBoy extends ParkingBoy {

  public GraduateParkingBoy(List<ParkingLot> parkingLots) {
    super(parkingLots);
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
