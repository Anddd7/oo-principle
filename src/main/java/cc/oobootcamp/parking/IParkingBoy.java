package cc.oobootcamp.parking;

import java.util.Optional;

public interface IParkingBoy {

  Ticket park(Car car);

  Car pick(Ticket ticket);

  Optional<Ticket> tryPark(Car car);

  Optional<Car> tryPick(Ticket ticket);
}
