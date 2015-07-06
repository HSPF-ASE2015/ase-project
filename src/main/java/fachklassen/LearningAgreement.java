package fachklassen;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
    //  @NamedQuery(name="getAlleStudenten", query="Select s from Student s"),
    @NamedQuery(name = "getLA", query = "Select l from LearningAgreement l where l.antrag.antragId = :antragid")
})
public class LearningAgreement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long learningAgreementId;

    @OneToMany(mappedBy = "learningAgreement", cascade = CascadeType.ALL)
    private List<LearningAgreement_Pos> learningAgreement_Liste;
    @OneToOne
    private Antrag antrag;
    @OneToOne
    private Hochschule auslandshochschule;
    
    @Basic
    private int gesamtCredits;
    @Basic
    private String semester;
    @Basic
    private String startDatum;
    @Basic
    private String endDatum;
    @Basic
    private String ausstellDatum;

    public LearningAgreement() {

    }

    public String getEndDatum() {
        return this.endDatum;
    }

    public void setEndDatum(String endDatum) {
        this.endDatum = endDatum;
    }

    public List<LearningAgreement_Pos> getLearningAgreement_Liste() {
        return this.learningAgreement_Liste;
    }

    public int getAnzahlPositionen() {
        return learningAgreement_Liste.size();
    }

    public void setLearningAgreement_Liste(List<LearningAgreement_Pos> learningAgreement_Liste) {
        this.learningAgreement_Liste = learningAgreement_Liste;
    }

    public int getGesamtCredits() {
        return this.gesamtCredits;
    }

    public void setGesamtCredits(int gesamtCredits) {
        this.gesamtCredits = gesamtCredits;
    }

    public Long getLearningAgreementId() {
        return this.learningAgreementId;
    }

    public void setLearningAgreementId(Long learningAgreementId) {
        this.learningAgreementId = learningAgreementId;
    }

    public String getSemester() {
        return this.semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStartDatum() {
        return this.startDatum;
    }

    public void setStartDatum(String startDatum) {
        this.startDatum = startDatum;
    }

    public String getAusstellDatum() {
        return this.ausstellDatum;
    }

    public void setAusstellDatum(String ausstellDatum) {
        this.ausstellDatum = ausstellDatum;
    }

    public Antrag getAntrag() {
        return this.antrag;
    }

    public void setAntrag(Antrag antrag) {
        this.antrag = antrag;
    }

    public Hochschule getAuslandshochschule() {
        return this.auslandshochschule;
    }

    public void setAuslandshochschule(Hochschule auslandshochschule) {
        this.auslandshochschule = auslandshochschule;
    }
}
