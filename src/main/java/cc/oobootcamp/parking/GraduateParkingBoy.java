package cc.oobootcamp.parking;

import java.util.List;

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
}
