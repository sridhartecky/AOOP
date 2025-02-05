import java.util.*;

interface IStudent {
    String getName();
    int getId();
    List<Course> getCourses();
}

interface ICourse {
    String getCourseName();
    int getCourseId();
    List<Student> getEnrolledStudents();
}

interface IEnrollmentService {
    void enroll(Student student, Course course);
    void unenroll(Student student, Course course);
}


class Student implements IStudent {
    private final int id;
    private final String name;
    private final List<Course> courses;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }
}

class Course implements ICourse {
    private final int courseId;
    private final String courseName;
    private final List<Student> enrolledStudents;

    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.enrolledStudents = new ArrayList<>();
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public List<Student> getEnrolledStudents() {
        return Collections.unmodifiableList(enrolledStudents);
    }

    public void addStudent(Student student) {
        enrolledStudents.add(student);
    }

    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }
}


class EnrollmentService implements IEnrollmentService {
    public void enroll(Student student, Course course) {
        student.addCourse(course);
        course.addStudent(student);
    }

    public void unenroll(Student student, Course course) {
        student.removeCourse(course);
        course.removeStudent(student);
    }
}

// Dependency Injection (DIP) applied using constructor injection.
class SIS {
    private final IEnrollmentService enrollmentService;

    public SIS(IEnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    public void enrollStudent(Student student, Course course) {
        enrollmentService.enroll(student, course);
    }

    public void unenrollStudent(Student student, Course course) {
        enrollmentService.unenroll(student, course);
    }
}

// Main class to demonstrate functionality.
public class Main {
    public static void main(String[] args) {
        // Create students
        Student student1 = new Student(1, "Alice");
        Student student2 = new Student(2, "Bob");

        // Create courses
        Course course1 = new Course(101, "Mathematics");
        Course course2 = new Course(102, "Physics");

        // Create enrollment service
        IEnrollmentService enrollmentService = new EnrollmentService();

        // Create SIS
        SIS sis = new SIS(enrollmentService);

        // Enroll students in courses
        sis.enrollStudent(student1, course1);
        sis.enrollStudent(student2, course2);

        // Display enrolled students in a course
        System.out.println("Students enrolled in Mathematics:");
        for (Student student : course1.getEnrolledStudents()) {
            System.out.println(student.getName());
        }

        System.out.println("Students enrolled in Physics:");
        for (Student student : course2.getEnrolledStudents()) {
            System.out.println(student.getName());
        }

        // Unenroll a student and display the updated list
        sis.unenrollStudent(student1, course1);
        System.out.println("Updated students in Mathematics:");
        for (Student student : course1.getEnrolledStudents()) {
            System.out.println(student.getName());
        }
    }
}