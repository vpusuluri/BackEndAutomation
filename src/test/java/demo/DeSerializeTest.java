package demo;
import static io.restassured.RestAssured.given;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import pojo.AddPlace;
public class DeSerializeTest 
{

	public static void main(String[] args) 
	{

		RestAssured.useRelaxedHTTPSValidation();
		
		AddPlace request = given().queryParam("key", "qaclick123").expect().defaultParser(Parser.JSON)
				.when()
				.get("https://rahulshettyacademy.com/maps/api/place/get/json").as(AddPlace.class);
	
	

		System.out.println("Status value is---->"+  request.toString());
		System.out.println("Message is---->"+ request.getPhone_number());

	}

}




