package simulations;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class UserGatlingTests extends Simulation {

	private static final String BASE_URL = "http://localhost:8080";

	HttpProtocolBuilder httpProtocol = http.baseUrl(BASE_URL).acceptHeader("application/json")
			.contentTypeHeader("application/json");

	ScenarioBuilder findUserById = scenario("Encuentra un usuario por su uuid").exec(
			http("findUserById").get("/user/userId?id=4366fdc8-b32d-46bc-9df8-2e8ce68f0743").check(status().is(200)));
	ScenarioBuilder findUserByName = scenario("Encuentra un usuario por nombre")
			.exec(http("findUserByName").get("/user/name/?userName=alvaro").check(status().is(200)));

	{
		setUp(findUserById.injectOpen(incrementUsersPerSec(5).times(12).eachLevelLasting(5).startingFrom(10)),
				findUserByName.injectOpen(incrementUsersPerSec(5).times(12).eachLevelLasting(5).startingFrom(10)))
				.protocols(httpProtocol).maxDuration(300);
	}
}
