package io.github.lengors.protoscout.domain.scrapers.services;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.lengors.protoscout.domain.scrapers.models.ScraperResponseResultDescriptionlessDetail;
import io.github.lengors.protoscout.domain.scrapers.models.ScraperResponseResultDescriptiveDetail;
import io.github.lengors.protoscout.domain.scrapers.models.ScraperResponseResultDetail;
import io.github.lengors.protoscout.domain.services.InferenceDeserializer;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Deserializer for {@link ScraperResponseResultDetail}.
 *
 * @author lengors
 */
public class ScraperResponseResultDetailDeserializer extends InferenceDeserializer<ScraperResponseResultDetail> {
  /**
   * Default constructor setting value class to null.
   */
  public ScraperResponseResultDetailDeserializer() {
    this((Class<?>)  null);
  }

  /**
   * Constructor setting value class.
   *
   * @param valueClass Value class.
   */
  @SuppressWarnings("nullness")
  public ScraperResponseResultDetailDeserializer(final @Nullable Class<?> valueClass) {
    super(valueClass);
  }

  /**
   * Determines the concrete class of the node based on its tree.
   *
   * @param node The node to determine the concrete class for.
   * @return The concrete class.
   */
  @Override
  protected Class<? extends ScraperResponseResultDetail> inferType(final JsonNode node) {
    return node.hasNonNull("description")
      ? ScraperResponseResultDescriptiveDetail.class
      : ScraperResponseResultDescriptionlessDetail.class;
  }
}
