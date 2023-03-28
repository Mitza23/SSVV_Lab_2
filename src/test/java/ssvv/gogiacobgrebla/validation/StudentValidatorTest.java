package ssvv.gogiacobgrebla.validation;

import org.junit.Test;
import ssvv.gogiacobgrebla.domain.Student;

public class StudentValidatorTest {

    private final StudentValidator validator = new StudentValidator();

    private Student student = new Student("1", "Florin Albisoru", 933);


    @Test
    public void validate_successful() {
        validator.validate(student);
        assert true;
    }

    @Test(expected = ValidationException.class)
    public void validate_fail_wrongId_null() {
        student.setID(null);
        validator.validate(student);
    }

    @Test(expected = ValidationException.class)
    public void validate_fail_wrongId_empty() {
        student.setID("");
        validator.validate(student);
    }

    @Test(expected = ValidationException.class)
    public void validate_fail_wrongName_null() {
        student.setNume(null);
        validator.validate(student);
    }

    @Test(expected = ValidationException.class)
    public void validate_fail_wrongName_empty() {
        student.setNume("");
        validator.validate(student);
    }

    @Test(expected = ValidationException.class)
    public void validate_fail_wrongGroup_firstInterval_lowerBoundary() {
        student.setGrupa(-2147483648);
        validator.validate(student);
    }

    @Test(expected = ValidationException.class)
    public void validate_fail_wrongGroup_firstInterval_upperBoundary() {
        student.setGrupa(110);
        validator.validate(student);
    }

    @Test
    public void validate_success_group_secondInterval_lowerBoundary() {
        student.setGrupa(111);
        validator.validate(student);
    }

    @Test
    public void validate_success_group_secondInterval_upperBoundary() {
        student.setGrupa(937);
        validator.validate(student);
    }

    @Test(expected = ValidationException.class)
    public void validate_fail_wrongGroup_thirdInterval_lowerBoundary() {
        student.setGrupa(938);
        validator.validate(student);
    }

    @Test(expected = ValidationException.class)
    public void validate_fail_wrongGroup_thirdInterval_upperBoundary() {
        student.setGrupa(2147483647);
        validator.validate(student);
    }
}