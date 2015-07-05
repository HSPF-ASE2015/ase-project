/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import fachklassen.LearningAgreement;
import fachklassen.Student;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Marcel
 */
@Stateless
public class StudentController {

    
    @PersistenceContext
    private EntityManager em;
    private Student student;
    private LearningAgreement la;
    
    public Student validateLogin(String bname, String pw) {
        try {
            Query query = em.createNamedQuery("getStudentWithLogin");
            query.setParameter("bname", bname);
            query.setParameter("pw", pw);
            student = (Student) query.getSingleResult();
            
            return student;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
