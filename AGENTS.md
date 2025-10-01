# Repository Guidelines

## Project Structure & Module Organization
- Application source lives in `src/main/java`; organize packages by bounded context (`com.john.parking.<feature>`) so controllers, services, and repositories stay co-located.
- Shared configuration, SQL seeds, and static assets belong in `src/main/resources`; add profile overrides as `application-<profile>.yaml`.
- Tests mirror production packages under `src/test/java`; reuse the `com.john.test` namespace for integration scaffolding.
- Gradle metadata sits in `build.gradle` and `settings.gradle`, while build artifacts and reports land in `build/`.

## Build, Test, and Development Commands
- `./gradlew clean build` — cleans prior outputs, compiles Java 21 sources, runs tests, and assembles the Boot JAR.
- `./gradlew bootRun` — runs the application with Spring DevTools reload for quick iteration.
- `./gradlew test` — executes JUnit 5 suites; pair with `--info` when diagnosing failures.
- `./gradlew check` — aggregates verification tasks, including future linting or static analysis plugins.

## Coding Style & Naming Conventions
- Follow Spring Boot idioms: constructor injection, `@Service`/`@Repository` stereotypes, and Lombok only when it meaningfully removes boilerplate.
- Use four-space indentation, brace-on-same-line formatting, and descriptive method names; leverage IDE formatters or Spotless (`./gradlew spotlessApply`) if enabled.
- Classes use PascalCase (`ParkingRateService`), beans use camelCase (`parkingLotRepository`), and configuration keys stay kebab-case (`parking.api.base-url`).

## Testing Guidelines
- Write unit tests with JUnit Jupiter and Mockito; use `@DataJpaTest` slices for persistence logic and `@SpringBootTest` for wiring checks.
- Name test classes `<ClassName>Tests` and methods `methodUnderTest_expectedOutcome`; annotate longer descriptions with `@DisplayName` where helpful.
- Maintain ≥80% coverage on service and controller layers; prefer the in-memory H2 datasource defined in `application.yaml` for database paths.

## Commit & Pull Request Guidelines
- Keep commits imperative and scope tagged (`feat: add reservation endpoint`), limiting subject lines to 72 characters.
- Reference issues with `Fixes #ID` where applicable and include context for exploratory work in the body.
- Pull requests should summarize intent, enumerate validation steps (`./gradlew test`), and attach screenshots for user-visible changes.
- Rebase onto `main` before requesting review to keep history linear; avoid merge commits unless resolving long-lived branches.

## Security & Configuration Tips
- Store credentials in environment variables or secret stores; never commit `.env` files or database dumps.
- Gate actuator endpoints and H2 console behind authentication when deployed beyond localhost.
- Document required environment variables in `README.md` and provide safe defaults via `application-local.yaml` when adding new configuration.
