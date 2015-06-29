package fachklassen;


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
    @NamedQuery(name="getAlleInlandsKurse", query="Select k from Kurs k where k.hochschule.name = :HeimatHS"),
        @NamedQuery(name="getKurs", query="Select k from Kurs k where k.id1 = :wahlKurs")
 })
public class Kurs implements Serializable {

    @Basic
    private int ects;
    @Id
    private Long id1;
    @Basic
    private String name;
    @Basic
    private String sprache;
    @OneToOne(targetEntity = Hochschule.class)
    private Hochschule hochschule;

    public Kurs() {

    }
   
    public int getEcts() {
        return this.ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }
   
    public Long getId1() {
        return this.id1;
    }

    public void setId1(Long id1) {
        this.id1 = id1;
    }
   
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
    public String getSprache() {
        return this.sprache;
    }

    public void setSprache(String sprache) {
        this.sprache = sprache;
    }
   
    public Hochschule getHochschule() {
        return this.hochschule;
    }

    public void setHochschule(Hochschule hochschule) {
        this.hochschule = hochschule;
    }
}
