/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fachklassen.Hochschule;
import fachklassen.Kurs;
import fachklassen.LearningAgreement;
import fachklassen.Student;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import restfulServiceObjects.KursRESTServiceClient;
import restfulServiceObjects.KursWS;
import java.lang.reflect.Type;
import javax.ejb.Singleton;

/**
 *
 * @author Marcel
 */
@Singleton
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
        em.persist(learningAgreement);
        return learningAgreement;
    }
    
    public void speichereLearningAgreement (){
        em.merge(learningAgreement);
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

       
    public List<Kurs> getAlleAuslandsKurse(/* Hochschule partnerHochschule*/) {
        Hochschule partnerHochschule = new Hochschule(); // wird später in die FUnktion als Parameter übergeben
        
        Type listType = new TypeToken<ArrayList<KursWS>>() {}.getType();
        KursRESTServiceClient kursRESTServiceClient = new KursRESTServiceClient();
        String kurseString = kursRESTServiceClient.findAll(String.class); 
        List<KursWS> kurseWS = new Gson().fromJson(kurseString, listType);
        
        List<Kurs> kurseDB = new ArrayList<>();
        
        for(KursWS kursWS : kurseWS){
            
            if(kursWS.getErrorKurs() == null){
                kurseDB.add(new Kurs(kursWS.getKursId(), kursWS.getEcts(), kursWS.getName(), kursWS.getSprache(), partnerHochschule));
            } else {
                // TODO: error: exception?
            }
        }
       
        kursRESTServiceClient.close();
        
        return kurseDB;
        
//        try {
//            Query query = em.createNamedQuery("getAlleInlandsKurse");
//            query.setParameter("HeimatHS", "SanDiego"); // Hier die ausgewählte AuslandsHS eintragen!!!!
//            return query.getResultList();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
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
