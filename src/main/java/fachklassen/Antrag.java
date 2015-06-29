package fachklassen;


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Antrag implements Serializable {

    @OneToOne(targetEntity = LearningAgreement.class)
    private LearningAgreement learningAgreement1;
    @OneToOne(targetEntity = Student.class,mappedBy = "antrag")
    private Student student;
    @Id
    private Long id1;
    @Basic
    private boolean genehmigt;

    public Antrag() {

    }
   
    public LearningAgreement getLearningAgreement1() {
        return this.learningAgreement1;
    }

    public void setLearningAgreement1(LearningAgreement learningAgreement1) {
        this.learningAgreement1 = learningAgreement1;
    }
   
    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
   
    public Long getId1() {
        return this.id1;
    }

    public void setId1(Long id1) {
        this.id1 = id1;
    }
    
    public boolean isGenehmigt() {
        return this.genehmigt;
    }

    public void setGenehmigt(boolean genehmigt) {
        this.genehmigt = genehmigt;
    }
}
