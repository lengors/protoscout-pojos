package io.github.lengors.protoscout.domain.scrapers.specifications.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecificationJexlExpression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ScraperSpecificationJexlExpressionDeserializerTest {
  /**
   * Object mapper helper for testing.
   */
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void shouldCorrectlyDeserializedStructuredJexlExpression() throws JsonProcessingException {
    final var value = "expression";
    final var content = String.format("{\"jexl\": \"%s\"}", value);
    final var expression = objectMapper.readValue(content, ScraperSpecificationJexlExpression.class);
    Assertions.assertEquals(value, expression.getJexl());
  }

  @Test
  void shouldCorrectlyDeserializedTextualJexlExpression() throws JsonProcessingException {
    final var value = "expression";
    final var content = String.format("\"%s\"", value);
    final var expression = objectMapper.readValue(content, ScraperSpecificationJexlExpression.class);
    Assertions.assertEquals(String.format("'%s'", value), expression.getJexl());
  }

  @Test
  void shouldFailDeserializedArrayJexlExpression() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      objectMapper.readValue("[]", ScraperSpecificationJexlExpression.class);
    });
  }

  @Test
  void shouldFailDeserializedInvalidStructuedJexlExpression() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      objectMapper.readValue("{\"jexl\":[]}", ScraperSpecificationJexlExpression.class);
    });
  }
}
