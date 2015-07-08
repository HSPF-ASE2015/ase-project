/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import fachklassen.Hochschule;
import fachklassen.Kurs;
import fachklassen.LearningAgreement;
import fachklassen.Student;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Marcel
 */
@Stateful
public class LearningAgreementController {
    
    @PersistenceContext
    private EntityManager em;
    private Student student;
    private LearningAgreement learningAgreement;

    public LearningAgreementController() {
    }
    
    public LearningAgreement getLearningAgreement(Student student) {
        try {
            Query query = em.createNamedQuery("getLA");
            query.setParameter("antragid", student.getAntrag().getAntragId());
            learningAgreement = (LearningAgreement) query.getSingleResult();
            return learningAgreement;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public LearningAgreement erstelleLearningAgreement(Student student) {
        learningAgreement = new LearningAgreement(student.getAntrag());
        speichereLearningAgreement();
        return learningAgreement;
    }
    
    public void speichereLearningAgreement (){
        em.persist(learningAgreement);
    }
    
    public LearningAgreement loescheLearningAgreementPosition(String posId, LearningAgreement la) {
        la.getLearningAgreementPositionen().remove(Integer.parseInt(posId) - 1);
        long i;
        for (i = 0; i < la.getAnzahlPositionen(); i++) {
            la.getLearningAgreementPositionen().get((int)i).setLaPosId(i + 1);
        }
        return la;
    }
    
     public List<Kurs> getAlleInlandsKurse () {
        try {
            Query query = em.createNamedQuery("getAlleInlandsKurse");
            query.setParameter("HeimatHS", "Pforzheim"); // Hier die Heimathochschule eintragen!!!!
            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     
        public Kurs getKurs (Long posId) {
        try {
            Query query = em.createNamedQuery("getKurs");
            query.setParameter("wahlKurs", posId);
            return (Kurs) query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

        //Hier muss eigentlich der Webservice rein -- Als demo einfach mit DB-Abfrage von einer betimmten HS gemacht
    public List<Kurs> getAlleAuslandsKurse() {
        try {
            Query query = em.createNamedQuery("getAlleInlandsKurse");
            query.setParameter("HeimatHS", "SanDiego"); // Hier die ausgewählte AuslandsHS eintragen!!!!
            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Hochschule> getPartnerHS() {
        try {
            Query query = em.createNamedQuery("getPartnerHS");
            query.setParameter("HeimatHS", "Pforzheim"); // Hier die Heimathochschule eintragen!!!!
            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
