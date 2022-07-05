package Specifics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class withoutUsingRequestSpecification
{
   @Test
   public void getAllBookings()
   {
       RestAssured.given()
       .log()
       .all()
       .baseUri("https://restful-booker.herokuapp.com")
       .basePath("booking")
       .when()
       
       .then()
       .statusLine("HTTP/1.1 200 OK");
   }
   
@Test
public void getBookingDetailsWithInvalidFirstName()
{
               RestAssured.given()
               .log()
               .all()
              .baseUri("https://restful-booker.herokuapp.com")
               .basePath("/booking")
               .param("firstName","Rahul")
               .when()
               .then()
               .statusLine("HTTP/1.1 200 OK");
}
}