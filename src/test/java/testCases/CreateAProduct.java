package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;

public class CreateAProduct {
	@Test
	public void createAProduct() {

		Response response = given()
				// .log().all()
				.baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type", "application/json; charset=UTF-8").auth().preemptive()
				.basic("demo@techfios.com", "abc123").body(new File("src\\main\\java\\data\\CreatePayload.json")).when()
				// .log().all()
				.post("/create.php").then()
				// .log().all()
				.extract().response();

		int actualResponse = response.statusCode();
		System.out.println("ActualResponse :" + actualResponse);
		int expectedResponse = 201;
		Assert.assertEquals(actualResponse, expectedResponse);

		String actualHeader = response.getHeader("Content-Type");
		System.out.println("actualHeader :" + actualHeader);
		String expectedHeader = "application/json; charset=UTF-8";
		Assert.assertEquals(actualHeader, expectedHeader);

		String actualResponseBody = response.getBody().asString();
		System.out.println("actualResponseBody :" + actualResponseBody);

		JsonPath jp = new JsonPath(actualResponseBody);

		String actualMassage = jp.get("message");
		System.out.println("ActualMassage: " + actualMassage);
		Assert.assertEquals(actualMassage, "Product was created.");

	}

}
