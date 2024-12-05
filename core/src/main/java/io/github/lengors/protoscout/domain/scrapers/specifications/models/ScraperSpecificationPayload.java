package io.github.lengors.protoscout.domain.scrapers.specifications.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base type for scraper request payload.
 *
 * @author lengors
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
  @JsonSubTypes.Type(ScraperSpecificationDataPayload.class),
  @JsonSubTypes.Type(ScraperSpecificationJsonPayload.class)
})
public sealed interface ScraperSpecificationPayload extends Serializable
  permits ScraperSpecificationDataPayload, ScraperSpecificationJsonPayload {

}
