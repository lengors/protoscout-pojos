package io.github.lengors.protoscout.domain.scrapers.specifications.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base type for scraper action in handler.
 *
 * @author lengors
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
  @JsonSubTypes.Type(ScraperSpecificationFlatAction.class),
  @JsonSubTypes.Type(ScraperSpecificationMapAction.class),
  @JsonSubTypes.Type(ScraperSpecificationRequestAction.class),
  @JsonSubTypes.Type(ScraperSpecificationReturnAction.class)
})
public sealed interface ScraperSpecificationAction extends Serializable
  permits ScraperSpecificationFlatAction, ScraperSpecificationMapAction, ScraperSpecificationRequestAction,
  ScraperSpecificationReturnAction {

}
