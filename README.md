# Students finder
## Problem description:
Given each student has a geolocation lat/lon point, how would you determine which students are physically in any classroom?  

Write a function that returns the students if they are in a classroom.  

## Solution approach
As we have the geolocation and dimension of the classroom, we only need to calculate
the sides (top, bottom, left, right) geolocation of the classroom and check if the student
geolocation is inside the classroom. The result is the list of students that are present in one of the classrooms.

For the bonus, the approach is to have a map of classrooms and their attendance (the list of students present in the
classroom), and filter all the classrooms that have less than the expected attendance. The result is the list of
students in the classrooms with the minimum expected attendance. 

## Code organization
* domain package: Contains classes for data: Student, StudyClass, GeoLocationBox. The last one represents a classroom location and implements a method to check if a coordinate is in the box.
* businesslogic package: contains the classes dedicated to solve the problem (e.g. to get a list of students that are in classes).
  * location package: contains the interface and implementation used for dealing with the geo-location operations.
  * finder package: contains the interface and implementation (that use LocationCalculator) that checks if students are in classes. 
* StudentFinderTest class (in test): Contains test cases for the several scenarios
* resources directory (in test): contains json files with input data.

## Run the code
```mvn clean test```