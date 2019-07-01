package cc.oobootcamp.parking;

import static java.util.Comparator.comparing;

import java.util.List;
import java.util.Optional;

public class SmartParkingBoy extends GraduateParkingBoy {

  public SmartParkingBoy(List<ParkingLot> parkingLots) {
    super(parkingLots);
  }

  public Optional<Ticket> tryPark(Car car) {
    return getParkingLots()
        .stream()
        .max(comparing(ParkingLot::getAvailableLots))
        .filter(ParkingLot::hasAvailableLots)
        .map(parkingLot -> parkingLot.park(car));
  }
}
