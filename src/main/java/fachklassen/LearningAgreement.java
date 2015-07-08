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
    private List<LearningAgreementPosition> learningAgreementPositionen;
    @OneToOne
    private Antrag antrag;
    
    @Basic
    private int gesamtCredits;
    @Basic
    private String ausstellDatum;

    public LearningAgreement() {

    }
    public LearningAgreement(Antrag antrag) {
        this.antrag = antrag;
    }

    public List<LearningAgreementPosition> getLearningAgreementPositionen() {
        return this.learningAgreementPositionen;
    }

    public int getAnzahlPositionen() {
        return learningAgreementPositionen.size();
    }

    public void setLearningAgreementPositionen(List<LearningAgreementPosition> learningAgreementPositionen) {
        this.learningAgreementPositionen = learningAgreementPositionen;
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
}
