package Specifics;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class WithRequestSpecification
{
  RequestSpecification reqspecs;
  @BeforeClass
  public void setupRequestSpecification()
  {
     reqspecs = RestAssured.given()
     .baseUri("https://restful-booker.herokuapp.com")
     .basePath("/booking");
  }
  
  @Test
  public void getAllBookings()
  {
     RestAssured.given()
     .spec(reqspecs)
     .when()
     .then()
     .statusLine("HTTP/1.1 200 OK");
  }
  @Test
  public void getBookingDetailsWithInvalidFirstName()
  {
     RestAssured.given(reqspecs)
     .param("firstName","Rahul")
     .when()
     .then()
     .statusLine("HTTP/1.1 200 OK");
  }
}