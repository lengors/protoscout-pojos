package io.github.lengors.protoscout.domain.scrapers.specifications.services;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import io.github.lengors.protoscout.domain.scrapers.specifications.models.ScraperSpecificationJexlExpression;

/**
 * Deserializer for scraper's expressions. If the node is a string, it creates a JEXL expression that simply returns a
 * string by wrapping the value in quotes and creates a {@link ScraperSpecificationJexlExpression} with it, otherwise if
 * the node is an object and has the {@code jexl} property, it creates a {@link ScraperSpecificationJexlExpression} with
 * it.
 */
public class ScraperSpecificationJexlExpressionDeserializer
  extends StdDeserializer<ScraperSpecificationJexlExpression> {

  /**
   * Default deserializer constructor.
   */
  protected ScraperSpecificationJexlExpressionDeserializer() {
    super(ScraperSpecificationJexlExpression.class);
  }

  /**
   * @param jsonParser             JSON parser holding parsing value.
   * @param deserializationContext Current deserialization context.
   * @return Deserialized scraper's JEXL expression POJO.
   * @throws IOException              If deserialization fails due to IO issue.
   * @throws IllegalArgumentException If unknown POJO structure.
   */
  @Override
  public ScraperSpecificationJexlExpression deserialize(
    final JsonParser jsonParser,
    final DeserializationContext deserializationContext) throws IOException {
    final JsonNode node = jsonParser
      .getCodec()
      .readTree(jsonParser);

    Objects.requireNonNull(node);
    if (node.isTextual()) {
      return new ScraperSpecificationJexlExpression(String.format("'%s'", node.asText()));
    }

    if (!node.isObject()) {
      throw new IllegalArgumentException(String.format("Expected object node, found %s", node.getNodeType()));
    }

    final var jexl = node.get("jexl");
    Objects.requireNonNull(jexl);
    if (!jexl.isTextual()) {
      throw new IllegalArgumentException(String.format("Expected textual node, found %s", node.getNodeType()));
    }

    return new ScraperSpecificationJexlExpression(jexl.asText());
  }

}
