package io.github.lengors.protoscout.domain.scrapers.specifications.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.github.lengors.protoscout.domain.scrapers.specifications.services.ScraperSpecificationReturnExtractDetailDeserializer;

/**
 * Base type for scraper specification return extract detail entry.
 *
 * @author lengors
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
@JsonSubTypes({
  @JsonSubTypes.Type(ScraperSpecificationReturnDescriptiveDetail.class),
  @JsonSubTypes.Type(ScraperSpecificationReturnDescriptionlessDetail.class)
})
@JsonDeserialize(using = ScraperSpecificationReturnExtractDetailDeserializer.class)
public sealed interface ScraperSpecificationReturnExtractDetail extends ScraperSpecificationReturnDetail
  permits ScraperSpecificationReturnDescriptionlessDetail, ScraperSpecificationReturnDescriptiveDetail {

}
