package stepdef;

import static io.restassured.RestAssured.*;

import org.junit.Assert;

import authToken_pojo.AuthTokenResponse;
import getBooking_pojo_classes.GetBookingById;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import partialUpdateBooking_pojo.PartialUpdateResponse;
import testData.TestData;
import updateBooking_pojo.UpdateBookingResponse;
import uri_resource.Resource_Endpoint;
import utils.Utility_Class;

public class UpdateBooking extends Utility_Class{
	
	TestData testdata = new TestData();
	Resource_Endpoint Resource;
	
	AuthTokenResponse getAuthTokenResponse;
	UpdateBookingResponse updateBookingResponse;
	GetBookingById getBookingByIDResponse;
	PartialUpdateResponse partialUpdateResponse;
	GetBookingById verifyPartialUpdateResponse;
	
	RequestSpecification updateBookingheaderReq;
	RequestSpecification partialUpdateReq;
	
	Response getAuthTokenReq;
	Response updateBookingReq;
	Response getBookingIdReq;
	Response partialUpdateBookingReq;
	Response deleteBookingReq;
	Response deleteBookingResponse;
	
	static String token;
	
	String updateFirstName;
	String updateLastName;
	String updateAdditionalNeeds;
	
	String actualFirstName;
	String actualLastName;
	String actualAdditionalNeeds;
	
	String partialUpdateFirstname;
	String partialUpdateLastname;

	@Given("User call the base URI")
	public void user_call_the_base_uri() throws Exception {
		
		req = given().
				spec(requestSpecBuilder()).
				log().all();
		
	}
	
	@When("User use the {string} resource to get the Auth Token")
	public void user_use_the_resource_to_get_the_auth_token(String resource) {
		
		Resource = Resource_Endpoint.valueOf(resource);
		
		if(resource.equalsIgnoreCase("getAuthToken")) {
			
			getAuthTokenReq = req.
						body(testdata.getAuthToken()).
					when().
						post(Resource.getResource());
			
		}
		
	}
	
	@Then("Check the response returned as status {int} success")
	public void check_the_response_returned_as_status_success(Integer int1) {

		getAuthTokenResponse = getAuthTokenReq.
				then().
					extract().
					response().
					as(AuthTokenResponse.class);
		
	}
	
	@Then("Also extract the Auth token from the response")
	public void also_extract_the_auth_token_from_the_response() {
		
		token = getAuthTokenResponse.getToken();
		System.out.println("Token: " + token);
		
	}
	
	@Given("Use the header and update body payload")
	public void use_the_header_and_update_body_payload() {
		
		updateBookingheaderReq = req.
				accept("application/json").
				header("Cookie", "token=" + token).
				header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=").
				body(testdata.updateBookingData());
		
	}
	
	@When("User use the {string} resource to update the booking")
	public void user_use_the_resource_to_update_the_booking(String resource) {

		Resource = Resource_Endpoint.valueOf(resource);
		
		if(resource.equalsIgnoreCase("updateBooking")) {
			
			updateBookingReq = updateBookingheaderReq.
						pathParam("bookingid", 1).
					when().
						put(Resource.getResource() + "/{bookingid}");
			
		}else if(resource.equalsIgnoreCase("partialUpdateBooking")) {
			
			partialUpdateBookingReq = partialUpdateReq.
					pathParam("bookingid", 1).
				when().
					patch(Resource.getResource() + "/{bookingid}");
			
		}
		
	}

	@Then("Check the update booking response returns status {int} success")
	public void check_the_update_booking_response_returns_status_success(Integer int1) {
		
		updateBookingResponse = updateBookingReq.
				then().
					extract().
					response().
					as(UpdateBookingResponse.class);
		
		updateFirstName = updateBookingResponse.getFirstname();
		updateLastName = updateBookingResponse.getLastname();
		updateAdditionalNeeds = updateBookingResponse.getAdditionalneeds();
		
	}

	@When("User use the {string} resource to get the updated booking")
	public void user_use_the_resource_to_get_the_updated_booking(String resource) {
		
		Resource = Resource_Endpoint.valueOf(resource);
		
		if(resource.equalsIgnoreCase("getBookingByID")) {
			
			getBookingIdReq = req.
						pathParam("bookingid", 1).
					when().
						get(Resource.getResource() + "/{bookingid}");
			
		}else if(resource.equalsIgnoreCase("getPartialUpdateBooking")) {
			
			getBookingIdReq = req.
					pathParam("bookingid", 1).
				when().
					get(Resource.getResource() + "/{bookingid}");
			
		}
		
	}

	@Then("Verify the response returned status {int} success")
	public void verify_the_response_returned_status_success(Integer int1) {
		
		getBookingByIDResponse = getBookingIdReq.
				then().
					extract().
					response().
					as(GetBookingById.class);
		
		actualFirstName = getBookingByIDResponse.getFirstname();
		actualLastName = getBookingByIDResponse.getLastname();
		actualAdditionalNeeds = getBookingByIDResponse.getAdditionalneeds();
		
	}

	@Then("Also Compare the before and after update response")
	public void also_compare_the_before_and_after_update_response() {
		
		Assert.assertEquals(updateFirstName, actualFirstName);
		Assert.assertEquals(updateLastName, actualLastName);
		Assert.assertEquals(updateAdditionalNeeds, actualAdditionalNeeds);
		
	}
	
	@Given("User user partial update payload body")
	public void user_user_partial_update_payload_body() {
		
		partialUpdateReq = req.
				accept("application/json").
				header("Cookie", "token=" + token).
				header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=").
				body(testdata.partialUpdateData());
		
	}

	@Then("Check the partial update booking response with status code {int} success")
	public void check_the_partial_update_booking_response_with_status_code_success(Integer int1) {
		
		partialUpdateResponse = partialUpdateBookingReq.
				then().
					extract().
					response().
					as(PartialUpdateResponse.class);
		
	}

	@Then("Verify the partial Update response with status {int} success")
	public void verify_the_partial_update_response_with_status_success(Integer int1) {

		verifyPartialUpdateResponse = getBookingIdReq.
				then().
					log().all().
					extract().
					response().
					as(GetBookingById.class);
		
	}

	@Then("Also compare before and after partial booking update")
	public void also_compare_before_and_after_partial_booking_update() {

		partialUpdateFirstname = verifyPartialUpdateResponse.getFirstname();
		partialUpdateLastname = verifyPartialUpdateResponse.getLastname();
		
		Assert.assertNotSame(actualFirstName, partialUpdateFirstname);
		Assert.assertNotSame(actualLastName, partialUpdateLastname);
		
	}
	
	@When("User use the {string} resource to delete the booking")
	public void user_use_the_resource_to_delete_the_booking(String resource) {
		
		Resource = Resource_Endpoint.valueOf(resource);
		
		if(resource.equalsIgnoreCase("deleteBooking")) {
		
			deleteBookingReq = req.
					accept("application/json").
					header("Cookie", "token=" + token).
					header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=").
					pathParam("bookingid", 1).
				when().
					delete(Resource.getResource() + "/{bookingid}");
		
		}
		
	}

	@Then("Check the delete booking response returned as {int} created")
	public void check_the_delete_booking_response_returned_as_created(Integer int1) {
		
		deleteBookingResponse = deleteBookingReq.
				then().
					log().all().
					statusCode(201).
					extract().
					response();
		
	}

}
