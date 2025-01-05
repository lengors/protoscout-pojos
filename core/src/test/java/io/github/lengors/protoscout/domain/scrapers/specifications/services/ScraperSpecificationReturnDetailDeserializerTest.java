package io.github.lengors.protoscout.domain.scrapers.specifications.services;

import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecificationReturnDetail;
import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecificationReturnDescriptiveDetail;
import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecificationReturnDescriptionlessDetail;
import io.github.lengors.protoscout.domain.services.InferenceDeserializerTestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class ScraperSpecificationReturnDetailDeserializerTest
    extends InferenceDeserializerTestTemplate<ScraperSpecificationReturnDetail> {
  /**
   * Initializes test suite with for {@link ScraperSpecificationReturnDetail} base type.
   */
  ScraperSpecificationReturnDetailDeserializerTest() {
    super(ScraperSpecificationReturnDetail.class);
  }

  @MethodSource("sources")
  @ParameterizedTest
  void shouldCorrectlyDeserializeReturnDetail(
      final String modelName,
      final Class<? extends ScraperSpecificationReturnDetail> expectedType) throws IOException {
    test(String.format("models/specifications/%s", modelName), expectedType);
  }

  private static Stream<Arguments> sources() {
    return Stream.of(
        Arguments.of("return-detail-descriptive", ScraperSpecificationReturnDescriptiveDetail.class),
        Arguments.of("return-detail-descriptionless", ScraperSpecificationReturnDescriptionlessDetail.class));
  }
}
