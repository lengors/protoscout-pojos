package io.github.lengors.protoscout.domain.scrapers.specifications.services;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecificationAction;
import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecificationFlatAction;
import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecificationMapAction;
import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecificationRequestAction;
import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecificationReturnAction;
import io.github.lengors.protoscout.domain.services.InferenceDeserializer;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Deserializer for {@link ScraperSpecificationAction}.
 *
 * @author lengors
 */
public class ScraperSpecificationActionDeserializer extends InferenceDeserializer<ScraperSpecificationAction> {

  /**
   * Default constructor setting value class to null.
   */
  public ScraperSpecificationActionDeserializer() {
    this((Class<?>) null);
  }

  /**
   * Constructor setting value class.
   *
   * @param valueClass Value class.
   */
  @SuppressWarnings("nullness")
  public ScraperSpecificationActionDeserializer(final @Nullable Class<?> valueClass) {
    super(valueClass);
  }

  /**
   * Determines the concrete class of the node based on its tree.
   *
   * @param node The node to determine the concrete class for.
   * @return The concrete class.
   */
  @Override
  protected Class<? extends ScraperSpecificationAction> inferType(final JsonNode node) {
    if (node.hasNonNull("requests")) {
      return ScraperSpecificationRequestAction.class;
    } else if (node.hasNonNull("returns")) {
      return ScraperSpecificationReturnAction.class;
    } else if (node.hasNonNull("flattens")) {
      return ScraperSpecificationFlatAction.class;
    } else {
      return ScraperSpecificationMapAction.class;
    }
  }
}
