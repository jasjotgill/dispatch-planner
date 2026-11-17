package com.jasjot.dispatch.health;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Reports the installed PostGIS version, so a healthy response confirms that
 * the database is reachable and the spatial extension is present.
 */
@Component
public class PostgisHealthIndicator implements HealthIndicator {

	private final JdbcTemplate jdbcTemplate;

	public PostgisHealthIndicator(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Health health() {
		try {
			String version = this.jdbcTemplate.queryForObject("SELECT PostGIS_Version()", String.class);
			return Health.up().withDetail("version", version).build();
		}
		catch (DataAccessException ex) {
			return Health.down(ex).build();
		}
	}

}
