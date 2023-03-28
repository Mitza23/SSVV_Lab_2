package ssvv.gogiacobgrebla.repository;

import ssvv.gogiacobgrebla.domain.Student;
import ssvv.gogiacobgrebla.validation.*;

public class StudentRepository extends AbstractCRUDRepository<String, Student> {
    public StudentRepository(Validator<Student> validator) {
        super(validator);
    }
}

