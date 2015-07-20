package managedbeans;

import controller.LearningAgreementController;
import controller.StudentController;
import fachklassen.LearningAgreement;
import fachklassen.Student;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * 
 */
@Named
@SessionScoped
public class StudentBean implements Serializable {

    private Student student;

    private LearningAgreement learningAgreement;


    @EJB
    private LearningAgreementController learningAgreementController;
    @EJB
    private StudentController studentController;

    public StudentBean() {
        this.student = new Student();
    }

    public String login() {
        // Login korrekt, dann Antrag prüfen und entsprechende Anzeige zurückgeben
        if(studentController.validateLogin(student.getBenutzername(), student.getPasswort())) {
            student=studentController.getStudent();

                return "antraegeAnzeigen";

        } else { // Login inkorrekt, Fehler anzeigen, gleiche Anzeige schalten
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            view.findComponent("login_error").setRendered(true);
            return null;
        }
    }

    public String logout() {
        student = null;
        studentController.logout();
        learningAgreementController.logout();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }

    public LearningAgreement getLA() {
        return learningAgreement;
    }

    public void setLA(LearningAgreement la) {
        this.learningAgreement = la;
    }
    
    public Student getStudent() {
        return student;
    }

    public void setKunde(Student student) {
        this.student = student;
    }


}
