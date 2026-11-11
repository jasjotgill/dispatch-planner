# CLAUDE.md

## Commands

Run from `backend/`. Docker must be running for tests.

- `./mvnw verify` — build and run all tests
- `./mvnw test -Dtest=ClassName#method` — run a single test
- `./mvnw spring-boot:test-run` — run locally against a Testcontainers database

## Architecture

- `graph/` must not import from `dispatch/` or `web/`. The graph layer operates on dense node indices and travel seconds only, and is testable without a Spring context.
- The road network is read from PostGIS once at startup into an immutable CSR structure. Search code never traverses JPA entities.
- Routing, graph search, and route optimisation are implemented in-house. Do not introduce routing or graph libraries (GraphHopper, OSRM, JGraphT, pgRouting) or OSM import tools (osm2pgsql, osm2pgrouting).

## Database

- Flyway owns the schema; Hibernate runs with `ddl-auto: validate`.
- Migrations live in `backend/src/main/resources/db/migration/` as `V<n>__<description>.sql`. Never modify a committed migration.
- Spatial columns use PostGIS geometry types in SRID 4326, mapped via Hibernate Spatial to JTS types.

## Conventions

- Java: tab indentation, match surrounding style.
- New dependencies require justification in the PR description.
- Unit-test algorithm code without Spring. Integration tests use Testcontainers with the `postgis/postgis` image.
- OSM extracts (`*.osm.pbf`), `data/`, and `.env` are never committed.

## Git

- Branch from `main` as `feature/<name>` or `fix/<name>`; merge via pull request with CI passing.
- Commit subjects are short and imperative; use the body for the reasoning when it isn't obvious.
