package io.github.lengors.protoscout.builder;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jsonschema2pojo.Annotator;
import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.rules.Rule;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JType;

import io.github.lengors.js2pets.factories.EnhancedRuleFactory;

public class ProtoScoutRuleFactory extends EnhancedRuleFactory.ExcludeNoArgsConstructor {
  public ProtoScoutRuleFactory(
    final GenerationConfig generationConfig,
    final Annotator annotator,
    final SchemaStore schemaStore) {
    super(generationConfig, annotator, schemaStore);
  }

  public ProtoScoutRuleFactory(final GenerationConfig generationConfig) {
    this(generationConfig, new Jackson2Annotator(generationConfig), new SchemaStore());
  }

  public ProtoScoutRuleFactory() {
    this(new DefaultGenerationConfig());
  }

  @Override
  public @NonNull Rule<JPackage, JType> getObjectRule() {
    final var superObjectRule = super.getObjectRule();
    return (nodeName, node, parent, generatableType, currentSchema) -> {
      final var resultType = superObjectRule.apply(nodeName, node, parent, generatableType, currentSchema);
      if (resultType instanceof JDefinedClass jDefinedClass) {
        jDefinedClass
          .mods()
          .setFinal(true);
      }
      return resultType;
    };
  }
}
