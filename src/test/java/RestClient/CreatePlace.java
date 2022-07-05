package RestClient;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import RequestBody.AddPlaceBody;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class CreatePlace 
{
	
	
    @SuppressWarnings("deprecation")
	@Test(priority=1)
	public  void testAddPlace() throws IOException
	{
    	String fpath = "C:\\Users\\pvbra\\eclipse-workspace\\BackEndAutomation\\src\\test\\resources\\config.properties";
    	FileInputStream fis = new FileInputStream(fpath);
    	Properties prop = new Properties();
    	prop.load(fis);  
       RestAssured.baseURI = "https://rahulshettyacademy.com";
       String response = given()
           .log().all().header("Content-Type", "application/json").queryParam("key", "qaclick123").body(AddPlaceBody.getBody())
       .when()
           .post("maps/api/place/add/json")
        .then()
           .log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
           .header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
       System.out.println("The Response = " + response);
       JsonPath js = new JsonPath(response);
       String placeId = js.getString("place_id");
      
       System.out.println("The Extracted PlaceId = " + placeId);
       
       FileOutputStream fos = new FileOutputStream(fpath);
       prop.setProperty("PlaceId", placeId);
       prop.save(fos, "PlaceId File Updated");
       fos.close();
	   String place = prop.getProperty("PlaceId");
	   System.out.println("The PlaceId is = " + place);
            

	}
    
    @SuppressWarnings("deprecation")
	@Test(priority=2)
    public void testUpdatePlace() throws IOException
    {
    	String fpath = "C:\\Users\\pvbra\\eclipse-workspace\\BackEndAutomation\\src\\test\\resources\\config.properties";
    	FileInputStream fis = new FileInputStream(fpath);
    	Properties prop = new Properties();
    	prop.load(fis);
    	 String place = prop.getProperty("PlaceId");
    	 String initadd = "HNO:12-12,ChetPut,Chennai-700001";
  	   System.out.println("The PlaceId is = " + place);
  	   
  	 FileOutputStream fos = new FileOutputStream(fpath);
     prop.setProperty("InitAdd", initadd);
     prop.save(fos, "Address File Updated");
     fos.close();
    	RestAssured.baseURI = "https://rahulshettyacademy.com";
    	String response = given()
    	  .log().all().body("{\r\n" + 
    	  		"\"place_id\":\""+ place +"\" ,\r\n" + 
    	  		"\"address\":\"" + initadd +"\", \r\n" + 
    	  		"\"key\":\"qaclick123\"\r\n" + 
    	  		"}")
    	  .when().put("maps/api/place/update/json")
    	  .then().log().all().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"))
    	  .extract().response().asString();
    	System.out.println("The Response for Updated Place = " + response);
    	  //JsonPath js = new JsonPath(response);
		/*
		 * String add = js.getString("address");
		 * System.out.println("The Extracted Updated Address = " + add);
		 * FileOutputStream fos = new FileOutputStream(fpath);
		 * prop.setProperty("Address", add); prop.save(fos, "Address File Updated");
		 * fos.close(); String newadd = prop.getProperty("PlaceId");
		 * System.out.println("The PlaceId is = " + newadd);
		 */
    	
    	
    }
    
    @Test(priority=3)
    public void testGetPlace() throws IOException
    {
		
		  String fpath =
		  "C:\\Users\\pvbra\\eclipse-workspace\\BackEndAutomation\\src\\test\\resources\\config.properties";
		  FileInputStream fis = new FileInputStream(fpath); 
		  Properties prop = new   Properties(); 
		  prop.load(fis); 
		  String place = prop.getProperty("PlaceId");
		  System.out.println("The PlaceId is = " + place);
		  
		  String add = prop.getProperty("InitAdd");
		  System.out.println("The Updated Address is = " + add);
		 
		
  	   
  	 RestAssured.baseURI = "https://rahulshettyacademy.com";
  	   String response = given().log().all().header("Content-Type", "application/json")
  			   .queryParam("key", "qaclick123")
  			   .queryParam("place_id", place)
  	   .when().get("maps/api/place/get/json")
  	   .then().assertThat().statusCode(200).extract().response().asString();
  	   System.out.println("The Get Request Response = " + response);
  	   JsonPath js = new JsonPath(response);
  	   String newadd = js.getString("address");
  	   System.out.println("The Updated Address= " + newadd);
  	   Assert.assertEquals("Verification", add, newadd);
    }

}
