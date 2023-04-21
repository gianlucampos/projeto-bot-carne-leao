package br.com.main.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ActiveEnum {

    AGNC("AGNC INVESTMENT CORP"),
    BRG("BLUEROCK RESIDENTIAL GROWTH REIT INC"),
    GOOD("GLADSTONE COMMERCIAL CORPORATION REAL ESTATE INVESTMENT TRUST"),
    LTC("LTC PROPERTIES INC"),
    JNJ("JOHNSON & JOHNSON CONSUMER INC");

    private final String description;
}
