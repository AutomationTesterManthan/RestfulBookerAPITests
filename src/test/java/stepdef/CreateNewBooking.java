package stepdef;

import static io.restassured.RestAssured.*;

import org.junit.Assert;

import createBookingResponse_pojo.CreateBookingResponse;
import getBooking_pojo_classes.GetBookingById;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import testData.TestData;
import uri_resource.Resource_Endpoint;
import utils.Utility_Class;

public class CreateNewBooking extends Utility_Class{
	
	TestData testdata = new TestData();
	Resource_Endpoint Resource;
	
	Response createBookingReq;
	Response getBookingByIDReq;
	CreateBookingResponse createBookingResponse;
	GetBookingById getBookingByIDResponse;
	
	int bookingId;
	String expected_firstname;
	String expected_lastname;
	String actual_firstname;
	String actual_lastname;

	@Given("User calls the bookings base URI")
	public void user_calls_the_bookings_base_uri() throws Exception {

		req = given().
				spec(requestSpecBuilder());
		
	}
	
	@When("User use the {string} resource to create a new booking")
	public void user_use_the_resource_to_create_a_new_booking(String resource) {
		
		Resource = Resource_Endpoint.valueOf(resource);
		
		if(resource.equalsIgnoreCase("createBooking")) {
			
			createBookingReq = req.
						body(testdata.createBooking()).
						log().all().
					when().
						post(Resource.getResource());
			
		}
		
	}
	
	@Then("Check if create new booking response returned with status {int} OK")
	public void check_if_create_new_booking_response_returned_with_status_ok(Integer int1) {

		createBookingResponse = createBookingReq.
				then().
					log().all().
					spec(responseSpecBuilder()).
					extract().
					response().
					as(CreateBookingResponse.class);
		
		expected_firstname = createBookingResponse.getBooking().getFirstname();
		expected_lastname = createBookingResponse.getBooking().getLastname();
	}
	
	@Then("Validate the response with the new booking Id")
	public void validate_the_response_with_the_new_booking_id() {

		bookingId = createBookingResponse.getBookingid();
		System.out.println("Booking ID: " + bookingId);
		
	}
	
	@When("User use the {string} resource with the new booking Id to check if new booking is created")
	public void user_use_the_resource_with_the_new_booking_id_to_check_if_new_booking_is_created(String resource) {

		if(resource.equalsIgnoreCase("getBookingByID")) {
					
			getBookingByIDReq = req.
						pathParam("bookingid", bookingId).
						log().all().
					when().
						get(Resource.getResource() + "/{bookingid}");
					
		}
	}
	
	@Then("Check if the get booking response returned with status {int} OK")
	public void check_if_the_get_booking_response_returned_with_status_ok(Integer int1) {

		getBookingByIDResponse = getBookingByIDReq.
				then().
					log().all().
					spec(responseSpecBuilder()).
					extract().
					response().
					as(GetBookingById.class);
		
	}
	
	@Then("Verify and compare both the responses")
	public void verify_and_compare_both_the_responses() {

		actual_firstname = getBookingByIDResponse.getFirstname();
		actual_lastname = getBookingByIDResponse.getLastname();
		
		Assert.assertEquals(expected_firstname, actual_firstname);
		Assert.assertEquals(expected_lastname, actual_lastname);
		
	}

}
