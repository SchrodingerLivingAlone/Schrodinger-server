package com.shrodinger.domain.common;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Age {
    AGE10("10대"),
    AGE20("20대"),
    AGE30("30대"),
    AGE40("40대"),
    AGE50("50대"),
    AGE60("60대"),
    AGE70("70대"),
    AGE80("80대");

    private final String age;

    @JsonValue
    public String getAge() {
        return this.age;
    }

    public static Age fromString(String strAge) {
        if(strAge==""){
            return null;
        }

        for(Age age : Age.values()){
            if((age.getAge().charAt(0))==(strAge.charAt(0))){
                return age;
            }
        }
        return null;
    }
}
