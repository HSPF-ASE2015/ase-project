/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import controller.LAController;
import controller.StudentController;
import fachklassen.Hochschule;
import fachklassen.Kurs;
import fachklassen.LearningAgreement;
import fachklassen.LearningAgreement_Pos;
import fachklassen.Student;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Marcel
 */
@Named
@SessionScoped
public class StudentBean implements Serializable {

    private Student student;

    private LearningAgreement la;
    private List<Kurs> alleKurse;
    private List<Hochschule> partnerHochschulen;

    private Kurs inlandskurs;
    private Kurs auslandskurs;

    @EJB
    private LAController laController;
    @EJB
    private StudentController studentController;

    public StudentBean() {
        this.student = new Student();
    }

    public String login() {
        // Login korrekt, dann Antrag prüfen und entsprechende Anzeige zurückgeben
        if(studentController.validateLogin(student.getBenutzername(), student.getPasswort())) {
            student=studentController.getStudent();
            if ( student.getAntrag().isGenehmigt() == false){
                return "login_fail.xhtml";
            } else {
            String anzeigeLA= fillLA();  
            return anzeigeLA; 
            }
        } else { // Login inkorrekt, Fehler anzeigen, gleiche Anzeige schalten
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            view.findComponent("login_error").setRendered(true);
            return null;
        }

    }


    public String fillLA() {

        try {
            la = laController.getLA(student);
            if (la == null) {
                la = new LearningAgreement();
                //Letzte ID in der DB suchen?
                //la.setId1((long)435);
            }
            return "learningAgreementAnlegen_1.xhtml";
        } catch (Exception e) {
            return e.toString();
        }
    }

    /**
     *
     */
    public void speichernLA() {
        //Hier erst das LA mti den aktuellen Daten aus der Weboberfläche füllen. Das geht aber iwie nicht?!!?
        FacesContext.getCurrentInstance().renderResponse();
        laController.speichereLA(la);
    }

    public void loeschen() {
        String posId = getRequestParameter("posId");
        System.out.println("Posititionsnummer " + posId);
        la = laController.loeschenLAposition(posId, la);
        FacesContext.getCurrentInstance().renderResponse();
    }

    private String getRequestParameter(String parameter) {
        return FacesContext.getCurrentInstance().
                getExternalContext().
                getRequestParameterMap().get(parameter);
    }

    // Button von LA zu Kurs auswaehlen
    public String kursHinzufuegen() {
        alleKurse = laController.getAlleInlandsKurse();
        return "inlandskursAnzeigen.xhtml";
    }

    // Ausgewaehlten Inlandskurs finden und zwischenspeichern
    public String kursWahlspeichern() {
        String posId = getRequestParameter("kursId");
//        System.out.println("Kursnummer " + Long.parseLong(posId));
//        inlandskurs = laController.getKurs(Long.parseLong(posId));
//        inlandskurs = alleKurse.get(Integer.parseInt(posId));

        //ausgewaehlten Kurs speichern
        for (Kurs k : alleKurse) {
            if (k.getKursId() == Long.parseLong(posId)) {
                inlandskurs = k;
            }
        };
        //FacesContext.getCurrentInstance().renderResponse();
        alleKurse = laController.getAlleAuslandsKurse();
        return "auslandskursAnzeigen.xhtml";
    }

    // Auslandskurs speichern
    public String kursWahlspeichern_2() {
        String posId = getRequestParameter("kursId");
//        System.out.println("Kursnummer " + Long.parseLong(posId));
//        inlandskurs = laController.getKurs(Long.parseLong(posId));
//        inlandskurs = alleKurse.get(Integer.parseInt(posId));

        //ausgewaehlten Kurs speichern
        for (Kurs k : alleKurse) {
            if (k.getKursId() == Long.parseLong(posId)) {
                auslandskurs = k;
            }
        };

        // Kurswahl im LA speichern      
        la.getLearningAgreement_Liste().add(new LearningAgreement_Pos(inlandskurs, auslandskurs));
        return "learningAgreementAnlegen_1";
    }
    
    public String partnerHSwaehlen(){
        partnerHochschulen = laController.getPartnerHS();
        return "hochschul_liste.xhtml";
    }
    
    public String setPartnerHS(){
        String posId = getRequestParameter("hsId");
        for (Hochschule h : partnerHochschulen) {
            if (h.getHochschuleId() == Long.parseLong(posId)) {
                la.setAuslandshochschule(h);
            }
        };
        return "learningAgreementAnlegen_1.xhtml";
    }

    public LearningAgreement getLA() {
        return la;
    }

    public void setLA(LearningAgreement la) {
        this.la = la;
    }

    public LAController getLaController() {
        return laController;
    }

    public void setLaController(LAController laController) {
        this.laController = laController;
    }

    public StudentController getStudentController() {
        return studentController;
    }

    public void setStudentController(StudentController studentController) {
        this.studentController = studentController;
    }
    
    public List<Kurs> getAlleKurse() {
        return alleKurse;
    }

    public List<Hochschule> getPartnerHochschulen() {
        return partnerHochschulen;
    }

    public void setPartnerHochschulen(List<Hochschule> partnerHochschulen) {
        this.partnerHochschulen = partnerHochschulen;
    }

    public void setAlleKurse(List<Kurs> alleKurse) {
        this.alleKurse = alleKurse;
    }
    
    public Student getStudent() {
        return student;
    }

    public void setKunde(Student student) {
        this.student = student;
    }

}
