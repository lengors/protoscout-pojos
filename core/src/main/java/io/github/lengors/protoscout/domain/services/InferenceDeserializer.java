package io.github.lengors.protoscout.domain.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;

/**
 * Base deserializer for implementing inference deserializers used for polymorphism.
 *
 * @param <T> Deserialization target type.
 * @author lengors
 */
public abstract class InferenceDeserializer<T> extends StdDeserializer<T> {

  /**
   * Constructor setting value class.
   *
   * @param valueClass Value class.
   */
  protected InferenceDeserializer(final Class<?> valueClass) {
    super(valueClass);
  }

  /**
   * Constructor setting value type.
   *
   * @param valueType Value type.
   */
  protected InferenceDeserializer(final JavaType valueType) {
    super(valueType);
  }

  /**
   * Constructor from another deserializer.
   *
   * @param source Source deserializer.
   */
  protected InferenceDeserializer(final StdDeserializer<?> source) {
    super(source);
  }

  /**
   * Deserializes node into the target type.
   *
   * @param parser  Parser used for reading JSON content
   * @param context Context that can be used to access information about this deserialization activity.
   *
   * @return Deserialized value.
   * @throws IOException Thrown if some deserialization or reading sub-task fails.
   */
  @Override
  @SuppressWarnings("unchecked")
  public T deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
    final var codec = parser.getCodec();
    final var node = codec.readTree(parser);

    final var properTypeValue = inferType(node);

    // TODO optimize
    final var type = TypeFactory
        .defaultInstance()
        .constructType(properTypeValue);
    final var config = context.getConfig();
    final var deserializer = BeanDeserializerFactory.instance.buildBeanDeserializer(context, type,
        config.introspect(type));
    if (deserializer instanceof ResolvableDeserializer resolvableDeserializer) {
      resolvableDeserializer.resolve(context);
    }

    try (var subParser = codec.treeAsTokens(node)) {
      if (subParser.currentToken() == null) {
        subParser.nextToken();
      }
      return (T) deserializer.deserialize(subParser, context);
    }
  }

  /**
   * Determines the concrete class of the node based on its tree.
   *
   * @param node The node to determine the concrete class for.
   * @return The concrete class.
   */
  protected abstract Class<? extends T> inferType(TreeNode node);
}
