Feature: Creating a new booking using POST http method

Scenario: To create a new booking
	Given User calls the bookings base URI
	When User use the "createBooking" resource to create a new booking
	Then Check if create new booking response returned with status 200 OK
	And Validate the response with the new booking Id
	When User use the "getBookingByID" resource with the new booking Id to check if new booking is created 
	Then Check if the get booking response returned with status 200 OK
	And Verify and compare both the responses
	