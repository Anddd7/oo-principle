package cc.oobootcamp.parking;

import static java.util.Comparator.comparing;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

  public SmartParkingBoy(List<ParkingLot> parkingLots) {
    super(parkingLots);
  }

  @Override
  public Ticket park(Car car) {
    return getParkingLots()
        .stream()
        .max(comparing(ParkingLot::getAvailableLots))
        .filter(ParkingLot::hasAvailableLots)
        .map(parkInto(car))
        .orElseThrow(ParkingLotIsFullException::new);
  }
}
