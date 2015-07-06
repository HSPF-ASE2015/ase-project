package fachklassen;


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Antrag implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long antragId;
    
    @OneToOne(mappedBy = "antrag")
    private LearningAgreement learningAgreement;
    @OneToOne(mappedBy = "antrag")
    private Student student;
    @OneToOne
    private Hochschule partnerhochschule;

    @Basic
    private boolean genehmigt;


    public Antrag() {

    }
   
    public LearningAgreement getLearningAgreement() {
        return this.learningAgreement;
    }

    public void setLearningAgreement(LearningAgreement learningAgreement) {
        this.learningAgreement = learningAgreement;
    }
   
    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
   
    public Long getAntragId() {
        return this.antragId;
    }

    public void setAntragId(Long antragId) {
        this.antragId = antragId;
    }
    
    public boolean isGenehmigt() {
        return this.genehmigt;
    }

    public void setGenehmigt(boolean genehmigt) {
        this.genehmigt = genehmigt;
    }
    public Hochschule getPartnerhochschule() {
        return partnerhochschule;
    }

    public void setPartnerhochschule(Hochschule partnerhochschule) {
        this.partnerhochschule = partnerhochschule;
    }
}
