package io.github.lengors.protoscout.builder;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

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

  private static final String SCRAPER_SPECIFICATION_JEXL_EXPRESSION_CLASS =
    BASE_MODELS_PACKAGE + ".ScraperSpecificationJexlExpression";

  private static final String SCRAPER_SPECIFICATION_FLAT_ACTION_CLASS =
    BASE_MODELS_PACKAGE + ".ScraperSpecificationFlatAction";
  private static final String SCRAPER_SPECIFICATION_MAP_ACTION_CLASS =
    BASE_MODELS_PACKAGE + ".ScraperSpecificationMapAction";
  private static final String SCRAPER_SPECIFICATION_RETURN_FLAT_DETAIL_CLASS =
    BASE_MODELS_PACKAGE + ".ScraperSpecificationReturnFlatDetail";
  private static final String SCRAPER_SPECIFICATION_RETURN_FLAT_STOCK_CLASS =
    BASE_MODELS_PACKAGE + ".ScraperSpecificationReturnFlatStock";

  private static final String SCRAPER_SPECIFICATION_JEXL_EXPRESSION_DESERIALIZER_CLASS =BASE_SERVICES_PACKAGE + ".ScraperSpecificationJexlExpressionDeserializer";

  private static final Function<String, Predicate<JFieldVar>> FIELD_PREDICATE_FACTORY = expectedFieldName -> field -> field
    .name()
    .equalsIgnoreCase(expectedFieldName);

  private static final Predicate<JFieldVar> MAPS_FIELD_PREDICATE = FIELD_PREDICATE_FACTORY.apply("maps");
  private static final Predicate<JFieldVar> FLATTENS_FIELD_PREDICATE = FIELD_PREDICATE_FACTORY.apply("flattens");

  private static final Map<String, Predicate<JFieldVar>> FIELD_PREDICATES = Map.of(
    SCRAPER_SPECIFICATION_MAP_ACTION_CLASS, MAPS_FIELD_PREDICATE,
    SCRAPER_SPECIFICATION_FLAT_ACTION_CLASS, FLATTENS_FIELD_PREDICATE,
    SCRAPER_SPECIFICATION_RETURN_FLAT_DETAIL_CLASS, FLATTENS_FIELD_PREDICATE,
    SCRAPER_SPECIFICATION_RETURN_FLAT_STOCK_CLASS, FLATTENS_FIELD_PREDICATE
  );

  public ProtoScoutAnnotator(@NonNull final GenerationConfig generationConfig) {
    super(generationConfig, false);
  }

  @Override
  public void propertyField(
    final JFieldVar field,
    final JDefinedClass clazz,
    final String propertyName,
    final JsonNode propertyNode) {
    final var predicate = FIELD_PREDICATES.get(clazz.fullName());
    if (predicate != null && predicate.test(field)) {
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
        .equalsIgnoreCase(SCRAPER_SPECIFICATION_JEXL_EXPRESSION_CLASS)) {
        jDefinedClass
          .annotate(JsonDeserialize.class)
          .param(
            "using",
            type
              .owner()
              .ref(SCRAPER_SPECIFICATION_JEXL_EXPRESSION_DESERIALIZER_CLASS));
      }
    }
  }
}
