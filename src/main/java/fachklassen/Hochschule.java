package fachklassen;


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hochschule implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long hochschuleId;

    @Basic
    private String name;

    public Hochschule() {

    }
   
    public Long getHochschuleId() {
        return this.hochschuleId;
    }

    public void setHochschuleId(Long hochschuleId) {
        this.hochschuleId = hochschuleId;
    }
   
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
