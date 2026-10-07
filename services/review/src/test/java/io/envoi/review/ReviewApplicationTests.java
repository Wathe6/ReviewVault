package io.envoi.review;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"spring.kafka.bootstrap-servers=localhost:9092",
		"spring.kafka.listener.auto-startup=false",
		"eureka.client.enabled=false"
})
class ReviewApplicationTests {

	@Test
	void contextLoads() {
	}

}
