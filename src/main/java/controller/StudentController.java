/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import fachklassen.Student;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Singleton
public class StudentController {

    
    @PersistenceContext
    private EntityManager em;
    private Student student;
    
    public Boolean validateLogin(String bname, String pw) {
        try {
            Query query = em.createNamedQuery("getStudentWithLogin");
            query.setParameter("bname", bname);
            query.setParameter("pw", pw);
            student = (Student) query.getSingleResult();
            
            return (student!=null);
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public StudentController() {
        this.student = new Student();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
}
