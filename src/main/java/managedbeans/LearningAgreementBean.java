package managedbeans;

import controller.LearningAgreementController;
import controller.StudentController;
import exceptions.WSException;
import fachklassen.Kurs;
import fachklassen.LearningAgreement;
import fachklassen.Student;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

@Named
@SessionScoped
public class LearningAgreementBean implements Serializable {

    private Student student;
    private LearningAgreement learningAgreement;
    private List<Kurs> inlandsKurse;
    private List<Kurs> auslandsKurse;
    private Kurs selectedInlandskurs;
    private Kurs selectedAuslandskurs;
    private Boolean enabledButtonAdd = false;
    private Boolean changesUnsaved = false;

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
        return learningAgreementController.getLearningAgreement(student) != null;
    }

    public String learningAgreementAnlegen() {
        learningAgreement = learningAgreementController.erstelleLearningAgreement(student);
        kurseLaden();
        return "learningAgreementBearbeiten";
    }

    public String learningAgreementBearbeiten() {
        learningAgreement = learningAgreementController.getLearningAgreement(student);
        kurseLaden();
        return "learningAgreementBearbeiten";
    }

    public void speichereLearningAgreement() {
        learningAgreementController.speichereLearningAgreement();
        changesUnsaved = false;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Learning Agreement gespeichert.", "Die Änderungen wurden erfolgreich gespeichert."));
    }

    public void loeschePosition() {
        String posIdx = getRequestParameter("posIdx");
        learningAgreement = learningAgreementController.loescheLearningAgreementPosition(Integer.parseInt(posIdx));
        changesUnsaved = true;
        FacesContext.getCurrentInstance().renderResponse();
    }

    private String getRequestParameter(String parameter) {
        return FacesContext.getCurrentInstance().
                getExternalContext().
                getRequestParameterMap().get(parameter);
    }

    public void kurseLaden() {

        inlandsKurse = learningAgreementController.getAlleInlandsKurse();
        try {
            auslandsKurse = learningAgreementController.getAlleAuslandsKurse();
        } catch (WSException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Auslandskurse konnten nicht über Webservice geladen werden.", ex.getMessage()));
        }
    }

    public String learningAgreementPositionAnlegen() {
        if (selectedInlandskurs != null && selectedAuslandskurs != null) {
            learningAgreement = learningAgreementController.erstelleLearningAgreementPosition(selectedInlandskurs, selectedAuslandskurs);
            selectedAuslandskurs = null;
            selectedInlandskurs = null;
            enabledButtonAdd = false;
            changesUnsaved = true;
        }
        return "learningAgreementBearbeiten";
    }

    public void onKursSelect(SelectEvent event) {
        enabledButtonAdd = selectedInlandskurs != null && selectedAuslandskurs != null;
    }

    public void onNoteChange() {
        changesUnsaved = true;
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

    public Kurs getSelectedInlandskurs() {
        return selectedInlandskurs;
    }

    public void setSelectedInlandskurs(Kurs selectedInlandskurs) {
        this.selectedInlandskurs = selectedInlandskurs;
        System.out.println("Inlandskursgespeichert");
    }

    public Kurs getSelectedAuslandskurs() {
        return selectedAuslandskurs;
    }

    public void setSelectedAuslandskurs(Kurs selectedAuslandskurs) {
        this.selectedAuslandskurs = selectedAuslandskurs;
        System.out.println("Auslandskursgespeichert");
    }

    public Boolean getEnabledButtonAdd() {
        return enabledButtonAdd;
    }

    public void setEnabledButtonAdd(Boolean enabledButtonAdd) {
        this.enabledButtonAdd = enabledButtonAdd;
    }

    public Boolean getChangesUnsaved() {
        return changesUnsaved;
    }

    public void setChangesUnsaved(Boolean changesUnsaved) {
        this.changesUnsaved = changesUnsaved;
    }

}
