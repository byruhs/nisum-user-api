package com.nisum.userapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nisum.userapi.domain.entity.PhoneEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class PhoneDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("number")
    private String number;

    @JsonProperty("citycode")
    private String cityCode;

    @JsonProperty("contrycode")
    private String countryCode;

    public PhoneDto() {
    }

    public PhoneDto(final PhoneEntity phoneEntity) {
        this.number = phoneEntity.getNumber();
        this.cityCode = phoneEntity.getCityCode();
        this.countryCode = phoneEntity.getCountryCode();
    }
}
