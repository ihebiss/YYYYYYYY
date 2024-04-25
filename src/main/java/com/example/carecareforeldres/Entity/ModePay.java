package com.example.carecareforeldres.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ModePay {
    @JsonProperty("CARTE_BANCAIRE")
    CARTE_BANCAIRE,
    @JsonProperty("CACHE")
    CACHE
}
