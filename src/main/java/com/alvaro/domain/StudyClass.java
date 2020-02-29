package com.alvaro.domain;

import lombok.Data;

/**
 * This class stores data for a class (StudyClass is used in order to not have problems with Java reserved word).
 */
@Data
public class StudyClass {
    private String name;
    private double latitude;
    private double longitude;
    private String classroom;
}
