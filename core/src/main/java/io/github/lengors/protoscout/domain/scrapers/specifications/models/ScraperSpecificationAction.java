package io.github.lengors.protoscout.domain.scrapers.specifications.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.github.lengors.protoscout.domain.scrapers.specifications.services.ScraperSpecificationActionDeserializer;

/**
 * Base type for scraper action in handler.
 *
 * @author lengors
 */
@JsonSubTypes({
  @JsonSubTypes.Type(ScraperSpecificationFlatAction.class),
  @JsonSubTypes.Type(ScraperSpecificationMapAction.class),
  @JsonSubTypes.Type(ScraperSpecificationRequestAction.class),
  @JsonSubTypes.Type(ScraperSpecificationReturnAction.class)
})
@JsonDeserialize(using = ScraperSpecificationActionDeserializer.class)
public sealed interface ScraperSpecificationAction extends Serializable
  permits ScraperSpecificationFlatAction, ScraperSpecificationMapAction, ScraperSpecificationRequestAction,
  ScraperSpecificationReturnAction {

}
