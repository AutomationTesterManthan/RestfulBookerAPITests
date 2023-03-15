package uri_resource;

public enum Resource_Endpoint {
	
	Booking("/booking"),
	getBookingByID("/booking"),
	getBookingByName("/booking"),
	getBookingByCheckInCheckOut("/booking"),
	getPingHealthCheck("/ping"),
	createBooking("/booking"),
	getAuthToken("/auth"),
	updateBooking("/booking"),
	partialUpdateBooking("/booking"),
	getPartialUpdateBooking("/booking"),
	deleteBooking("/booking");
	
	private String resource;
	
	Resource_Endpoint(String resource){
		
		this.resource = resource;
		
	}
	
	public String getResource() {
		
		return resource;
		
	}

}
