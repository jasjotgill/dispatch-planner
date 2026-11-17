package com.jasjot.dispatch.health;

import static org.assertj.core.api.Assertions.assertThat;

import com.jasjot.dispatch.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
@Import(TestcontainersConfiguration.class)
class HealthEndpointIntegrationTests {

	@Autowired
	private RestTestClient client;

	@Test
	void healthEndpointReportsPostgisVersion() {
		this.client.get()
			.uri("/actuator/health")
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.status").isEqualTo("UP")
			.jsonPath("$.components.postgis.status").isEqualTo("UP")
			.jsonPath("$.components.postgis.details.version")
			.value(String.class, (version) -> assertThat(version).matches("\\d+\\.\\d+.*"));
	}

}
