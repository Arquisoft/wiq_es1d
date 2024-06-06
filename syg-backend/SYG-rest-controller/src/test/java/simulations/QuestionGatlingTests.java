package simulations;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class QuestionGatlingTests extends Simulation {

	private static final String BASE_URL = "http://localhost:8080";

	HttpProtocolBuilder httpProtocol = http.baseUrl(BASE_URL).acceptHeader("application/json")
			.contentTypeHeader("application/json");

	ScenarioBuilder findAllQuestions = scenario("Encuentra todas las preguntas")
			.exec(http("findAllQuestions").get("/question").check(status().is(200)));
	ScenarioBuilder findQuestionById = scenario("Encuentra una pregunta por su id")
			.exec(http("findQuestionById").get("/question/id?id=200").check(status().is(200)));
	ScenarioBuilder findQuestionByCategory = scenario("Encuentra una pregunta por su categoria")
			.exec(http("findQuestionByCategory").get("/question/category?categoryId=1").check(status().is(200)));
	{
		setUp(findAllQuestions.injectOpen(incrementUsersPerSec(5).times(12).eachLevelLasting(5).startingFrom(10)),
				findQuestionById.injectOpen(incrementUsersPerSec(5).times(12).eachLevelLasting(5).startingFrom(10)),
				findQuestionByCategory
						.injectOpen(incrementUsersPerSec(5).times(12).eachLevelLasting(5).startingFrom(10)))
				.protocols(httpProtocol).maxDuration(300);

	}
}
