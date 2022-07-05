package RestClient;

import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class XMLSchemaValidation 
{
	 @Test
     public void validateXMLSchema() throws IOException
     {
		 File file = new File("./Data/Request.xml");
		 if(file.exists())
			 System.out.println("File Exists");
		 
		 FileInputStream fis = new FileInputStream(file);
    	 String reqBody = IOUtils.toString(fis,"UTF-8");
		 
		 
				 RestAssured.baseURI = "http://www.dneonline.com/";
    	 RestAssured.given(). 
    	  contentType("text/xml"). 
    	  accept(ContentType.XML).
    	  body(reqBody).
    	   when().
    	     post("/calculator.asmx").
    	      then().
    	      statusCode(200).
    	      log().all().
    	       and().
    	            body("//*AddResult.text()", equalTo("5")).
    	       and().assertThat().
    	            body(matchesXsdInClasspath("Calculator.xsd"));
    	            
     }
}
