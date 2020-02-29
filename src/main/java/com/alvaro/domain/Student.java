package com.alvaro.domain;

import lombok.Data;

/**
 * This class stores data for a student.
 */
@Data
public class Student {
    private String name;
    private double latitude;
    private double longitude;
}
