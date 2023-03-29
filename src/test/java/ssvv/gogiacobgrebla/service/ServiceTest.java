package ssvv.gogiacobgrebla.service;

import org.junit.Test;
import ssvv.gogiacobgrebla.domain.Nota;
import ssvv.gogiacobgrebla.domain.Student;
import ssvv.gogiacobgrebla.domain.Tema;
import ssvv.gogiacobgrebla.repository.NotaXMLRepository;
import ssvv.gogiacobgrebla.repository.StudentXMLRepository;
import ssvv.gogiacobgrebla.repository.TemaXMLRepository;
import ssvv.gogiacobgrebla.validation.NotaValidator;
import ssvv.gogiacobgrebla.validation.StudentValidator;
import ssvv.gogiacobgrebla.validation.TemaValidator;
import ssvv.gogiacobgrebla.validation.Validator;

import static org.junit.Assert.*;

public class ServiceTest {

    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "src/main/resources/studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "src/main/resources/teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "src/main/resources/note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
    private String id = "1";
    private String nume = "Florin Albisoru";
    private int grupa = 933;

    @Test
    public void saveStudent_successful() {
        int result = service.saveStudent(id, nume, grupa);
        assertEquals(0, result);
    }

    @Test
    public void saveStudent_nullId() {
        int result = service.saveStudent(null, nume, grupa);
        assertEquals(1, result);
    }

    @Test
    public void saveStudent_emptyId() {
        int result = service.saveStudent("", nume, grupa);
        assertEquals(1, result);
    }

    @Test
    public void saveStudent_nullNume() {
        int result = service.saveStudent(id, null, grupa);
        assertEquals(1, result);
    }

    @Test
    public void saveStudent_emptyNume() {
        int result = service.saveStudent(id, "", grupa);
        assertEquals(1, result);
    }

    @Test
    public void saveStudent_wrongGroup_firstInterval_lowerBoundary() {
        int result = service.saveStudent(id, nume, -2147483648);
        assertEquals(1, result);
    }

    @Test
    public void saveStudent_firstInterval_upperBoundary() {
        int result = service.saveStudent(id, nume, 110);
        assertEquals(1, result);
    }

    @Test
    public void saveStudent_goodGroup_secondInterval_lowerBoundary() {
        int result = service.saveStudent(id, nume, 111);
        assertEquals(0, result);
    }

    @Test
    public void saveStudent_goodInterval_upperBoundary() {
        int result = service.saveStudent(id, nume, 937);
        assertEquals(0, result);
    }

    @Test
    public void saveStudent_wrongGroup_thirdInterval_lowerBoundary() {
        int result = service.saveStudent(id, nume, 938);
        assertEquals(1, result);
    }

    @Test
    public void saveStudent_thirdInterval_upperBoundary() {
        int result = service.saveStudent(id, nume, 2147483647);
        assertEquals(1, result);
    }
}