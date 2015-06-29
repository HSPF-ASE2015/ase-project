/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import controller.LAController;
import controller.StudentController;
import fachklassen.Kurs;
import fachklassen.LearningAgreement;
import fachklassen.LearningAgreement_Pos;
import fachklassen.Student;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 *
 * @author Marcel
 */
@Named
@SessionScoped
public class StudentBean implements Serializable {

    private Student student;
//    private String benutzername;
//    private String passwort;
//    private long matnr;
    private LearningAgreement la;
    private List<Kurs> alleKurse;
//    @ManagedBean(value="#{studentBean.student}")
//    private Student student2;

    private Kurs inlandskurs;
    private Kurs auslandskurs;

    public List<Kurs> getAlleKurse() {
        return alleKurse;
    }

    public void setAlleKurse(List<Kurs> alleKurse) {
        this.alleKurse = alleKurse;
    }

    @EJB
    private LAController laController;
    @EJB
    private StudentController studentController;

    /**
     * Creates a new instance of StudentBean
     */
    public StudentBean() {
        this.student = new Student();
    }

    public String validateStudentLogin(FacesContext context,
            UIComponent component, Object value) throws ValidatorException, Exception {
//        benutzername = (String) value;
//        passwort = (String) value;
//        matnr = (Long) value;

        try {
            student = studentController.validateLogin(student.getBenutzername(), student.getPasswort());
            if (student != null) {
                String anzeigeLA = fillLA();
                return anzeigeLA;
                //return "learningAgreementAnlegen_1.xhtml";
            } else {
                throw new Exception("Passwort oder Benutzername falsch");
            }
        } catch (Exception e) { // hier kommt immer null pointer exception, wenn er nichts findet. Liegt wohl an der xhtml!
            String msg = e.getMessage();
            FacesMessage fMsg
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
            context.addMessage(component.getClientId(context), fMsg);
            throw new ValidatorException(fMsg);
        }
    }

    public Student getStudent() {
        return student;
    }

    public void setKunde(Student student) {
        this.student = student;
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
            if (k.getId1() == Long.parseLong(posId)) {
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
            if (k.getId1() == Long.parseLong(posId)) {
                auslandskurs = k;
            }
        };

        // Kurswahl im LA speichern      
        la.getLearningAgreement_Liste().add(new LearningAgreement_Pos(inlandskurs, auslandskurs));
        return "learningAgreementAnlegen_1";
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

}
