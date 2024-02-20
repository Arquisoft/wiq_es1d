package adapter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import syg.mysql.adapter.QuestionAdapter;

@SpringBootTest
public class SYGAdapterIT {

	@Autowired
	private QuestionAdapter SYGAdapter;

//	@Test
//	void saveQuestion() {
//		SYGAdapter.saveQuestion();
//	}
}
