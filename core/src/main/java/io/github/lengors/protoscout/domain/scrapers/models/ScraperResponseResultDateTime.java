package io.github.lengors.protoscout.domain.scrapers.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base type for a result date-time value.
 *
 * @author lengors
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
  @JsonSubTypes.Type(ScraperResponseResultDateTimeInstant.class),
  @JsonSubTypes.Type(ScraperResponseResultDateTimeRange.class)
})
public sealed interface ScraperResponseResultDateTime extends Serializable
  permits ScraperResponseResultDateTimeInstant, ScraperResponseResultDateTimeRange {

}
