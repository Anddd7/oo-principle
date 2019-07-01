package cc.oobootcamp.parking;

import java.util.List;
import java.util.Optional;

public class GraduateParkingBoy implements IParkingBoy {

  private List<ParkingLot> parkingLots;

  public GraduateParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  public Ticket park(Car car) {
    return tryPark(car).orElseThrow(ParkingLotIsFullException::new);
  }

  public Car pick(Ticket ticket) {
    return tryPick(ticket).orElseThrow(InvalidTicketException::new);
  }

  @Override
  public Optional<Ticket> tryPark(Car car) {
    return parkingLots
        .stream()
        .filter(ParkingLot::hasAvailableLots)
        .findFirst()
        .map(parkingLot -> parkingLot.park(car));
  }

  @Override
  public Optional<Car> tryPick(Ticket ticket) {
    return parkingLots
        .stream()
        .filter(parkingLot -> parkingLot.isValidTicket(ticket))
        .findFirst()
        .map(parkingLot -> parkingLot.pick(ticket));
  }

  List<ParkingLot> getParkingLots() {
    return parkingLots;
  }
}
