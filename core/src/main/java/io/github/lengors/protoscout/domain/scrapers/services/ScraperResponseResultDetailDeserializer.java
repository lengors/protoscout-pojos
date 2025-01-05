package io.github.lengors.protoscout.domain.scrapers.services;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
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
   * Constructor setting value type.
   *
   * @param valueType Value type.
   */
  public ScraperResponseResultDetailDeserializer(final JavaType valueType) {
    super(valueType);
  }

  /**
   * Constructor from another deserializer.
   *
   * @param source Source deserializer.
   */
  public ScraperResponseResultDetailDeserializer(final StdDeserializer<?> source) {
    super(source);
  }

  /**
   * Determines the concrete class of the node based on its tree.
   *
   * @param node The node to determine the concrete class for.
   * @return The concrete class.
   */
  @Override
  protected Class<? extends ScraperResponseResultDetail> inferType(final TreeNode node) {
    if (!(node instanceof JsonNode jsonNode)) {
      throw new IllegalArgumentException(String.format("Expected JsonNode, got %s", node));
    }

    if (jsonNode.hasNonNull("description")) {
      return ScraperResponseResultDescriptiveDetail.class;
    } else {
      return ScraperResponseResultDescriptionlessDetail.class;
    }
  }
}
