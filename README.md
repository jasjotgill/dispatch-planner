# GTA Dispatch Planner

A route optimisation and drive-time coverage service built on the real road network of the Greater Toronto Area.

Given a depot and a set of service jobs, it answers two questions a mobile service dispatcher faces every morning:

- **Coverage:** which jobs can be reached within a given drive time, following real roads rather than straight lines (isochrones).
- **Sequence:** in what order one vehicle should visit its jobs to minimise drive time without breaking capacity or time-window constraints.

The road graph is built from OpenStreetMap data, and the shortest-path algorithms (Dijkstra and A*) are implemented from first principles.

## Status

In progress. Started October 2026.

## Tech stack

- **Backend:** Java 21, Spring Boot, Spring Data JPA, Flyway
- **Database:** PostgreSQL with PostGIS
- **Frontend:** React, TypeScript, MapLibre GL JS
- **Infrastructure:** Docker, GitHub Actions
- **Testing:** JUnit, Testcontainers
