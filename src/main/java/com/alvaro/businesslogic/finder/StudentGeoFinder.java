package com.alvaro.businesslogic.finder;

import com.alvaro.businesslogic.location.LocationCalculator;
import com.alvaro.domain.GeoLocationBox;
import com.alvaro.domain.Student;
import com.alvaro.domain.StudyClass;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements the solution for problem 2 and bonus problem
 */
@Slf4j
public class StudentGeoFinder implements StudentFinder {
    private final Double COURSE_HALF_SIZE = 10d;
    private final LocationCalculator locationCalculator;

    public StudentGeoFinder(LocationCalculator locationCalculator) {
        this.locationCalculator = locationCalculator;
    }

    /**
     * Check for students in classes
     *
     * @param students     List of students
     * @param studyClasses List of classes
     * @return List of students in classes
     */
    public List<Student> getStudentsInClasses(List<Student> students, List<StudyClass> studyClasses) {
        List<Student> studentsInClasses = new ArrayList<>();
        studyClasses.forEach(studyClass -> {
            GeoLocationBox geoLocationBox = locationCalculator.getGeoLocationBox(
                    studyClass.getLongitude(),
                    studyClass.getLatitude(),
                    COURSE_HALF_SIZE);
            students.forEach(student -> {
                if (geoLocationBox.isInBox(student.getLatitude(), student.getLongitude())) {
                    studentsInClasses.add(student);
                }
            });
        });
        log.info("studentsInClasses: {}", studentsInClasses);
        return studentsInClasses;
    }

    /**
     * Check for students in classes with minimum attendance
     *
     * @param students          List of students
     * @param studyClasses      List of studyClasses
     * @param minimumAttendance minimum attendance
     * @return List of students in classes with at least the minimum of students attendance
     */
    public List<Student> getStudentsInClassesWithMinimumAttendance(List<Student> students,
                                                                   List<StudyClass> studyClasses,
                                                                   int minimumAttendance) {
        Map<String, List<Student>> studentsInStudyClass = new HashMap<>();
        studyClasses.forEach(studyClass -> {
            GeoLocationBox geoLocationBox = locationCalculator.getGeoLocationBox(
                    studyClass.getLongitude(),
                    studyClass.getLatitude(),
                    COURSE_HALF_SIZE);
            students.forEach(student -> {
                if (geoLocationBox.isInBox(student.getLatitude(), student.getLongitude())) {
                    addStudentToStudyClassMap(studentsInStudyClass, studyClass.getClassroom(), student);
                }
            });
        });
        List<Student> studentsInClassesWithMinimumAttendance =
                filterStudentsByStudyClassAttendance(studentsInStudyClass, minimumAttendance);
        log.info("studentsInClassesWithMinimumAttendance: {}", studentsInClassesWithMinimumAttendance);
        return studentsInClassesWithMinimumAttendance;
    }

    /**
     * Add student to studyClassStudents map using classRoomName as key
     *
     * @param studyClassStudents Map of classRoomName -> list of attending students
     * @param classRoomName      Classroom name
     * @param student            Student instance
     */
    private void addStudentToStudyClassMap(Map<String, List<Student>> studyClassStudents,
                                           String classRoomName,
                                           Student student) {
        if (studyClassStudents.containsKey(classRoomName)) {
            studyClassStudents.get(classRoomName).add(student);
        } else {
            List<Student> attendingStudents = new ArrayList<>();
            attendingStudents.add(student);
            studyClassStudents.put(classRoomName, attendingStudents);
        }
    }

    /**
     * Return students in classes with at least minimum attendance
     *
     * @param studyClassStudents Map of studyClasses (classroomName) -> list of attending students
     * @param minimumAttendance  The minimum attendance for a studyClass
     */
    private List<Student> filterStudentsByStudyClassAttendance(Map<String, List<Student>> studyClassStudents,
                                                               int minimumAttendance) {
        List<Student> filteredStudents = new ArrayList<>();
        for (String classroomName : studyClassStudents.keySet()) {
            List<Student> attendingStudents = studyClassStudents.get(classroomName);
            if (attendingStudents.size() >= minimumAttendance) {
                filteredStudents.addAll(attendingStudents);
            }
        }
        return filteredStudents;
    }
}
