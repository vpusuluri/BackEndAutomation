package RestClient;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;


public class GetRequest 
{
	@Test(priority=1)
	public void testCase1()
	{
		Response response = RestAssured.get("https://reqres.in/api/users?page=2");
		int code = response.getStatusCode();
		String body = response.getBody().asString();
		String head = response.getHeader("content-type");
		String line = response.getStatusLine();
		long time = response.getTime();	
		String sid = response.getSessionId();
		String type = response.getContentType();
		ResponseBody bdy = response.getBody();
		System.out.println("The Response Time = " + time);
		System.out.println("The Response sessionId = " + sid);
		System.out.println("The Response Content Type = " + type);
		System.out.println("The Response Header = " + head);
		System.out.println("The Response Status Line = " + line);
		System.out.println("The Response Code = " + code);
		System.out.println("The Response Body = " + body);
		System.out.println("The Response Body Type = " + bdy);
		Assert.assertEquals(code, 200);
	}
	
	@Test(priority=2)
	public void testcase2()
	{
		 baseURI = "https://reqres.in/api";
		 given().
		         get("/users?page=2").
		 then().
		         statusCode(200).
		         body("data[1].id",equalTo(8))
		 .log().all();
		 
	}

	
  
}
