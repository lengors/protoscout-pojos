package io.github.lengors.protoscout.domain.jexl.models;

import jakarta.validation.constraints.NotNull;

/**
 * Generic holder of JEXL expression.
 *
 * @author lengors
 */
public interface JexlExpressionSpecification {
  /**
   * Get JEXL expression.
   *
   * @return JEXL expression.
   */
  @NotNull
  String getJexl();
}
