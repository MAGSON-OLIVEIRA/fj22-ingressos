package br.com.caelum.ingresso.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImagemCapa {
	@JsonProperty("Poster")
	private String url;

}
