package parking;

import mocking.CustomerDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VipParkingStrategyTest {

  @Mock
  CarDao carDao;

  @InjectMocks
  VipParkingStrategy vipParkingStrategy;

  @Test
  public void
      testPark_givenAVipCarAndAFullParkingLot_thenGiveAReceiptWithCarNameAndParkingLotName() {

    /* Exercise 4, Write a test case on VipParkingStrategy.park()
     * With using Mockito spy, verify and doReturn */
    Car car = new Car("Ase");
    ParkingLot parkingLot = new ParkingLot("south", 1);
    List<ParkingLot> parkingLots = new ArrayList<>();
    parkingLots.add(parkingLot);
    VipParkingStrategy vipParkingStrategy = Mockito.spy(new VipParkingStrategy());
    doReturn(true).when(vipParkingStrategy).isAllowOverPark(car);
    vipParkingStrategy.park(parkingLots, car);
    verify(vipParkingStrategy, times(1)).createReceipt(parkingLot, car);
  }

  @Test
  public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

    /* Exercise 4, Write a test case on VipParkingStrategy.park()
     * With using Mockito spy, verify and doReturn */
    Car car = new Car("se");
    ParkingLot parkingLot = new ParkingLot("south", 0);
    List<ParkingLot> parkingLots = new ArrayList<>();
    parkingLots.add(parkingLot);
    VipParkingStrategy vipParkingStrategy = Mockito.spy(new VipParkingStrategy());
    vipParkingStrategy.park(parkingLots, car);
    doReturn(false).when(vipParkingStrategy).isAllowOverPark(car);
    verify(vipParkingStrategy, times(1)).createNoSpaceReceipt(car);
  }

  @Test
  public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue() {

    /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
     * You may refactor the code, or try to use
     * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
     */
    when(carDao.isVip(anyString())).thenReturn(true);
    Car car = new Car("Ase");
    ParkingLot parkingLot = new ParkingLot("south", 1);
    List<ParkingLot> parkingLots = new ArrayList<>();
    parkingLots.add(parkingLot);
    boolean allowOverPark = vipParkingStrategy.isAllowOverPark(car);
    Assert.assertEquals(true,allowOverPark);
  }

  @Test
  public void
      testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse() {

    /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
     * You may refactor the code, or try to use
     * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
     */
    when(carDao.isVip(anyString())).thenReturn(true);
    Car car = new Car("se");
    ParkingLot parkingLot = new ParkingLot("south", 1);
    List<ParkingLot> parkingLots = new ArrayList<>();
    parkingLots.add(parkingLot);
    boolean allowOverPark = vipParkingStrategy.isAllowOverPark(car);
    Assert.assertEquals(false,allowOverPark);


  }

  @Test
  public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
    /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
     * You may refactor the code, or try to use
     * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
     */
    when(carDao.isVip(anyString())).thenReturn(false);
    Car car = new Car("Ase");
    ParkingLot parkingLot = new ParkingLot("south", 1);
    List<ParkingLot> parkingLots = new ArrayList<>();
    parkingLots.add(parkingLot);
    boolean allowOverPark = vipParkingStrategy.isAllowOverPark(car);
    Assert.assertEquals(false,allowOverPark);
  }

  @Test
  public void
      testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
    /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
     * You may refactor the code, or try to use
     * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
     */
    when(carDao.isVip(anyString())).thenReturn(false);
    Car car = new Car("se");
    ParkingLot parkingLot = new ParkingLot("south", 1);
    List<ParkingLot> parkingLots = new ArrayList<>();
    parkingLots.add(parkingLot);
    boolean allowOverPark = vipParkingStrategy.isAllowOverPark(car);
    Assert.assertEquals(false,allowOverPark);


  }

  private Car createMockCar(String carName) {
    Car car = mock(Car.class);
    when(car.getName()).thenReturn(carName);
    return car;
  }
}
