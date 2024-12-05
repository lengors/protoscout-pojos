package io.github.lengors.protoscout.domain.scrapers.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base type for a result detail entry.
 *
 * @author lengors
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
  @JsonSubTypes.Type(ScraperResponseResultDescriptiveDetail.class),
  @JsonSubTypes.Type(ScraperResponseResultDescriptionlessDetail.class)
})
public sealed interface ScraperResponseResultDetail extends Serializable
  permits ScraperResponseResultDescriptiveDetail, ScraperResponseResultDescriptionlessDetail {

}
