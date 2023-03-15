Feature: Update a booking using Auth token

Scenario: Get the Auth token
	Given User call the base URI
	When User use the "getAuthToken" resource to get the Auth Token
	Then Check the response returned as status 200 success
	And Also extract the Auth token from the response
	
Scenario: Update the booking using auth token
	Given User call the base URI
	And Use the header and update body payload
	When User use the "updateBooking" resource to update the booking
	Then Check the update booking response returns status 200 success
	When User use the "getBookingByID" resource to get the updated booking
	Then Verify the response returned status 200 success
	And Also Compare the before and after update response
	
Scenario: Update the booking partial using auth token
	Given User call the base URI
	And User user partial update payload body
	When User use the "partialUpdateBooking" resource to update the booking
	Then Check the partial update booking response with status code 200 success
	When User use the "getPartialUpdateBooking" resource to get the updated booking
	Then Verify the partial Update response with status 200 success
	And Also compare before and after partial booking update
	
Scenario: Delete the newly added booking
	Given User call the base URI
	When User use the "deleteBooking" resource to delete the booking
	Then Check the delete booking response returned as 201 created
	
	