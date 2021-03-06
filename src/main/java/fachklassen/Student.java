package fachklassen;


import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
    @NamedQuery(name="getAlleStudenten", query="Select s from Student s"),
    @NamedQuery(name="getStudent", query="Select s from Student s where s.benutzername = :bname"),
    @NamedQuery(name="getStudentWithLogin", query="Select s from Student s where s.benutzername=:bname and s.passwort=:pw")
 })
public class Student implements Serializable {
    @Id
    private Long matrikelnr;
    
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Antrag antrag;
    
    @Basic
    private String vorname;
    @Basic
    private String nachname;
    @Basic
    private String geburtsort;
    @Basic
    private Date geburtsdatum;
    @Basic
    private String fakultaet;
    @Basic
    private String studiengang;
    @Basic
    private String benutzername;
    @Basic
    private String passwort;

    public Student() {

    }
   
    public String getStudiengang() {
        return this.studiengang;
    }

    public void setStudiengang(String studiengang) {
        this.studiengang = studiengang;
    }
   
    public String getPasswort() {
        return this.passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
   
    public Date getGeburtsdatum() {
        return this.geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }
   
    public String getFakultaet() {
        return this.fakultaet;
    }

    public void setFakultaet(String fakultaet) {
        this.fakultaet = fakultaet;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

   
    public String getGeburtsort() {
        return this.geburtsort;
    }

    public void setGeburtsort(String geburtsort) {
        this.geburtsort = geburtsort;
    }
   
    public Antrag getAntrag() {
        return this.antrag;
    }

    public void setAntrag(Antrag antrag) {
        this.antrag = antrag;
    }
   
    public Long getMatrikelnr() {
        return this.matrikelnr;
    }

    public void setMatrikelnr(Long matrikelnr) {
        this.matrikelnr = matrikelnr;
    }
   
    public String getBenutzername() {
        return this.benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }
}
