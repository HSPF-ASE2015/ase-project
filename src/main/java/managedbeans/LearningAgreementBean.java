package managedbeans;

import controller.LearningAgreementController;
import controller.StudentController;
import fachklassen.Kurs;
import fachklassen.LearningAgreement;
import fachklassen.LearningAgreementPosition;
import fachklassen.Student;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author joe
 */
@Named
@SessionScoped
public class LearningAgreementBean implements Serializable {

    private Student student;
    private LearningAgreement learningAgreement;
    private List<Kurs> inlandsKurse;
    private List<Kurs> auslandsKurse;
    private Kurs inlandskurs;
    private Kurs auslandskurs;
   
    
    @EJB
    private StudentController studentController;
    @EJB
    private LearningAgreementController learningAgreementController;
    

    public LearningAgreementBean() {

    }
    
    @PostConstruct
    public void init() {
        student = studentController.getStudent();
    }
    
    public Boolean hasLearningAgreement() {
        return learningAgreementController.getLearningAgreement(student)!=null;
    }
        
    public String learningAgreementAnlegen() {
        learningAgreement = learningAgreementController.erstelleLearningAgreement(student);
        return "learningAgreementBearbeiten";
    }
    
    public String learningAgreementBearbeiten() {
        learningAgreement = learningAgreementController.getLearningAgreement(student);
        return "learningAgreementBearbeiten";
    }

    public void speichereLearningAgreement() {
        learningAgreementController.speichereLearningAgreement();
        //FacesContext.getCurrentInstance().renderResponse();
    }

    public void loeschePosition() {
        String posId = getRequestParameter("posId");
        System.out.println("Posititionsnummer " + posId);
        learningAgreement = learningAgreementController.loescheLearningAgreementPosition(Long.parseLong(posId));
        FacesContext.getCurrentInstance().renderResponse();
    }

    private String getRequestParameter(String parameter) {
        return FacesContext.getCurrentInstance().
                getExternalContext().
                getRequestParameterMap().get(parameter);
    }

    // Button von LA zu Kurs auswaehlen
    public String kursHinzufuegen() {
        inlandsKurse = learningAgreementController.getAlleInlandsKurse();
        return "inlandskursAnzeigen.xhtml";
    }

    public String inlandskursWaehlen() {
        String posId = getRequestParameter("kursId");

        for (Kurs k : inlandsKurse) {
            if (k.getKursId() == Long.parseLong(posId)) {
                inlandskurs = k;
                break;
            }
        }
        
        auslandsKurse = learningAgreementController.getAlleAuslandsKurse();
        
        if(!auslandsKurse.isEmpty()) {
            return "auslandskursAnzeigen.xhtml";
        } else {
            return null; // TODO: Error page 
        }
    }

    public String auslandkursWaehlen() {
        String posId = getRequestParameter("kursId");

        for (Kurs k : auslandsKurse) {
            if (k.getKursId() == Long.parseLong(posId)) {
                auslandskurs = k;
                break;
            }
        }
    
        learningAgreement.getLearningAgreementPositionen().add(new LearningAgreementPosition(inlandskurs, auslandskurs, learningAgreement));
        return "learningAgreementBearbeiten";
    }
    
    
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LearningAgreement getLearningAgreement() {
        return learningAgreement;
    }

    public void setLearningAgreement(LearningAgreement learningAgreement) {
        this.learningAgreement = learningAgreement;
    }
    
    public List<Kurs> getInlandsKurse() {
        return inlandsKurse;
    }

    public void setInlandsKurse(List<Kurs> inlandsKurse) {
        this.inlandsKurse = inlandsKurse;
    }

    public List<Kurs> getAuslandsKurse() {
        return auslandsKurse;
    }

    public void setAuslandsKurse(List<Kurs> auslandsKurse) {
        this.auslandsKurse = auslandsKurse;
    }

    public Kurs getInlandskurs() {
        return inlandskurs;
    }

    public void setInlandskurs(Kurs inlandskurs) {
        this.inlandskurs = inlandskurs;
    }

    public Kurs getAuslandskurs() {
        return auslandskurs;
    }

    public void setAuslandskurs(Kurs auslandskurs) {
        this.auslandskurs = auslandskurs;
    }
    
    
}
