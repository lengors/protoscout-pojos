package io.github.lengors.protoscout.domain.scrapers.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.github.lengors.protoscout.domain.scrapers.services.ScraperResponseResultDetailDeserializer;

/**
 * Base type for a result detail entry.
 *
 * @author lengors
 */
@JsonSubTypes({
  @JsonSubTypes.Type(ScraperResponseResultDescriptiveDetail.class),
  @JsonSubTypes.Type(ScraperResponseResultDescriptionlessDetail.class)
})
@JsonDeserialize(using = ScraperResponseResultDetailDeserializer.class)
public sealed interface ScraperResponseResultDetail extends Serializable
  permits ScraperResponseResultDescriptiveDetail, ScraperResponseResultDescriptionlessDetail {

}
