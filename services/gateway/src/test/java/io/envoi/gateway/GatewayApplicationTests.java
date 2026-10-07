package io.envoi.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8090/realms/reviewvault",
		"spring.security.oauth2.resourceserver.jwt.jwk-set-uri=http://localhost:8080/realms/reviewvault/protocol/openid-connect/certs"
})
class GatewayApplicationTests {

	@Test
	void contextLoads() {
	}

}
