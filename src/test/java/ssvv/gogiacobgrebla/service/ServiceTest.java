package ssvv.gogiacobgrebla.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ssvv.gogiacobgrebla.domain.Nota;
import ssvv.gogiacobgrebla.domain.Student;
import ssvv.gogiacobgrebla.domain.Tema;
import ssvv.gogiacobgrebla.repository.*;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ServiceTest {

    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "src/main/resources/studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "src/main/resources/teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "src/main/resources/note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

//    @Mock
    StudentXMLRepository mockStudentRepository = mock(StudentXMLRepository.class);
//    @Mock
    TemaXMLRepository mockTemaRepository = mock(TemaXMLRepository.class);
//    @Mock
    NotaXMLRepository mockNotaRepository = mock(NotaXMLRepository.class);

    Service mockService = new Service(mockStudentRepository, mockTemaRepository, mockNotaRepository);



    private String studentId = "9";
    private String studentNume = "Florin Albisoru";
    private int studentGrupa = 933;

    private String temaId = "9";
    private String temaDescriere = "lorem ipsum";
    private int temaDeadline = 3;
    private int temaStartLin = 2;

    @Before
    public void clearRepository() throws IOException {
        fileRepository1.delete("9");
        fileRepository2.delete("9");
    }

    @Test
    public void saveStudent_successful() {
        int result = service.saveStudent(studentId, studentNume, studentGrupa);
        assertEquals(1, result);
    }

    @Test
    public void saveStudent_nullId() {
        int result = service.saveStudent(null, studentNume, studentGrupa);
        assertEquals(0, result);
    }

    @Test
    public void saveStudent_emptyId() {
        int result = service.saveStudent("", studentNume, studentGrupa);
        assertEquals(0, result);
    }

    @Test
    public void saveStudent_nullNume() {
        int result = service.saveStudent(studentId, null, studentGrupa);
        assertEquals(0, result);
    }

    @Test
    public void saveStudent_emptyNume() {
        int result = service.saveStudent(studentId, "", studentGrupa);
        assertEquals(0, result);
    }

    @Test
    public void saveStudent_wrongGroup_firstInterval_lowerBoundary() {
        int result = service.saveStudent(studentId, studentNume, -2147483648);
        assertEquals(0, result);
    }

    @Test
    public void saveStudent_firstInterval_upperBoundary() {
        int result = service.saveStudent(studentId, studentNume, 110);
        assertEquals(0, result);
    }

    @Test
    public void saveStudent_goodGroup_secondInterval_lowerBoundary() {
        int result = service.saveStudent(studentId, studentNume, 111);
        assertEquals(1, result);
    }

    @Test
    public void saveStudent_goodInterval_upperBoundary() {
        int result = service.saveStudent(studentId, studentNume, 937);
        assertEquals(1, result);
    }

    @Test
    public void saveStudent_wrongGroup_thirdInterval_lowerBoundary() {
        int result = service.saveStudent(studentId, studentNume, 938);
        assertEquals(0, result);
    }

    @Test
    public void saveStudent_wrongGroup_thirdInterval_upperBoundary() {
        int result = service.saveStudent(studentId, studentNume, 2147483647);
        assertEquals(0, result);
    }

    @Test
    public void saveTema_successful() {
        int result = service.saveTema(temaId, temaDescriere, temaDeadline, temaStartLin);
        assertEquals(1, result);
    }

    @Test
    public void saveTema_wrong_nullId() {
        int result = service.saveTema(null, temaDescriere, temaDeadline, temaStartLin);
        assertEquals(0, result);
    }

    @Test
    public void saveTema_wrong_nullDescriere() {
        int result = service.saveTema(temaId, null, temaDeadline, temaStartLin);
        assertEquals(0, result);
    }

     @Test
    public void saveTema_wrong_zeroDeadline() {
        int result = service.saveTema(temaId, temaDescriere, 0, temaStartLin);
        assertEquals(0, result);
    }

     @Test
    public void saveTema_wrong_zeroStartline() {
        int result = service.saveTema(temaId, temaDescriere, temaDeadline, 0);
        assertEquals(0, result);
    }

    @Test
    public void saveTema_wrong_duplicate() {
        service.saveTema(temaId, temaDescriere, temaDeadline, temaStartLin);
        int result = service.saveTema(temaId, temaDescriere, temaDeadline, temaStartLin);
        assertEquals(0, result);
    }

    @Test
    public void saveStudent_success_mock(){
        when(mockStudentRepository.save(any(Student.class))).thenReturn(null);
//        when(mockStudentRepository.save(any(Student.class))).then(AdditionalAnswers.returnsFirstArg());
        int result = mockService.saveStudent(studentId, studentNume, studentGrupa);
        assertEquals(1, result);
    }
}