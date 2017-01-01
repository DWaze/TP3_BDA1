package com.company;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author Dr. Chaouche
 */
public class TestManager {
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    Teacher chaouche, seddari;
    Course bda1, dam;
    Student ali, farouk, karim, salim;
    
    List<Course> courses;
    List<Teacher> teachers;
    List<Student> students;
        
    @Before
    public void setUp() {
    
        bda1 = new Course(21, "BDA1", 4, "Bases de données avancées 1 ...");
        dam = new Course(22, "DAM", 4, "Développement d'applications mobiles ...");
        courses = new ArrayList<>();
        courses.add(bda1);
        courses.add(dam); 
//      -----------------------------------------------------------  
        chaouche = new Teacher(11, "Ahmed", "Chaouche", "ac.chaouche@misc-umc.org", new Date(80,0,1), Teacher.GRADE_DOCTOR);
        seddari = new Teacher(12, "Noureddine", "Seddari", "noureddine.seddari@univ-constantine2.dz", new Date(80,0,1), Teacher.GRADE_DOCTOR);   
        teachers = new ArrayList<>();
        teachers.add(chaouche);
        teachers.add(seddari);
//      ------------------------------------------------------------  
        chaouche.addCourse(bda1);
        chaouche.addCourse(dam);
        seddari.addCourse(bda1);
//      ------------------------------------------------------------  
        ali = new Student(1, "Ali", "Benmostafa", "ali.benmostafa@gmail.com", new Date(95, 2, 10), "161612345678", 12.75);
        farouk = new Student(2, "Farouk", "Souleymen", "farouk.souleymen@sntf.dz", new Date(95, 9, 21), "161687654321", 14.21);
        karim = new Student(3, "Karim", "Boualam", "karim.boualam@hotmail.com", new Date(96, 11, 04), "161699998877", 10.01);
        salim = new Student(4, "Salim", "Mohamed", "salim.mohamed@yahoo.com", new Date(96, 1, 14), "161699996655", 10.01);
        students = new ArrayList<>();
        students.add(ali);
        students.add(farouk);
        students.add(karim);
        students.add(salim);
//      ------------------------------------------------------------  
        ali.addAddress(new Address(31, "87, Ali Mendjeli", "Constantine", "25000", "Algeria"));
        farouk.addAddress(new Address(32, "21,23 Boulevard Mohamed V", "Alger", "16000", "Algeria"));
//      ------------------------------------------------------------        
        bda1.addStudent(ali);
//        méthode équivalente à :
//        StudentCourse sc1 = new StudentCourse(ali, bda);
//        bda.addStudentCourse(sc1);
//        ali.addStudentCourse(sc1);

        bda1.addStudent(farouk);
//        méthode équivalente à :
//        StudentCourse sc2 = new StudentCourse(farouk, bda); 
//        bda.addStudentCourse(sc2);
//        farouk.addStudentCourse(sc2);
      
        karim.addCourse(bda1);       
//        méthode équivalente à :
//        StudentCourse sc3 = new StudentCourse(karim, bda);
//        bda.addStudentCourse(sc3);
//        karim.addStudentCourse(sc3);
        
        dam.addStudent(karim);
//        méthode équivalente à :
//        StudentCourse sc4 = new StudentCourse(karim, dam);
//        dam.addStudentCourse(sc3);
//        karim.addStudentCourse(sc3);

//      ------------------------------------------------------------  
        ali.getrStudentCourse().get(0).setNoteValue(9); // BDA1
        farouk.getrStudentCourse().get(0).setNoteValue(16); // BDA1
        karim.getrStudentCourse().get(0).setNoteValue(7.5); // BDA1
        karim.getrStudentCourse().get(1).setNoteValue(12.5); // DAM        
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testStudentEquals() { // Equivalence de deux étudiants
        
        assertNotNull(farouk);
        Student s = new Student();
        s.setId(2);
        assertEquals(s, farouk);
    }
     
    @Test
    public void testAddress(){    // Code postal de l'adresse de ali
        assertNotNull(ali);
        assertNotNull(ali.getrAddress());
        assertEquals("25000", ali.getrAddress().getZipCode());       
    }
     
     
    @Test
    public void testBDA1(){    //    Tous les étudiants qui sont inscrits dans le cours 'BDA1'
        assertNotNull(bda1);
        assertEquals(3, bda1.getrStudentCourse().size());       
    }
    
    @Test
    public void testNotEnrolled(){    //    Etudiants qui ne sont inscrits dans aucun cours
        List<Student> notEnrolledStudents = new ArrayList<>();
        for (Student s : students) {
            assertNotNull(s.getrStudentCourse());
            if(s.getrStudentCourse().isEmpty())
                notEnrolledStudents.add(s);
        }
        assertEquals(1, notEnrolledStudents.size());       
    }
    
    @Test
    public void testProfTeachers(){    //    Les enseignants ayant le grade 'Docteur' et qui enseigne moins de 2 cours
        List<Teacher> profTeachers = new ArrayList<>();
        for (Teacher t : teachers) {
            if(t.getGrade() == Teacher.GRADE_DOCTOR){
                assertNotNull(t.getrCourse());
                if(t.getrCourse().size() >= 2)
                    profTeachers.add(t);
            }
        }
        assertEquals(1, profTeachers.size());       
    }
    
    @Test
    public void testAdmittedStudents(){    //    Tous les étudiants qui ont une moyenne supérieur à 10

        List<Student> admittedStudents = new ArrayList<>();
        for (Student s : students) {
            if(s.getNoteAverage() >= 10)
                admittedStudents.add(s);
        }
        assertEquals(2, admittedStudents.size());       
    }   
}
