package io.github.lengors.protoscout.domain.scrapers.specifications.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base type for scraper return detail entry.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
  @JsonSubTypes.Type(ScraperSpecificationReturnDescriptiveDetail.class),
  @JsonSubTypes.Type(ScraperSpecificationReturnDescriptionlessDetail.class)
})
public sealed interface ScraperSpecificationReturnDetail extends Serializable
  permits ScraperSpecificationReturnDescriptiveDetail, ScraperSpecificationReturnDescriptionlessDetail {

}
