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
import fachklassen.LearningAgreementPosition;
import fachklassen.Student;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import restfulServiceObjects.KursRESTServiceClient;
import restfulServiceObjects.KursWS;
import java.lang.reflect.Type;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.persistence.NoResultException;

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
    
    private final String HeimatHs = "Pforzheim";

    public LearningAgreementController() {
    }
    
    public void logout() {
        em.clear();
        student = null;
    }
    
    @SuppressWarnings("CallToPrintStackTrace")
    public LearningAgreement getLearningAgreement(Student student) {
        try {
            Query query = em.createNamedQuery("getLA");
            query.setParameter("antragid", student.getAntrag().getAntragId());
            learningAgreement = (LearningAgreement) query.getSingleResult();
            return learningAgreement;
            
        } catch (NoResultException ex) {
            return null;
            
        } catch (Exception e){
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
    
    public LearningAgreement loescheLearningAgreementPosition(Long posId) {
        
        int idx = 0;
        List<LearningAgreementPosition> laPosen = learningAgreement.getLearningAgreementPositionen();
        
        for(LearningAgreementPosition laPos : laPosen){
            
            if(laPos.getLaPosId().compareTo(posId) == 0){
                laPosen.remove(idx);
                break;
            }
            idx ++;
        }
        
        learningAgreement.setLearningAgreementPositionen(laPosen);

        return learningAgreement;
    }
    
    public LearningAgreement erstelleLearningAgreementPosition(Kurs inlandskurs, Kurs auslandskurs) {
        learningAgreement.anlegenLearningAgreementPosition(inlandskurs, auslandskurs);
        return learningAgreement;
    }
    
     public List<Kurs> getAlleInlandsKurse () {
        try {
            Query query = em.createNamedQuery("getKurseFromHs");
            //query.setParameter("HS", "Pforzheim");
            query.setParameter("HS", HeimatHs);
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

       
    public List<Kurs> getAlleAuslandsKurse() {
        Hochschule partnerHochschule = learningAgreement.getAntrag().getPartnerhochschule();
        List<Kurs> auslandsKurse; 
        try {
            
            auslandsKurse = getKurseFromDatabase(partnerHochschule);
            if(!auslandsKurse.isEmpty()){
                return auslandsKurse;
            } else {
                updateKurseFromWebservice(partnerHochschule);
                return getKurseFromDatabase(partnerHochschule);
            }
            
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    private List<Kurs> getKurseFromDatabase(Hochschule partnerHochschule){
        Query query = em.createNamedQuery("getKurseFromHs");
        query.setParameter("HS", partnerHochschule.getName());
        return query.getResultList();
    }
    
    private void updateKurseFromWebservice(Hochschule partnerHochschule){
        KursRESTServiceClient kursRESTServiceClient = new KursRESTServiceClient();
        String kurseString = kursRESTServiceClient.findAll(String.class); 
        
        Type listType = new TypeToken<ArrayList<KursWS>>() {}.getType();
        List<KursWS> kurseWS = new Gson().fromJson(kurseString, listType);
        
        for(KursWS kursWS : kurseWS){           
            if(kursWS.getErrorKurs() == null){
                em.persist(new Kurs(kursWS.getEcts(), kursWS.getName(), kursWS.getSprache(), partnerHochschule));
            } else {
                // TODO: Some what error handling -> throw exception
            }
        }     
        kursRESTServiceClient.close();       
    }

//    public List<Hochschule> getPartnerHS() {
//        try {
//            Query query = em.createNamedQuery("getPartnerHS");
//            query.setParameter("HeimatHS", "Pforzheim"); // Hier die Heimathochschule eintragen!!!!
//            return query.getResultList();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    
}
