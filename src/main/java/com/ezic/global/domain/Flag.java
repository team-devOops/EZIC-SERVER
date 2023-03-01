package com.ezic.global.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Flag {
    Y("Y"),
    N("N");

    public String value;
}
