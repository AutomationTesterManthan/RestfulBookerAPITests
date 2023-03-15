package testData;

import authToken_pojo.AuthTokenRequest;
import createBooking_pojo.BookingDetails;
import createBooking_pojo.CreateBookingRequest;
import partialUpdateBooking_pojo.PartialUpdateRequest;
import updateBooking_pojo.BookingDates;
import updateBooking_pojo.UpdateBookingRequest;

public class TestData {
	
	public CreateBookingRequest createBooking() {
		
		CreateBookingRequest bookingdata = new CreateBookingRequest();
		bookingdata.setFirstname("Jim");
		bookingdata.setLastname("Brown");
		bookingdata.setTotalprice(111);
		bookingdata.setDepositpaid(true);
		BookingDetails bookingdates = new BookingDetails();
		bookingdates.setCheckin("2018-01-01");
		bookingdates.setCheckout("2019-01-01");
		bookingdata.setBookingdates(bookingdates);
		bookingdata.setAdditionalneeds("Breakfast");
		
		return bookingdata;
		
	}
	
	public AuthTokenRequest getAuthToken() {
		
		AuthTokenRequest authToken = new AuthTokenRequest();
		authToken.setUsername("admin");
		authToken.setPassword("password123");
		
		return authToken;
		
	}
	
	public UpdateBookingRequest updateBookingData() {
		
		UpdateBookingRequest updateBooking = new UpdateBookingRequest();
		updateBooking.setFirstname("Jimmy");
		updateBooking.setLastname("Anderson");
		updateBooking.setTotalprice(1201);
		updateBooking.setDepositpaid(true);
		BookingDates bookingDate = new BookingDates();
		bookingDate.setCheckin("2023-01-11");
		bookingDate.setCheckout("2023-01-15");
		updateBooking.setBookingdates(bookingDate);
		updateBooking.setAdditionalneeds("Lunch");
		
		return updateBooking;
		
	}
	
	public PartialUpdateRequest partialUpdateData() {
		
		PartialUpdateRequest partialUpdate = new PartialUpdateRequest();
		partialUpdate.setFirstname("Vin");
		partialUpdate.setLastname("Diesel");
		
		return partialUpdate;
		
	}

}
