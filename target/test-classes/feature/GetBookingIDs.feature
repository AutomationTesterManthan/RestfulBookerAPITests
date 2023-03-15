Feature: Performing GET operation to get Booking IDs

Scenario: To get all the booking IDs using http GET method
	Given User calls the Restful booker baseURI
	When User use the "Booking" resource with the baseURI
	Then Check the "getBookingID" response returns with a status code of 200 success
	And Also check the "getBookingID" response for Booking IDs
	
Scenario: To get a specific booking using ID 
	Given User calls the Restful booker baseURI
	When User use the "getBookingByID" resource with the baseURI
	Then Check the Booking response returns status code of 200 success
	And Also check the response returned for specified booking ID
	
Scenario: To get all the booking IDs related to specific first and last name
	Given User calls the Restful booker baseURI
	When User use the "getBookingByName" resource with the baseURI
	Then Check the Bookings by name response returns status code 200 success
	And Also count the total number of IDs returned in the response
	
Scenario: To get all the booking IDs using Check-In and Check-Out date
	Given User calls the Restful booker baseURI
	When User use the "getBookingByCheckInCheckOut" resource with the baseURI 
	Then Check the response returned with status code of 200 success
	And Also count the total number of Bookings between the Check-In and Check-Out date
	
Scenario: Verify if the host is working 
	Given User calls the Restful booker baseURI
	When User use the "getPingHealthCheck" resource with the baseURI 
	Then Check the ping health reponse returned with status code 201 created
	