package Specifics;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class UsingResponseSpecification
{
  ResponseSpecification responseSpecification=null;

 @BeforeClass
 public void setupResponseSpecification()
 {
   responseSpecification = RestAssured.expect();
   responseSpecification.contentType(ContentType.JSON);
   responseSpecification.statusCode(200);
   responseSpecification.time(Matchers.lessThan(5000L));
   responseSpecification.statusLine("HTTP/1.1 200 OK");
}

@Test
public void getAllBookings()
{
   RestAssured
   .given()
   .baseUri("https://restful-booker.herokuapp.com")
   .when()
   .get("/booking")
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