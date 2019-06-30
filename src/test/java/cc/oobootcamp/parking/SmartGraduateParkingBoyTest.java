package cc.oobootcamp.parking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import org.junit.Test;

public class SmartGraduateParkingBoyTest {

  @Test
  public void should_return_a_valid_ticket_when_park_a_car_into_given_2_parking_lots_one_have_more_available_space() {
    ParkingLot lessSpaceParkingLot = new ParkingLot(2);
    ParkingLot moreSpaceParkingLot = new ParkingLot(3);
    SmartParkingBoy parkingBoy = new SmartParkingBoy(
        Arrays.asList(lessSpaceParkingLot, moreSpaceParkingLot)
    );
    Car car = new Car();

    Ticket ticket = parkingBoy.park(car);

    assertThat(ticket).isNotNull();
    assertThatThrownBy(() -> lessSpaceParkingLot.pick(ticket))
        .isInstanceOf(InvalidTicketException.class);
    assertThat(moreSpaceParkingLot.pick(ticket)).isNotNull();
  }

  @Test
  public void should_return_a_valid_ticket_when_park_a_car_into_given_2_parking_lots_with_some_available_space() {
    ParkingLot firstParkingLot = new ParkingLot(2);
    ParkingLot secondParkingLot = new ParkingLot(2);
    SmartParkingBoy parkingBoy = new SmartParkingBoy(
        Arrays.asList(firstParkingLot, secondParkingLot)
    );
    Car car = new Car();

    Ticket ticket = parkingBoy.park(car);

    assertThat(ticket).isNotNull();
    assertThat(firstParkingLot.pick(ticket)).isNotNull();
    assertThatThrownBy(() -> secondParkingLot.pick(ticket))
        .isInstanceOf(InvalidTicketException.class);
  }

  @Test
  public void should_return_valid_ticket_when_park_a_car_given_2_car_parks_first_full_second_with_available_lots() {
    ParkingLot fistParkingLot = buildAFullCarPark();
    ParkingLot secondParkingLot = new ParkingLot(2);
    SmartParkingBoy parkingBoy = new SmartParkingBoy(
        Arrays.asList(fistParkingLot, secondParkingLot)
    );
    Car car = new Car();

    Ticket ticket = parkingBoy.park(car);

    assertThat(ticket).isNotNull();
    assertThatThrownBy(() -> fistParkingLot.pick(ticket))
        .isInstanceOf(InvalidTicketException.class);
    assertThat(secondParkingLot.pick(ticket)).isNotNull();
  }

  @Test
  public void should_get_cannot_park_message_when_park_a_car_given_2_car_parks_both_full() {
    ParkingLot fistParkingLot = buildAFullCarPark();
    ParkingLot secondParkingLot = buildAFullCarPark();
    SmartParkingBoy parkingBoy = new SmartParkingBoy(
        Arrays.asList(fistParkingLot, secondParkingLot)
    );
    Car car = new Car();

    assertThatThrownBy(() -> parkingBoy.park(car))
        .isInstanceOf(ParkingLotIsFullException.class)
        .hasMessage("All car park are full, cannot park your car.");
  }

  @Test
  public void should_get_the_car_when_pick_a_car_with_valid_ticket_given_2_car_parks_parked_related_car() {
    ParkingLot fistParkingLot = buildAFullCarPark();
    ParkingLot secondParkingLot = new ParkingLot(2);
    SmartParkingBoy parkingBoy = new SmartParkingBoy(
        Arrays.asList(fistParkingLot, secondParkingLot)
    );
    Car myCar = new Car();
    Ticket myTicket = parkingBoy.park(myCar);

    Car pickedCar = parkingBoy.pick(myTicket);

    assertThat(pickedCar).isEqualTo(myCar);
  }

  @Test
  public void should_get_cannot_pick_message_when_pick_a_car_with_invalid_ticket_given_2_car_parks() {
    ParkingLot fistParkingLot = buildAFullCarPark();
    ParkingLot secondParkingLot = new ParkingLot(2);
    SmartParkingBoy parkingBoy = new SmartParkingBoy(
        Arrays.asList(fistParkingLot, secondParkingLot)
    );

    assertThatThrownBy(() -> parkingBoy.pick(new Ticket()))
        .isInstanceOf(InvalidTicketException.class)
        .hasMessage("Your ticket is invalid, cannot find your car in our park.");
  }

  private ParkingLot buildAFullCarPark() {
    return new ParkingLot(1, new Car());
  }
}
