package managedbeans;

import controller.LearningAgreementController;
import controller.StudentController;
import fachklassen.Hochschule;
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
    private List<Kurs> alleKurse;
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
        FacesContext.getCurrentInstance().renderResponse();
    }

    public void loeschePosition() {
        String posId = getRequestParameter("posId");
        System.out.println("Posititionsnummer " + posId);
        learningAgreement = learningAgreementController.loescheLearningAgreementPosition(posId, learningAgreement);
        FacesContext.getCurrentInstance().renderResponse();
    }

    private String getRequestParameter(String parameter) {
        return FacesContext.getCurrentInstance().
                getExternalContext().
                getRequestParameterMap().get(parameter);
    }

    // Button von LA zu Kurs auswaehlen
    public String kursHinzufuegen() {
        alleKurse = learningAgreementController.getAlleInlandsKurse();
        return "inlandskursAnzeigen.xhtml";
    }

    // Ausgewaehlten Inlandskurs finden und zwischenspeichern
    public String kursWahlspeichern() {
        String posId = getRequestParameter("kursId");
//        System.out.println("Kursnummer " + Long.parseLong(posId));
//        inlandskurs = learningAgreementController.getKurs(Long.parseLong(posId));
//        inlandskurs = alleKurse.get(Integer.parseInt(posId));

        //ausgewaehlten Kurs speichern
        for (Kurs k : alleKurse) {
            if (k.getKursId() == Long.parseLong(posId)) {
                inlandskurs = k;
            }
        };
        //FacesContext.getCurrentInstance().renderResponse();
        alleKurse = learningAgreementController.getAlleAuslandsKurse();
        return "auslandskursAnzeigen.xhtml";
    }

    // Auslandskurs speichern
    public String kursWahlspeichern_2() {
        String posId = getRequestParameter("kursId");
//        System.out.println("Kursnummer " + Long.parseLong(posId));
//        inlandskurs = learningAgreementController.getKurs(Long.parseLong(posId));
//        inlandskurs = alleKurse.get(Integer.parseInt(posId));

        //ausgewaehlten Kurs speichern
        for (Kurs k : alleKurse) {
            if (k.getKursId() == Long.parseLong(posId)) {
                auslandskurs = k;
            }
        };

        // Kurswahl im LA speichern      
        learningAgreement.getLearningAgreementPositionen().add(new LearningAgreementPosition(inlandskurs, auslandskurs));
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

    public List<Kurs> getAlleKurse() {
        return alleKurse;
    }

    public void setAlleKurse(List<Kurs> alleKurse) {
        this.alleKurse = alleKurse;
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
