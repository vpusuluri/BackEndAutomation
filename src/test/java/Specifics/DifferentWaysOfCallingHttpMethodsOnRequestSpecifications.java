package Specifics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DifferentWaysOfCallingHttpMethodsOnRequestSpecifications
{
	@Test
   public void getMethods()
   {
       RequestSpecification request = RestAssured.given();
       request.baseUri("https://restful-booker.herokuapp.com");
       request.basePath("/booking");
    
       Response response = request.get();
       System.out.println("Method1...." + response.asString());

       Response response1 = RestAssured.given(request).get();
       System.out.println("Method2....." + response1.asString());
      
       Response response2 = RestAssured.given().spec(request).get();
       System.out.println("Method3...." + response2.asString());
}
}