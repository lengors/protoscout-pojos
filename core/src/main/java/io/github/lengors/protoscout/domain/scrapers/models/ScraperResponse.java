package io.github.lengors.protoscout.domain.scrapers.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base type of scraper's response.
 *
 * @author lengors
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
  @JsonSubTypes.Type(ScraperResponseError.class),
  @JsonSubTypes.Type(ScraperResponseResult.class),
})
public sealed interface ScraperResponse extends Serializable permits ScraperResponseError, ScraperResponseResult {

}
