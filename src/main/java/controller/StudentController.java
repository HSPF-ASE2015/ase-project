/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import fachklassen.LearningAgreement;
import fachklassen.Student;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Marcel
 */
@Stateful
public class StudentController {

    
    @PersistenceContext
    private EntityManager em;
    private Student student;
    private LearningAgreement la;
    
    public Student validateLogin(String bname, String pw) {
        try {
            Query query = em.createNamedQuery("getStudent");
            query.setParameter("bname", bname);
            student = (Student) query.getSingleResult();
            if (pw.equals(student.getPasswort())){
                 return student;
            }else {
                    return null;
                    }
          //  kunde = em.find(Kunde.class, kundenNummer);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
