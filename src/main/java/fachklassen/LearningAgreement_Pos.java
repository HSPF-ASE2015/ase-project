package fachklassen;


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class LearningAgreement_Pos implements Serializable {

    @OneToOne(targetEntity = Kurs.class)
    private Kurs inlands_kurs;
    @Basic
    private String note_ausland;
    @Id
    private Long id1;
    @OneToOne(targetEntity = Kurs.class)
    private Kurs auslands_kurs;
    @Basic
    private boolean genehmigt;
    @Basic
    private float note_inland;
    @ManyToOne(targetEntity = LearningAgreement.class)
    private LearningAgreement learningAgreement;

    public LearningAgreement_Pos() {

    }

    public LearningAgreement_Pos(Kurs inlands_kurs, Kurs auslands_kurs) {
        this.inlands_kurs = inlands_kurs;
        this.auslands_kurs = auslands_kurs;
    }
   
    public Kurs getInlands_kurs() {
        return this.inlands_kurs;
    }

    public void setInlands_kurs(Kurs inlands_kurs) {
        this.inlands_kurs = inlands_kurs;
    }
   
    public String getNote_ausland() {
        return this.note_ausland;
    }

    public void setNote_ausland(String note_ausland) {
        this.note_ausland = note_ausland;
    }
   
    public Long getId1() {
        return this.id1;
    }

    public void setId1(Long id1) {
        this.id1 = id1;
    }
   
    public Kurs getAuslands_kurs() {
        return this.auslands_kurs;
    }

    public void setAuslands_kurs(Kurs auslands_kurs) {
        this.auslands_kurs = auslands_kurs;
    }
    
    public boolean isGenehmigt() {
        return this.genehmigt;
    }

    public void setGenehmigt(boolean genehmigt) {
        this.genehmigt = genehmigt;
    }
   
    public float getNote_inland() {
        return this.note_inland;
    }

    public void setNote_inland(float note_inland) {
        this.note_inland = note_inland;
    }
   
    public LearningAgreement getLearningAgreement() {
        return this.learningAgreement;
    }

    public void setLearningAgreement(LearningAgreement learningAgreement) {
        this.learningAgreement = learningAgreement;
    }
}
