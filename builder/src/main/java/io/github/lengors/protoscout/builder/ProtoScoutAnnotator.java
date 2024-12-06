package io.github.lengors.protoscout.builder;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jsonschema2pojo.GenerationConfig;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.github.lengors.js2pets.annotators.NullabilityAnnotator;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JType;

public class ProtoScoutAnnotator extends NullabilityAnnotator {
  private static final String BASE_PACKAGE = "io.github.lengors.protoscout.domain.scrapers.specifications";
  private static final String BASE_MODELS_PACKAGE = BASE_PACKAGE + ".models";
  private static final String BASE_SERVICES_PACKAGE = BASE_PACKAGE + ".services";

  private static final String SCAPER_SPECIFICATION_JEXL_EXPRESSION_CLASS =
    BASE_MODELS_PACKAGE + ".ScraperSpecificationJexlExpression";

  private static final String SCAPER_SPECIFICATION_MAP_ACTION_CLASS =
    BASE_MODELS_PACKAGE + ".ScraperSpecificationMapAction";

  private static final String SCAPER_SPECIFICATION_JEXL_EXPRESSION_DESERIALIZER_CLASS =
    BASE_SERVICES_PACKAGE + ".ScraperSpecificationJexlExpressionDeserializer";

  public ProtoScoutAnnotator(@NonNull final GenerationConfig generationConfig) {
    super(generationConfig);
  }

  @Override
  public void propertyField(
    final JFieldVar field,
    final JDefinedClass clazz,
    final String propertyName,
    final JsonNode propertyNode) {
    if (clazz
      .fullName()
      .equals(SCAPER_SPECIFICATION_MAP_ACTION_CLASS)
      && field
      .name()
      .equalsIgnoreCase("maps")) {
      field
        .annotate(JsonFormat.class)
        .param("with", JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }
  }

  @Override
  public void type(@NonNull final JType type) {
    super.type(type);
    if (type instanceof JDefinedClass jDefinedClass) {
      jDefinedClass.annotate(jDefinedClass
        .owner()
        .ref("io.github.lengors.protoscout.domain.annotations.Generated"));
      if (jDefinedClass
        .fullName()
        .equalsIgnoreCase(SCAPER_SPECIFICATION_JEXL_EXPRESSION_CLASS)) {
        jDefinedClass
          .annotate(JsonDeserialize.class)
          .param(
            "using",
            type
              .owner()
              .ref(SCAPER_SPECIFICATION_JEXL_EXPRESSION_DESERIALIZER_CLASS));
      }
    }
  }
}
