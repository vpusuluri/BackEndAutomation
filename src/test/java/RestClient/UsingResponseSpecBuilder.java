package RestClient;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class UsingResponseSpecBuilder
{
	ResponseSpecification responseSpecification=null;
	
	@BeforeClass
	public void setupResponseSpecification()
	{
		       responseSpecification = new ResponseSpecBuilder()
				
				.expectStatusCode(200)
				.expectStatusLine("HTTP/1.1 200 OK")
				.expectContentType(ContentType.JSON)
				.expectResponseTime(Matchers.lessThan(5000L))
				.build();
	}
	@Test
	public void getAllBookings()
	{
		RestAssured
		.given()
		.baseUri("https://restful-booker.herokuapp.com")
		.when()
		.then()
		.spec(responseSpecification)
		.body("size()",Matchers.greaterThan(5));
	}

	@Test
	public void getBookingDetailsWithInvalidFirstName()
	{
		RestAssured
		.given()
		.baseUri("https://restful-booker.herokuapp.com")
		.when()
		.get("/booking?firstname=jim")
		.then()
		.spec(responseSpecification)
		.body("size()",Matchers.equalTo(0));
	}
}