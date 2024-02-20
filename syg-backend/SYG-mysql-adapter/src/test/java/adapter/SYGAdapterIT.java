package adapter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import syg.mysql.adapter.SYGAdapter;

@SpringBootTest
public class SYGAdapterIT {

	@Autowired
	private SYGAdapter SYGAdapter;

//	@Test
//	void saveQuestion() {
//		SYGAdapter.saveQuestion();
//	}
}
