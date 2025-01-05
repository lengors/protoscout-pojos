package io.github.lengors.protoscout.domain.scrapers.specifications.services;

import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecificationAction;
import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecificationFlatAction;
import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecificationMapAction;
import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecificationRequestAction;
import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecificationReturnAction;
import io.github.lengors.protoscout.domain.services.InferenceDeserializerTestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class ScraperSpecificationActionDeserializerTest extends InferenceDeserializerTestTemplate<ScraperSpecificationAction> {
  /**
   * Initializes test suite with for {@link ScraperSpecificationAction} base type.
   */
  ScraperSpecificationActionDeserializerTest() {
    super(ScraperSpecificationAction.class);
  }

  @MethodSource("sources")
  @ParameterizedTest
  void shouldCorrectlyDeserializeAction(
      final String modelName,
      final Class<? extends ScraperSpecificationAction> expectedType) throws IOException {
    test(String.format("models/specifications/%s", modelName), expectedType);
  }

  private static Stream<Arguments> sources() {
    return Stream.of(
        Arguments.of("flat-action", ScraperSpecificationFlatAction.class),
        Arguments.of("map-action", ScraperSpecificationMapAction.class),
        Arguments.of("request-action", ScraperSpecificationRequestAction.class),
        Arguments.of("return-action", ScraperSpecificationReturnAction.class));
  }
}
