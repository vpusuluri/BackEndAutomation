package Specifics;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class WithoutUsingResponseSpecification
{
  @Test
  public void getAllBookings()
  {
     RestAssured
     .given()
     .baseUri("https://restful-booker.herokuapp.com")
     .when()
     .get("/booking")
     .then()
     .contentType(ContentType.JSON)
     .time(Matchers.lessThan(5000L))
     .statusLine("HTTP/1.1 200 OK")
     .body("size()",Matchers.greaterThan(5));
}

@Test
public void getBookingDetailsWithInvalidFirstName()
{
   RestAssured
   .given()
   .baseUri("https://restful-booker.herokuapp.com")
   .when()
   .get("/booking?firstname=Rahul")
   .then()
   .contentType(ContentType.JSON)
   .time(Matchers.lessThan(5000L))
   .statusLine("HTTP/1.1 200 OK")
   .body("size()",Matchers.equalTo(0));
}
}