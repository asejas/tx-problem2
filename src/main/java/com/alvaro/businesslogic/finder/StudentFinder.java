package com.alvaro.businesslogic.finder;

import com.alvaro.domain.Student;
import com.alvaro.domain.StudyClass;

import java.util.List;

/**
 * This interface define methods finding students in a group of studyClasses (problem 2)
 */
public interface StudentFinder {
    /**
     * Check for students in classes
     *
     * @param students     List of students
     * @param studyClasses List of classes
     * @return List of students in classes
     */
    List<Student> getStudentsInClasses(List<Student> students, List<StudyClass> studyClasses);

    /**
     * Check for students in classes with minimum attendance
     *
     * @param students          List of students
     * @param studyClasses      List of studyClasses
     * @param minimumAttendance minimum attendance
     * @return List of students in classes with at least the minimum of students attendance
     */
    List<Student> getStudentsInClassesWithMinimumAttendance(List<Student> students,
                                                            List<StudyClass> studyClasses,
                                                            int minimumAttendance);

}
