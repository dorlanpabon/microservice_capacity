package com.pragma.powerup.domain.constants;

public class DomainConstants {
    public static final String NAME_MUST_BE_LESS_THAN_50_CHARACTERS = "Name must be less than 50 characters";
    public static final String DESCRIPTION_MUST_BE_LESS_THAN_90_CHARACTERS = "Description must be less than 90 characters";
    public static final String CAPACITY_ALREADY_EXISTS = "Capacity already exists";
    public static final String EMPTY_TECHNOLOGY_LIST = "Technology list cannot be empty";
    public static final int MIN_TECHNOLOGIES = 3;
    public static final int MAX_TECHNOLOGIES = 20;
    public static final String INVALID_TECHNOLOGY_COUNT = "Invalid number of technologies";

    DomainConstants() {
        throw new IllegalStateException("Utility class");
    }
}
