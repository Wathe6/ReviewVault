package io.envoi.library;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"spring.kafka.bootstrap-servers=localhost:9093",
		"spring.kafka.listener.auto-startup=false",
		"eureka.client.enabled=false"
})
class LibraryApplicationTests {

	@Test
	void contextLoads() {
	}

}
