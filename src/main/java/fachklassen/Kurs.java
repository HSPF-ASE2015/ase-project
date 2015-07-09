package fachklassen;


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
    @NamedQuery(name="getKurseFromHs", query="Select k from Kurs k where k.hochschule.name = :HS"),
        @NamedQuery(name="getKurs", query="Select k from Kurs k where k.kursId = :wahlKurs")
 })
public class Kurs implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long kursId;
    
    @OneToOne(targetEntity = Hochschule.class)
    private Hochschule hochschule;
    
    @Basic
    private int ects;
    @Basic
    private String name;
    @Basic
    private String sprache;

    public Kurs() {

    }
    
    public Kurs(int ects, String name, String sprache, Hochschule hochschule){
        this.ects = ects;
        this.name = name;
        this.sprache = sprache;
        this.hochschule = hochschule;
    }
   
    public int getEcts() {
        return this.ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }
   
    public Long getKursId() {
        return this.kursId;
    }

    public void setKursId(Long kursId) {
        this.kursId = kursId;
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
