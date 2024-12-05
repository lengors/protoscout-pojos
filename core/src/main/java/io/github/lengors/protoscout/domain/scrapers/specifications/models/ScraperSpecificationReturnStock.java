package io.github.lengors.protoscout.domain.scrapers.specifications.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base type for scraper request return stock action.
 *
 * @author lengors
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
  @JsonSubTypes.Type(ScraperSpecificationReturnExtractStock.class),
  @JsonSubTypes.Type(ScraperSpecificationReturnFlatStock.class)
})
public sealed interface ScraperSpecificationReturnStock extends Serializable
  permits ScraperSpecificationReturnExtractStock, ScraperSpecificationReturnFlatStock {

}
