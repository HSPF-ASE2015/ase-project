package fachklassen;


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name="getPartnerHS", query="Select h from Hochschule h where h.name != :HeimatHS")
 })
public class Hochschule implements Serializable {

    @Id
    private Long id1;
    @Basic
    private String name;

    public Hochschule() {

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
}
