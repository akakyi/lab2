package edu.lab.back.util;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.NonNull;

import java.util.Arrays;

public enum ProfileTypeEnum implements NamedEnum {

    STUDENT("Студент"),

    TEACHER("Учитель");

    private String name;

    ProfileTypeEnum(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return this.name;
    }

    public static ProfileTypeEnum getEnumByName(@NonNull final String name) {
        final ProfileTypeEnum result = Arrays.stream(ProfileTypeEnum.values())
            .filter(it -> it.getName().equals(name))
            .findFirst()
            .orElse(null);

        return result;
    }

}
