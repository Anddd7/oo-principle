package cc.oobootcamp.parking;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ParkingManager extends SmartParkingBoy {

  private List<ParkingBoy> parkingBoys;

  public ParkingManager(List<ParkingLot> parkingLots, ParkingBoy... parkingBoys) {
    super(parkingLots);
    this.parkingBoys = Arrays.asList(parkingBoys);
  }

  @Override
  protected Optional<Ticket> tryPark(Car car) {
    Optional<Ticket> optionalTicket = super.tryPark(car);
    if (optionalTicket.isPresent()) {
      return optionalTicket;
    }
    return tryParkByParkingBoys(car);
  }

  private Optional<Ticket> tryParkByParkingBoys(Car car) {
    return parkingBoys.stream()
        .map(parkingBoy -> parkingBoy.tryPark(car))
        .filter(Optional::isPresent)
        .findFirst()
        .orElse(Optional.empty());
  }
}
