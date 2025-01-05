package io.github.lengors.protoscout.domain.scrapers.specifications.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.github.lengors.protoscout.domain.scrapers.specifications.services.ScraperSpecificationReturnDetailDeserializer;

/**
 * Base type for scraper return detail entry.
 */
@JsonSubTypes({
  @JsonSubTypes.Type(ScraperSpecificationReturnDescriptiveDetail.class),
  @JsonSubTypes.Type(ScraperSpecificationReturnDescriptionlessDetail.class)
})
@JsonDeserialize(using = ScraperSpecificationReturnDetailDeserializer.class)
public sealed interface ScraperSpecificationReturnDetail extends Serializable
  permits ScraperSpecificationReturnDescriptiveDetail, ScraperSpecificationReturnDescriptionlessDetail {

}
