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
public class LAController {

    public LAController() {
    }

    
    @PersistenceContext
    private EntityManager em;
    private Student student;
    private LearningAgreement la;

    
    public LearningAgreement getLA(Student student) {
     //   Long id1 = studentBean.getStudent().getAntrag().getId1();
        try {
            Query query = em.createNamedQuery("getLA");
            query.setParameter("antragid", student.getAntrag().getId1());
            la = (LearningAgreement) query.getSingleResult();
                 return la;
          //  kunde = em.find(Kunde.class, kundenNummer);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void speichereLA (LearningAgreement la){
        em.persist(la);
    }
    
    public LearningAgreement loeschenLAposition(String posId, LearningAgreement la) {
        la.getLearningAgreement_Liste().remove(Integer.parseInt(posId) - 1);
        long i;
        for (i = 0; i < la.getAnzahlPositionen(); i++) {
            la.getLearningAgreement_Liste().get((int)i).setId1(i + 1);
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
