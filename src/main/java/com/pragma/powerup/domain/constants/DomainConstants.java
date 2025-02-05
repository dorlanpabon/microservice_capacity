package com.pragma.powerup.domain.constants;

public class DomainConstants {
    public static final String NAME_MUST_BE_LESS_THAN_50_CHARACTERS = "Name must be less than 50 characters";
    public static final String DESCRIPTION_MUST_BE_LESS_THAN_90_CHARACTERS = "Description must be less than 90 characters";
    public static final String TECHNOLOGY_ALREADY_EXISTS = "Technology already exists";

    DomainConstants() {
        throw new IllegalStateException("Utility class");
    }
}
