package cc.oobootcamp.parking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import org.junit.Test;

public class ParkingManagerTest {

  @Test
  public void should_return_a_valid_ticket_when_park_given_a_parking_manager_with_available_lots_and_graduate_and_smart_parking_boy() {
    ParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(new ParkingLot(1)));
    ParkingBoy smartParkingBoy = new SmartParkingBoy(Collections.singletonList(new ParkingLot(1)));
    ParkingManager parkingManager = new ParkingManager(
        Collections.singletonList(new ParkingLot(1)),
        graduateParkingBoy,
        smartParkingBoy
    );

    Ticket ticket = parkingManager.park(new Car());

    assertThat(ticket).isNotNull();
  }

  @Test
  public void should_return_a_valid_ticket_when_park_given_a_parking_manager_and_graduate_and_smart_parking_boy_but_only_manager_have_available_lots() {
    ParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(new ParkingLot(1)));
    ParkingBoy smartParkingBoy = buildAFullSmartParkingBoy();
    ParkingManager parkingManager = new ParkingManager(
        Collections.singletonList(buildAFullParkingLot()),
        graduateParkingBoy,
        smartParkingBoy
    );

    Ticket ticket = parkingManager.park(new Car());

    assertThat(ticket).isNotNull();
  }

  @Test
  public void should_return_a_valid_ticket_when_park_given_a_parking_manager_and_graduate_and_smart_parking_boy_but_only_smart_have_available_lots() {
    ParkingBoy graduateParkingBoy = buildAFullGraduateParkingBoy();
    ParkingBoy smartParkingBoy = new SmartParkingBoy(Collections.singletonList(new ParkingLot(1)));
    ParkingManager parkingManager = new ParkingManager(
        Collections.singletonList(buildAFullParkingLot()),
        graduateParkingBoy,
        smartParkingBoy
    );

    Ticket ticket = parkingManager.park(new Car());

    assertThat(ticket).isNotNull();
  }

  @Test
  public void should_return_a_valid_ticket_when_park_given_a_parking_manager_and_graduate_and_smart_parking_boy_but_only_graduate_have_available_lots() {
    ParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(new ParkingLot(1)));
    ParkingBoy smartParkingBoy = buildAFullSmartParkingBoy();
    ParkingManager parkingManager = new ParkingManager(
        Collections.singletonList(buildAFullParkingLot()),
        graduateParkingBoy,
        smartParkingBoy
    );

    Ticket ticket = parkingManager.park(new Car());

    assertThat(ticket).isNotNull();
  }

  @Test
  public void should_return_a_valid_ticket_when_park_given_a_parking_manager_and_graduate_and_smart_parking_boy_are_all_full() {
    ParkingBoy graduateParkingBoy = buildAFullGraduateParkingBoy();
    ParkingBoy smartParkingBoy = buildAFullSmartParkingBoy();
    ParkingManager parkingManager = new ParkingManager(
        Collections.singletonList(buildAFullParkingLot()),
        graduateParkingBoy,
        smartParkingBoy
    );

    assertThatThrownBy(() -> parkingManager.park(new Car()))
        .isInstanceOf(ParkingLotIsFullException.class)
        .hasMessage("All car park are full, cannot park your car.");
  }

  @Test
  public void should_pick_the_car_when_pick_given_a_valid_ticket_with_a_car_parked_in_manager() {
    ParkingBoy graduateParkingBoy = buildAFullGraduateParkingBoy();
    ParkingBoy smartParkingBoy = buildAFullSmartParkingBoy();
    ParkingManager parkingManager = new ParkingManager(
        Collections.singletonList(new ParkingLot(1)),
        graduateParkingBoy,
        smartParkingBoy
    );
    Car car = new Car();
    Ticket ticket = parkingManager.park(car);

    Car pickedCar = parkingManager.pick(ticket);

    assertThat(pickedCar).isEqualTo(car);
  }

  @Test
  public void should_pick_the_car_when_pick_given_a_valid_ticket_with_a_car_parked_in_smart() {
    ParkingBoy graduateParkingBoy = buildAFullGraduateParkingBoy();
    ParkingBoy smartParkingBoy = new SmartParkingBoy(Collections.singletonList(new ParkingLot(1)));
    ParkingManager parkingManager = new ParkingManager(
        Collections.singletonList(buildAFullParkingLot()),
        graduateParkingBoy,
        smartParkingBoy
    );
    Car car = new Car();
    Ticket ticket = parkingManager.park(car);

    Car pickedCar = parkingManager.pick(ticket);

    assertThat(pickedCar).isEqualTo(car);
  }

  private ParkingLot buildAFullParkingLot() {
    return new ParkingLot(1, new Car());
  }

  private GraduateParkingBoy buildAFullGraduateParkingBoy() {
    return new GraduateParkingBoy(Collections.singletonList(buildAFullParkingLot()));
  }

  private SmartParkingBoy buildAFullSmartParkingBoy() {
    return new SmartParkingBoy(Collections.singletonList(buildAFullParkingLot()));
  }
}
