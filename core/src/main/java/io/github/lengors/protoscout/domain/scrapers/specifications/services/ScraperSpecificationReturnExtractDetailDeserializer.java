package io.github.lengors.protoscout.domain.scrapers.specifications.services;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecificationReturnExtractDetail;
import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecificationReturnDescriptiveDetail;
import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecificationReturnDescriptionlessDetail;
import io.github.lengors.protoscout.domain.services.InferenceDeserializer;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Deserializer for {@link ScraperSpecificationReturnExtractDetail}.
 *
 * @author lengors
 */
public class ScraperSpecificationReturnExtractDetailDeserializer
    extends InferenceDeserializer<ScraperSpecificationReturnExtractDetail> {

  /**
   * Default constructor setting value class to null.
   */
  public ScraperSpecificationReturnExtractDetailDeserializer() {
    this((Class<?>) null);
  }

  /**
   * Constructor setting value class.
   *
   * @param valueClass Value class.
   */
  @SuppressWarnings("nullness")
  public ScraperSpecificationReturnExtractDetailDeserializer(final @Nullable Class<?> valueClass) {
    super(valueClass);
  }

  /**
   * Determines the concrete class of the node based on its tree.
   *
   * @param node The node to determine the concrete class for.
   * @return The concrete class.
   */
  @Override
  protected Class<? extends ScraperSpecificationReturnExtractDetail> inferType(final JsonNode node) {
    return node.hasNonNull("description")
      ? ScraperSpecificationReturnDescriptiveDetail.class
      : ScraperSpecificationReturnDescriptionlessDetail.class;
  }
}
