package RestClient;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;
public class VideoGameAPITests 
{
   @Test(priority=1,invocationCount=1)
	public void getAllVideoGamesTest()
   {
	   Reporter.log("TestCase: getAllVideoGamesTest");
	   given().
	     when(). 
	      get("http://localhost:8080/app/videogames").
	       then().statusCode(200);
	   
   }
   
   
   @SuppressWarnings("unchecked")
   @Test(enabled = true,priority=2)
   public void addNewVideoGameTest()
   {
	   Reporter.log("TestCase: addNewVideoGameTest");
	  @SuppressWarnings("rawtypes")
	HashMap data = new HashMap();
	   data.put("id" , "1021");
	    data.put("name", "RobotMan");
	    data.put("releaseDate" , "2022-06-01");
	    data.put("reviewScore" , "1");
	    data.put("category" , "ETV");
	    data.put("rating" , "4Star");
	    
	   Response response =  
	    given().
	       contentType("application/json").
	       body(data).
	    when().
	       post("http://localhost:8080/app/videogames").
	    then().
	        statusCode(200).
	        log().body().
	        extract().response();
	   String jsonString = response.asString();
	   Assert.assertEquals(jsonString.contains("Record Added Successfully"), true);
	       
   }
   @Test(priority=3)
   public void getVideoGameTest()
   {
	   Reporter.log("TestCase: getVideoGameTest");
	   given().
	      when().
	        get("http://localhost:8080/app/videogames/1021").
	      then().
	        statusCode(200).
	        body("videoGame.id",equalTo("1021")).
	        body("videoGame.name",equalTo("RobotMan"));
	        
   }
   
   @SuppressWarnings("unchecked")
@Test(priority=4)
   public void updateVideoGameTest()
   {
	   Reporter.log("TestCase: updateVideoGameTest");
	   @SuppressWarnings("rawtypes")
		HashMap data = new HashMap();
		   data.put("id" , "1021");
		    data.put("name", "BillyGramMan");
		    data.put("releaseDate" , "2022-05-05");
		    data.put("reviewScore" , "55");
		    data.put("category" , "Thriller");
		    data.put("rating" , "5Star"); 
		    
		    given().
		       contentType("application/json").
		       body(data).
		    when().
		       put("http://localhost:8080/app/videogames/1021").
		    then().
		       statusCode(200).
		       log().body().
		       body("videoGame.id", equalTo("1021")).
		       body("videoGame.name", equalTo("BillyGramMan"));
		       
   }
   
   @Test(priority=5)
   public void deleteVideoGameTest()
   {
	   Reporter.log("TestCase: deleteVideoGameTest");
	   Response response = 
	   given().
	   contentType("application/json").
	      when().
	      delete("http://localhost:8080/app/videogames/1019").
	      then().
	        statusCode(200).
	        log().body().
	        extract().response();
	   String jsonString = response.asString();
	   Assert.assertEquals(jsonString.contains("Record Deleted Successfully"), true);
	       
   }
}
