package RequestBody;
public class AddPlaceBody 
{

	public static String getBody()
	{
		
		return "{\r\n" + 
           		"  \"location\": {\r\n" + 
           		"    \"lat\": -38.383494,\r\n" + 
           		"    \"lng\": 33.427362\r\n" + 
           		"  },\r\n" + 
           		"  \"accuracy\": 50,\r\n" + 
           		"  \"name\": \"Star House\",\r\n" + 
           		"  \"phone_number\": \"(+91) 911 893 3937\",\r\n" + 
           		"  \"address\": \"B164,IDPL Colony,Hyderabad\",\r\n" + 
           		"  \"types\": [\r\n" + 
           		"    \"WebStore\",\r\n" + 
           		"    \"Departmental stores\"\r\n" + 
           		"  ],\r\n" + 
           		"  \"website\": \"http://www.outlook.com\",\r\n" + 
           		"  \"language\": \"French-IN\"\r\n" + 
           		"}";
	}

}
