package cc.oobootcamp.parking;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class GraduateParkingBoy extends ParkingBoy {

  public GraduateParkingBoy(List<ParkingLot> parkingLots) {
    super(parkingLots);
  }

  @Override
  public Ticket park(Car car) {
    return super.park(car);
  }

  @Override
  Optional<ParkingLot> parkingStrategy(Stream<ParkingLot> parkingLots) {
    return parkingLots
        .filter(ParkingLot::hasAvailableLots)
        .findFirst();
  }
}
