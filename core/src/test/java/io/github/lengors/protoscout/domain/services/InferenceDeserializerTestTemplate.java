package io.github.lengors.protoscout.domain.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

/**
 * Template class for tests for deserialization using an implementation of {@link InferenceDeserializer}.
 *
 * @param <T> The base type to test for.
 * @author lengors
 */
public abstract class InferenceDeserializerTestTemplate<T> {
  /**
   * Object mapper helper for testing.
   */
  private final ObjectMapper objectMapper = new ObjectMapper();

  /**
   * Base class/interface to test for.
   */
  private final Class<T> valueClass;

  /**
   * Initializes test suite with given class/interface as the base one to test for.
   *
   * @param valueClass Base class/interface to test for.
   */
  protected InferenceDeserializerTestTemplate(final Class<T> valueClass) {
    this.valueClass = valueClass;
  }

  /**
   * Test for deserialization of model expecting for the given subtype of the base type to test for.
   *
   * @param modelName    The model name to test with.
   * @param expectedType The value class of the subtype of the base type to test for.
   * @throws IOException Thrown if reading value fails.
   */
  @SuppressWarnings("nullness")
  protected void test(final String modelName, final Class<? extends T> expectedType) throws IOException {
    final var resource = InferenceDeserializerTestTemplate.class.getResource(String.format("/%s.json", modelName));
    final var deserialized = objectMapper.readValue(resource, valueClass);
    Assertions.assertInstanceOf(expectedType, deserialized);
  }
}
