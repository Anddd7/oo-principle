package cc.oobootcamp.parking;

import static java.util.Comparator.comparing;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class SmartParkingBoy extends ParkingBoy {

  public SmartParkingBoy(List<ParkingLot> parkingLots) {
    super(parkingLots);
  }

  @Override
  Optional<ParkingLot> parkingStrategy(Stream<ParkingLot> parkingLots) {
    return parkingLots.max(comparing(ParkingLot::getAvailableLots))
        .filter(ParkingLot::hasAvailableLots);
  }
}
