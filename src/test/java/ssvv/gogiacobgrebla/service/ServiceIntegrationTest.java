package ssvv.gogiacobgrebla.service;

import org.junit.Before;
import org.junit.Test;
import ssvv.gogiacobgrebla.domain.Nota;
import ssvv.gogiacobgrebla.domain.Pair;
import ssvv.gogiacobgrebla.domain.Student;
import ssvv.gogiacobgrebla.domain.Tema;
import ssvv.gogiacobgrebla.repository.NotaXMLRepository;
import ssvv.gogiacobgrebla.repository.StudentXMLRepository;
import ssvv.gogiacobgrebla.repository.TemaXMLRepository;
import ssvv.gogiacobgrebla.validation.NotaValidator;
import ssvv.gogiacobgrebla.validation.StudentValidator;
import ssvv.gogiacobgrebla.validation.TemaValidator;
import ssvv.gogiacobgrebla.validation.Validator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class ServiceIntegrationTest {

    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "src/main/resources/studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "src/main/resources/teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "src/main/resources/note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
    private String studentId = "9";
    private String studentNume = "Florin Albisoru";
    private int studentGrupa = 933;

    private String temaId = "9";
    private String temaDescriere = "lorem ipsum";
    private int temaDeadline = 3;
    private int temaStartLin = 2;

    private String notaId = "9";

    private double notaVal = 9.55;

    private int notaPredata = 7;
    private String notaFeedback = "lorem ipsum";

    @Before
    public void clearRepository() throws IOException {
        fileRepository1.delete("9");
        fileRepository2.delete("9");
        fileRepository3.delete(new Pair<>("9", "9"));
    }

    @Test
    public void saveStudent_successful() {
        int result = service.saveStudent(studentId, studentNume, studentGrupa);
        assertEquals(1, result);
    }


    @Test
    public void saveTema_successful() {
        int result = service.saveTema(temaId, temaDescriere, temaDeadline, temaStartLin);
        assertEquals(1, result);
    }

    @Test
    public void saveNota_successful() {
        fileRepository3.delete(new Pair<>("4", "1"));
        int result = service.saveNota("4", "1", notaVal, notaPredata, notaFeedback);
        assertEquals(1, result);
    }

        @Test
    public void integrate_add_nota_student() {
        int result = service.saveStudent(studentId, studentNume, studentGrupa);
        assertEquals(1, result);
        result = service.saveTema(temaId, temaDescriere, temaDeadline, temaStartLin);
        assertEquals(1, result);
    }

    @Test
    public void integrate_add_nota_student_tema() {
        int result = service.saveStudent(studentId, studentNume, studentGrupa);
        assertEquals(1, result);
        result = service.saveTema(temaId, temaDescriere, temaDeadline, temaStartLin);
        assertEquals(1, result);
        result = service.saveNota(studentId, temaId, notaVal, notaPredata, notaFeedback);
        assertEquals(1, result);
    }
}