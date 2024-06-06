package simulations;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class CategoryGatlingTests extends Simulation {

	private static final String BASE_URL = "http://localhost:8080";

	HttpProtocolBuilder httpProtocol = http.baseUrl(BASE_URL).acceptHeader("application/json")
			.contentTypeHeader("application/json");

	ScenarioBuilder findAllCategories = scenario("Encuentra todas las categorias")
			.exec(http("findAllCategories").get("/category").check(status().is(200)));
	ScenarioBuilder findCategoryById = scenario("Encuentra una categoria por id")
			.exec(http("findCategoryById").get("/category/id?id=1").check(status().is(200)));
	{
		setUp(findAllCategories.injectOpen(
				// Incrementa el número de usuarios rápidamente
				incrementUsersPerSec(5).times(12) // Incremento de usuarios por segundo 12 veces (1 minuto)
						.eachLevelLasting(5) // Cada nivel dura 5 segundos
						.startingFrom(10) // Comienza con 10 usuarios por segundo
		), findCategoryById.injectOpen(
				incrementUsersPerSec(5).times(12).eachLevelLasting(5).startingFrom(10))).protocols(httpProtocol)
				.maxDuration(300);

	}
}
