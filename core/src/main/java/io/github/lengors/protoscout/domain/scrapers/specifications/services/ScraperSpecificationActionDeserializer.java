package io.github.lengors.protoscout.domain.scrapers.specifications.services;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
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
   * Constructor setting value type.
   *
   * @param valueType Value type.
   */
  public ScraperSpecificationActionDeserializer(final JavaType valueType) {
    super(valueType);
  }

  /**
   * Constructor from another deserializer.
   *
   * @param source Source deserializer.
   */
  public ScraperSpecificationActionDeserializer(final StdDeserializer<?> source) {
    super(source);
  }

  /**
   * Determines the concrete class of the node based on its tree.
   *
   * @param node The node to determine the concrete class for.
   * @return The concrete class.
   */
  @Override
  protected Class<? extends ScraperSpecificationAction> inferType(final TreeNode node) {
    if (!(node instanceof JsonNode jsonNode)) {
      throw new IllegalArgumentException(String.format("Expected JsonNode, got %s", node));
    }

    if (jsonNode.hasNonNull("requests")) {
      return ScraperSpecificationRequestAction.class;
    } else if (jsonNode.hasNonNull("returns")) {
      return ScraperSpecificationReturnAction.class;
    } else if (jsonNode.hasNonNull("flattens")) {
      return ScraperSpecificationFlatAction.class;
    } else {
      return ScraperSpecificationMapAction.class;
    }
  }
}
