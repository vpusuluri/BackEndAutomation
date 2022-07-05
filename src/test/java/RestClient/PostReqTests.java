package RestClient;

import org.json.simple.JSONObject;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PostReqTests 
{
	@Test
	public void test_1_post()
	{
		@SuppressWarnings("unused")
		JSONObject request = new JSONObject();
		request.put("name", "vijay");
		request.put("job","software engineer");
		System.out.println(request);
		System.out.println(request.toJSONString());
		
		given().
		  header("Content-type","application/json").
		  contentType(ContentType.JSON).
		  accept(ContentType.JSON).
		  body(request.toJSONString()).
		when().
		    post("https://reqres.in/api/users").
		     then().statusCode(201);
		    
		  
		
	}
	
	@Test
	public void test_2_put()
	{
		@SuppressWarnings("unused")
		JSONObject request = new JSONObject();
		request.put("name", "vijay");
		request.put("job","QAEngineer");
		System.out.println(request);
		System.out.println(request.toJSONString());
		
		given().
		  header("Content-type","application/json").
		  contentType(ContentType.JSON).
		  accept(ContentType.JSON).
		  body(request.toJSONString()).
		when().
		    put("https://reqres.in/api/users/2").
		     then().statusCode(200).log().all();
		    
		  
		
	}
	
	@Test
	public void test_3_patcb()
	{
		@SuppressWarnings("unused")
		JSONObject request = new JSONObject();
		request.put("name", "vijay");
		request.put("job","QAEngineer");
		System.out.println(request);
		System.out.println(request.toJSONString());
		
		given().
		  header("Content-type","application/json").
		  contentType(ContentType.JSON).
		  accept(ContentType.JSON).
		  body(request.toJSONString()).
		when().
		    put("https://reqres.in/api/users/2").
		     then().statusCode(200).log().all();
		    
		  
		
	}
	
	@Test
	public void test_4_delete()
	{
		
		given().
		  header("Content-type","application/json").
		  contentType(ContentType.JSON).
		  accept(ContentType.JSON).
		 
		when().
		    delete("https://reqres.in/api/users/2").
		     then().statusCode(204).log().all();
		    
		  
		
	}

}
