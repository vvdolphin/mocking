package parking;

import mocking.CustomerDao;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class InOrderParkingStrategyTest {

	@Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
	    * With using Mockito to mock the input parameter */

        //given
        Car car = createMockCar("BMW");
        ParkingLot parkingLot = new ParkingLot("south",10);
        InOrderParkingStrategy parkingStrategy = new InOrderParkingStrategy();
        //when
        Receipt resultReceipt = parkingStrategy.park(null, car);

        //then
        Assert.assertEquals("BMW", resultReceipt.getCarName());

    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
        ParkingLot parkingLot = new ParkingLot("south",1);
        Car car = createMockCar("BMW");
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        InOrderParkingStrategy parkingStrategy = new InOrderParkingStrategy();
        parkingStrategy.park(parkingLots,car);

        Car car1 = createMockCar("Bens");
        Receipt receipt = parkingStrategy.park(parkingLots, car1);

        Assert.assertEquals("No Parking Lot",  receipt.getParkingLotName());
    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
        //given
        ParkingLot parkingLot = new ParkingLot("south",10);
        List<ParkingLot> parkingLots = new ArrayList<>();
        Car car = createMockCar("BWM");
        InOrderParkingStrategy parkingStrategy = Mockito.spy(new InOrderParkingStrategy());
        //when
        parkingStrategy.park(parkingLots, car);
        //then
        verify(parkingStrategy,times(1)).createNoSpaceReceipt(car);
    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
        ParkingLot parkingLot = new ParkingLot("south",10);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        Car car = createMockCar("BWM");
        InOrderParkingStrategy parkingStrategy = Mockito.spy(new InOrderParkingStrategy());
        //when
        Receipt receipt = parkingStrategy.park(parkingLots, car);
        //then
        verify(parkingStrategy,times(1)).createReceipt(parkingLot,car);
    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */
        ParkingLot parkingLot = new ParkingLot("south",1);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        Car car = createMockCar("BWM");
        InOrderParkingStrategy parkingStrategy = Mockito.spy(new InOrderParkingStrategy());
        parkingStrategy.park(parkingLots,car);
        //then
        verify(parkingStrategy,times(1)).createReceipt(parkingLot,car);
    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */
        ParkingLot parkingLot = new ParkingLot("south",1);
        ParkingLot parkingLot1 = new ParkingLot("as",1);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingLot1);
        Car car = createMockCar("BWM");
        Car car1 = createMockCar("Bens");
        InOrderParkingStrategy parkingStrategy = Mockito.spy(new InOrderParkingStrategy());
        parkingStrategy.park(parkingLots,car);
        //when
        parkingStrategy.park(parkingLots, car1);
        //then
        verify(parkingStrategy,times(1)).createReceipt(parkingLot1,car1);
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }


}
