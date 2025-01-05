package io.github.lengors.protoscout.domain.scrapers.services;

import io.github.lengors.protoscout.domain.scrapers.models.ScraperResponseResultDescriptionlessDetail;
import io.github.lengors.protoscout.domain.scrapers.models.ScraperResponseResultDescriptiveDetail;
import io.github.lengors.protoscout.domain.scrapers.models.ScraperResponseResultDetail;
import io.github.lengors.protoscout.domain.services.InferenceDeserializerTestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class ScraperResponseResultDetailDeserializerTest
    extends InferenceDeserializerTestTemplate<ScraperResponseResultDetail> {
  /**
   * Initializes test suite with for {@link ScraperResponseResultDetail} base type.
   */
  ScraperResponseResultDetailDeserializerTest() {
    super(ScraperResponseResultDetail.class);
  }

  @MethodSource("sources")
  @ParameterizedTest
  void shouldCorrectlyDeserializeReturnDetail(
      final String modelName,
      final Class<? extends ScraperResponseResultDetail> expectedType) throws IOException {
    test(String.format("models/%s", modelName), expectedType);
  }

  private static Stream<Arguments> sources() {
    return Stream.of(
        Arguments.of("result-detail-descriptive", ScraperResponseResultDescriptiveDetail.class),
        Arguments.of("result-detail-descriptionless", ScraperResponseResultDescriptionlessDetail.class));
  }
}
