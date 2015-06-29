package fachklassen;


import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
  //  @NamedQuery(name="getAlleStudenten", query="Select s from Student s"),
    @NamedQuery(name="getLA", query="Select l from LearningAgreement l where l.antrag.id1 = :antragid")
 })
public class LearningAgreement implements Serializable {

    @Basic
    private String endDatum;
    @OneToMany(targetEntity = LearningAgreement_Pos.class,mappedBy = "learningAgreement")
    private List<LearningAgreement_Pos> learningAgreement_Liste;
    @Basic
    private int gesamtCredits;
    @Id
    private Long id1;
    @Basic
    private String semester;
    @Basic
    private String startDatum;
    @Basic
    private String ausstellDatum;
    @OneToOne(targetEntity = Antrag.class,mappedBy = "learningAgreement1")
    private Antrag antrag;
    @OneToOne(targetEntity = Hochschule.class)
    private Hochschule auslandshochschule;

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
   
    public Long getId1() {
        return this.id1;
    }

    public void setId1(Long id1) {
        this.id1 = id1;
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
