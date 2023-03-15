package stepdef;

import static io.restassured.RestAssured.*;

import java.util.List;

import getBooking_pojo_classes.GetBookingById;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import uri_resource.Resource_Endpoint;
import utils.Utility_Class;

public class GetBookings extends Utility_Class{
	
	Response getBookingReq;
	Response getBookingByIDReq;
	Response getBookingByName;
	Response getBookingByCheckInCheckOut;
	Response getPingHealthCheck;
	Response getPingHealthCheckResponse;
	
	String getBookingByIdFailedResponse;
	List getBookingByNameResponse;
	List getBookingByCheckIn_CheckOutResponse;
	
	GetBookingById getBookingByIdResponse;
	
	String getBookingRes;
	
	int ID;
	int countID;
	
	Resource_Endpoint Resource;
	JsonPath getBookingjson;

	@Given("User calls the Restful booker baseURI")
	public void user_calls_the_restful_booker_base_uri() throws Exception {
		
		req = given().
				spec(requestSpecBuilder()).
				header("Content-Type", "application/json");
		
	}
	
	@When("User use the {string} resource with the baseURI")
	public void user_use_the_resource_with_the_base_uri(String resource) {
		
		Resource = Resource_Endpoint.valueOf(resource);
		
		if(resource.equalsIgnoreCase("Booking")) {
		
			getBookingReq = req.
						log().all().
					when().
						get(Resource.getResource());
		
		}else if(resource.equalsIgnoreCase("getBookingByID")) {
			
			getBookingByIDReq = req.
						log().all().
						pathParam("bookingid", 1).
					when().
						get(Resource.getResource() + "/{bookingid}");
			
		}else if(resource.equalsIgnoreCase("getBookingByName")) {
			
			getBookingByName = req.
						log().all().
						queryParams("firstname", "Sally", "lastname", "Brown").
					when().
						get(Resource.getResource());
			
		}else if(resource.equalsIgnoreCase("getBookingByCheckInCheckOut")) {
			
			getBookingByCheckInCheckOut = req.
						log().all().
						queryParams("checkin", "2017-01-25", "checkout", "2022-12-25").
					when().
						get(Resource.getResource());
			
		}else if(resource.equalsIgnoreCase("getPingHealthCheck")) {
			
			getPingHealthCheck = req.
						log().all().
					when().
						get(Resource.getResource());
			
		}
		
	}
	
	@Then("Check the {string} response returns with a status code of {int} success")
	public void check_the_response_returns_with_a_status_code_of_success(String string, Integer int1) {
		
		getBookingRes = getBookingReq.
				then().
					log().all().
					spec(responseSpecBuilder()).
					extract().
					response().
					asString();
		
	}
	
	@Then("Also check the {string} response for Booking IDs")
	public void also_check_the_response_for_booking_i_ds(String string) {
		
		JsonPath getBookingjson = new JsonPath(getBookingRes);
		
		ID = getBookingjson.get("[0].bookingid");
		
		System.out.println("Booking ID at index 0: " + ID);
		
	}

	@Then("Check the Booking response returns status code of {int} success")
	public void check_the_booking_response_returns_status_code_of_success(Integer status_code) {
			
		getBookingByIdResponse = getBookingByIDReq.
				then().
					spec(responseSpecBuilder()).
					extract().
					response().
					as(GetBookingById.class);
			
	}

	@Then("Also check the response returned for specified booking ID")
	public void also_check_the_response_returned_for_specified_booking_id() {
		
		System.out.println("firstname: " + getBookingByIdResponse.getFirstname());
		System.out.println("lastname: " + getBookingByIdResponse.getLastname());
		System.out.println("total price:" + getBookingByIdResponse.getTotalprice());
		System.out.println("deposit paid: " + getBookingByIdResponse.isDepositpaid());
		System.out.println("Booking dates: ");
		System.out.println("Check-In: " + getBookingByIdResponse.getBookingdates().getCheckin());
		System.out.println("Check-Out: " + getBookingByIdResponse.getBookingdates().getCheckout());
		System.out.println("Additional needs: " + getBookingByIdResponse.getAdditionalneeds());
		
	}
	
	@Then("Check the Bookings by name response returns status code {int} success")
	public void check_the_bookings_by_name_response_returns_status_code_success(Integer int1) {
		
		getBookingByNameResponse = getBookingByName.
				then().
					spec(responseSpecBuilder()).
					extract().
					response().
					as(List.class);
		
	}

	@Then("Also count the total number of IDs returned in the response")
	public void also_count_the_total_number_of_i_ds_returned_in_the_response() {
		
		countID =getBookingByNameResponse.size(); 
			
		System.out.println("Total No. of Booking IDs related to specific Name: " + countID);
		
	}
	
	@Then("Check the response returned with status code of {int} success")
	public void check_the_response_returned_with_status_code_of_success(Integer int1) {
		
		getBookingByCheckIn_CheckOutResponse = getBookingByCheckInCheckOut.
				then().
					spec(responseSpecBuilder()).
					extract().
					response().
					as(List.class);
		
	}

	@Then("Also count the total number of Bookings between the Check-In and Check-Out date")
	public void also_count_the_total_number_of_bookings_between_the_check_in_and_check_out_date() {
		
		int countBookings = getBookingByCheckIn_CheckOutResponse.size();
		
		System.out.println("Total No. of Booking IDs between Check-In and Check-Out: " + countBookings);
		
	}
	
	@Then("Check the ping health reponse returned with status code {int} created")
	public void check_the_ping_health_reponse_returned_with_status_code_created(Integer int1) {
		
		getPingHealthCheckResponse = getPingHealthCheck.
				then().
					log().all().
					assertThat().
					statusCode(201).
					extract().
					response();
		
	}

}
