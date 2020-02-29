package com.alvaro;

import com.alvaro.businesslogic.finder.StudentFinder;
import com.alvaro.businesslogic.finder.StudentGeoFinder;
import com.alvaro.businesslogic.location.GeoLocationCalculator;
import com.alvaro.domain.Student;
import com.alvaro.domain.StudyClass;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
public class StudentFinderTest {
    private static List<Student> students;
    private static List<StudyClass> studyClasses;
    private static List<Student> students2;
    private static List<StudyClass> studyClasses2;
    private static List<Student> students3;
    private StudentFinder studentFinder;
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * Load test data
     * @throws IOException If an error occurs reading json fines
     */
    @BeforeClass
    public static void setupTestSuite() throws IOException {
        log.info("Loading test data....");
        students = mapper.readValue(
                Objects.requireNonNull(
                        StudentFinderTest.class.getClassLoader().getResourceAsStream("students.json")),
                new TypeReference<List<Student>>() {
                }
        );
        log.info("Students: {}", students);
        studyClasses = mapper.readValue(
                Objects.requireNonNull(
                        StudentFinderTest.class.getClassLoader().getResourceAsStream("classes.json")),
                new TypeReference<List<StudyClass>>() {
                }
        );
        log.info("Classes: {}", studyClasses);
        students2 = mapper.readValue(
                Objects.requireNonNull(
                        StudentFinderTest.class.getClassLoader().getResourceAsStream("students2.json")),
                new TypeReference<List<Student>>() {
                }
        );
        log.info("Students2: {}", students2);
        studyClasses2 = mapper.readValue(
                Objects.requireNonNull(
                        StudentFinderTest.class.getClassLoader().getResourceAsStream("classes2.json")),
                new TypeReference<List<StudyClass>>() {
                }
        );
        log.info("Classes2: {}", studyClasses2);
        students3 = mapper.readValue(
                Objects.requireNonNull(
                        StudentFinderTest.class.getClassLoader().getResourceAsStream("students3.json")),
                new TypeReference<List<Student>>() {
                }
        );
        log.info("Students3: {}", students3);
    }

    /**
     * Initialize studentFinder with an instance of LocationCalculator
     */
    @Before
    public void setup(){
        studentFinder = new StudentGeoFinder(new GeoLocationCalculator());
    }

    /**
     * First scenario for problem 2
     */
    @Test
    public void testStudentsInClassesFirstScenario_then_success() {
        List<Student> studentsInClasses = studentFinder.getStudentsInClasses(students, studyClasses);
        Assert.assertNotNull(studentsInClasses);
        Assert.assertFalse(studentsInClasses.isEmpty());
        Assert.assertEquals(3, studentsInClasses.size());
        Assert.assertTrue(studentsInClasses.contains(students.get(0)));
        Assert.assertTrue(studentsInClasses.contains(students.get(1)));
        Assert.assertTrue(studentsInClasses.contains(students.get(2)));
    }

    /**
     * Second scenario for problem 2
     */
    @Test
    public void testStudentsInClassesSecondScenario_then_success() {
        List<Student> studentsInClasses = studentFinder.getStudentsInClasses(students2, studyClasses);
        Assert.assertNotNull(studentsInClasses);
        Assert.assertFalse(studentsInClasses.isEmpty());
        Assert.assertEquals(1, studentsInClasses.size());
        Assert.assertFalse(studentsInClasses.contains(students2.get(0)));
        Assert.assertFalse(studentsInClasses.contains(students2.get(1)));
        Assert.assertTrue(studentsInClasses.contains(students2.get(2)));
    }

    /**
     * Scenario with empty result (no students in classrooms)
     */
    @Test
    public void testStudentsInClassesEmptyScenario_then_success() {
        List<Student> studentsInClassesEmpty = studentFinder.getStudentsInClasses(students2, studyClasses2);
        Assert.assertNotNull(studentsInClassesEmpty);
        Assert.assertTrue(studentsInClassesEmpty.isEmpty());
    }

    /**
     * Bonus scenario for Problem 2 (students in classes with minimum attendance)
     */
    @Test
    public void testStudentsInClassesBonusScenario_then_success() {
        List<Student> studentsInClasses = studentFinder.getStudentsInClassesWithMinimumAttendance(students3,
                studyClasses,
                2);
        Assert.assertNotNull(studentsInClasses);
        Assert.assertFalse(studentsInClasses.isEmpty());
        Assert.assertEquals(4, studentsInClasses.size());
        Assert.assertFalse(studentsInClasses.contains(students3.get(0)));
        Assert.assertTrue(studentsInClasses.contains(students3.get(1)));
        Assert.assertTrue(studentsInClasses.contains(students3.get(2)));
        Assert.assertTrue(studentsInClasses.contains(students3.get(3)));
        Assert.assertTrue(studentsInClasses.contains(students3.get(4)));
    }

    /**
     * Bonus scenario for Problem 2 (students in classes with minimum attendance with empty result)
     */
    @Test
    public void testStudentsInClassesBonusEmptyScenario_then_success() {
        List<Student> studentsInClasses = studentFinder.getStudentsInClassesWithMinimumAttendance(students,
                studyClasses,
                2);
        Assert.assertNotNull(studentsInClasses);
        Assert.assertTrue(studentsInClasses.isEmpty());
    }
}
