package Specifics;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class WithRequestSpecBuilder
{
  public static RequestSpecBuilder builder;
  public static RequestSpecification requestSpec;
  @BeforeClass
  public void setupRequestSpecification()
  {
     builder = new RequestSpecBuilder();
     builder.setBaseUri("https://restful-booker.herokuapp.com");
     builder.setBasePath("/booking");
     requestSpec = builder.build();
  }
  
  @Test
  public void getAllBookings()
  {
     RestAssured.given()
     .spec(requestSpec)
     .when()
     .then()
     .statusLine("HTTP/1.1 200 OK");
  }
  @Test
  public void getBookingDetailsWithInvalidFirstName()
  {
     RestAssured.given(requestSpec)
     .param("firstName","Rahul")
     .when()
     .then()
     .statusLine("HTTP/1.1 200 OK");
  }
}