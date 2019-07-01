package cc.oobootcamp.parking;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ParkingManager extends SmartParkingBoy {

  private List<IParkingBoy> parkingBoys;

  public ParkingManager(List<ParkingLot> parkingLots, IParkingBoy... parkingBoys) {
    super(parkingLots);
    this.parkingBoys = Arrays.asList(parkingBoys);
  }

  @Override
  public Optional<Ticket> tryPark(Car car) {
    Optional<Ticket> optionalTicket = super.tryPark(car);
    if (optionalTicket.isPresent()) {
      return optionalTicket;
    }
    return tryParkByParkingBoys(car);
  }

  private Optional<Ticket> tryParkByParkingBoys(Car car) {
    return tryFindAvailableParkingBoy(parkingBoy -> parkingBoy.tryPark(car));
  }

  @Override
  public Optional<Car> tryPick(Ticket ticket) {
    Optional<Car> optionalCar = super.tryPick(ticket);
    if (optionalCar.isPresent()) {
      return optionalCar;
    }
    return tryPickByParkingBoys(ticket);
  }

  private Optional<Car> tryPickByParkingBoys(Ticket ticket) {
    return tryFindAvailableParkingBoy(parkingBoy -> parkingBoy.tryPick(ticket));
  }

  private <T> Optional<T> tryFindAvailableParkingBoy(Function<IParkingBoy, Optional<T>> todo) {
    return parkingBoys.stream()
        .map(todo)
        .filter(Optional::isPresent)
        .findFirst()
        .orElse(Optional.empty());
  }
}
