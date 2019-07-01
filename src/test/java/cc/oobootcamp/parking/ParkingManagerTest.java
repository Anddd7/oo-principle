package cc.oobootcamp.parking;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import org.junit.Test;

public class ParkingManagerTest {

  @Test
  public void should_return_a_valid_ticket_when_park_given_a_parking_manager_with_available_lots_and_graduate_and_smart_parking_boy() {
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(new ParkingLot(1)));
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Collections.singletonList(new ParkingLot(1)));
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
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(new ParkingLot(1)));
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Collections.singletonList(buildAFullParkingLot()));
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
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(buildAFullParkingLot()));
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Collections.singletonList(new ParkingLot(1)));
    ParkingManager parkingManager = new ParkingManager(
        Collections.singletonList(buildAFullParkingLot()),
        graduateParkingBoy,
        smartParkingBoy
    );

    Ticket ticket = parkingManager.park(new Car());

    assertThat(ticket).isNotNull();
  }

  private ParkingLot buildAFullParkingLot() {
    return new ParkingLot(1, new Car());
  }
}
