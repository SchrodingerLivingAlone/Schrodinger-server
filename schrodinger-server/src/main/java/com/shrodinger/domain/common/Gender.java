package com.shrodinger.domain.common;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@RequiredArgsConstructor
@Slf4j
public enum Gender {
    MALE(0, "남성"),
    FEMALE(1, "여성");

    private final Integer value;
    private final String gender;

    @JsonValue
    public String getGender() {
        return this.gender;
    }

    public static Gender fromString(String strGender) {

        for(Gender gender : Gender.values()){
            if(gender.toString().equalsIgnoreCase(strGender)){
                return strGender.equals("male")? Gender.MALE : Gender.FEMALE;
            }
        }
        return null;
    }
}
