package cc.oobootcamp.parking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import org.junit.Test;

public class GraduateParkingBoyTest {

  @Test
  public void should_return_a_valid_ticket_when_park_a_car_into_given_2_car_parks_both_have_available_lots() {
    ParkingLot fistParkingLot = new ParkingLot(2);
    ParkingLot secondParkingLot = new ParkingLot(2);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(
        Arrays.asList(fistParkingLot, secondParkingLot)
    );
    Car car = new Car();

    Ticket ticket = graduateParkingBoy.park(car);

    assertThat(ticket).isNotNull();
    assertThat(fistParkingLot.pick(ticket)).isNotNull();
    assertThatThrownBy(() -> secondParkingLot.pick(ticket))
        .isInstanceOf(InvalidTicketException.class);
  }

  @Test
  public void should_return_valid_ticket_when_park_a_car_given_2_car_parks_first_full_second_with_available_lots() {
    ParkingLot fistParkingLot = buildAFullCarPark();
    ParkingLot secondParkingLot = new ParkingLot(2);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(
        Arrays.asList(fistParkingLot, secondParkingLot)
    );
    Car car = new Car();

    Ticket ticket = graduateParkingBoy.park(car);

    assertThat(ticket).isNotNull();
    assertThatThrownBy(() -> fistParkingLot.pick(ticket))
        .isInstanceOf(InvalidTicketException.class);
    assertThat(secondParkingLot.pick(ticket)).isNotNull();
  }

  @Test
  public void should_get_cannot_park_message_when_park_a_car_given_2_car_parks_both_full() {
    ParkingLot fistParkingLot = buildAFullCarPark();
    ParkingLot secondParkingLot = buildAFullCarPark();
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(
        Arrays.asList(fistParkingLot, secondParkingLot)
    );
    Car car = new Car();

    assertThatThrownBy(() -> graduateParkingBoy.park(car))
        .isInstanceOf(ParkingLotIsFullException.class)
        .hasMessage("All car park are full, cannot park your car.");
  }

  @Test
  public void should_get_the_car_when_pick_a_car_with_valid_ticket_given_2_car_parks_parked_related_car() {
    ParkingLot fistParkingLot = buildAFullCarPark();
    ParkingLot secondParkingLot = new ParkingLot(2);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(
        Arrays.asList(fistParkingLot, secondParkingLot)
    );
    Car myCar = new Car();
    Ticket myTicket = graduateParkingBoy.park(myCar);

    Car pickedCar = graduateParkingBoy.pick(myTicket);

    assertThat(pickedCar).isEqualTo(myCar);
  }

  @Test
  public void should_get_cannot_pick_message_when_pick_a_car_with_invalid_ticket_given_2_car_parks() {
    ParkingLot fistParkingLot = buildAFullCarPark();
    ParkingLot secondParkingLot = new ParkingLot(2);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(
        Arrays.asList(fistParkingLot, secondParkingLot)
    );

    assertThatThrownBy(() -> graduateParkingBoy.pick(new Ticket()))
        .isInstanceOf(InvalidTicketException.class)
        .hasMessage("Your ticket is invalid, cannot find your car in our park.");
  }

  private ParkingLot buildAFullCarPark() {
    return new ParkingLot(1, new Car());
  }
}
