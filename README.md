# Welcome to ProtoScout POJOs &middot; [![GitHub license](https://img.shields.io/github/license/lengors/protoscout-pojos?color=blue)](https://github.com/lengors/protoscout-pojos/blob/main/LICENSE) [![javadoc](https://javadoc.io/badge2/io.github.lengors/protoscout/javadoc.svg)](https://javadoc.io/doc/io.github.lengors/protoscout) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=lengors_protoscout-pojos&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=lengors_protoscout-pojos)

Welcome to the **protoscout-schemas** repository! This project provides with the generated POJOs from the [ProtoScout Schemas](https://github.com/lengors/protoscout-schemas) project, which defines the communication protocol for interacting with the [WebScout](https://github.com/lengors/webscout) microservice.


## Features

- **Request POJO**: POJO for the requests sent to the WebScout microservice.
- **Response POJOs**:
    - **Results POJO**: POJO for successful results emitted by the microservice.
    - **Errors POJO**: POJO for error messages.
- **Specification POJO**: POJO to define the scraping behavior, including parameters, authentication requirements, and how to scrap a particular website.

## Getting Started

This project is intended for use in projects that interact with the WebScout microservice, providing a ready-to-use library of POJOs for requests, responses, and scraper specifications.

### Installation

The POJOs are distributed as a Maven package. To include them in your project, add the following dependency to your `pom.xml`:

#### Maven

```xml
<dependency>
    <groupId>io.github.lengors</groupId>
    <artifactId>protoscout</artifactId>
    <version>${version}</version>
</dependency>
```

Alternatively, if you are using Gradle, you can include it by adding it as a dependency to your `build.gradle` script (example uses koltin script):

#### Gradle

```kotlin
dependencies {
    implementation("io.github.lengors:protoscout:$version")
    // Other dependencies
}
```

## Usage

Once installed, you can use the POJOs to make requests to the WebScout microservice:

### Request-response example

```java
import io.github.lengors.protoscout.domain.scrapers.models.ScraperRequest;
import io.github.lengors.protoscout.domain.scrapers.models.ScraperProfile;
import io.github.lengors.protoscout.domain.scrapers.models.ScraperInputs;
import io.github.lengors.protoscout.domain.scrapers.models.ScraperResponse;
import io.github.lengors.protoscout.domain.scrapers.models.ScraperResponseResult;
import io.github.lengors.protoscout.domain.scrapers.models.ScraperResponseError;
import java.util.List;

public class Example {
  public static void main(final String[] args) {
    final var inputs = new ScraperInputs();
    inputs.setAdditionalProperty("<some_input>", "<some_value>");
    final var request = new ScraperRequest("<search_term>", List.of(new ScraperProfile("<specification_name>", inputs)));
    final ScraperResponse response = simulateSendRequest(request);
    switch (response) {
      case ScraperResponseResult result -> System.out.println("Do something with result: " + result);
      case ScraperResponseError error -> System.out.println("Do something with error: " + error);
    }
  }
}
```

### Specification example

```java
import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecification;
import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecificationSettings;
import java.util.List;

public class Example {
  public static void example(final ScraperSpecificationSettings settings) {
    final var specification = new ScraperSpecification("<specification_name>", settings, List.of());
    final ScraperSpecification definedSpecification = simulateSendSpecification(request);
    System.out.println("Do something with specification: " + definedSpecification);
  }
}
```

## Documentation and Resources

For detailed guides and additional information, please refer to our [GitHub Wiki](https://github.com/lengors/protoscout-pojos/wiki).

If you wish to check the detailed API documentation, visit the [Javadoc](https://javadoc.io/doc/io.github.lengors/protoscout) page.

## Contributing

Contributions are welcome! Please refer to our [Contribution Guidelines](./CONTRIBUTING.md) for more information on how to get involved.

## License

This project is licensed under [Apache License Version 2.0](./LICENSE), which places it in the public domain.
