package projektKompetencyjny;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "uczniowie")
public class Uczen {

    @Id
    @Column(name = "id_ucznia")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int Id_Ucznia;

    @Column(name = "imię")
    private String name;

    @Column(name = "nazwisko")
    private String nazwisko;

    @Column(name = "hasło")
    private String haslo;

    @Column(name = "email")
    private String email;

    @Column(name = "`miejsce zamieszkania`")
    private String miejsceZamieszkania;

    @Column(name = "`miejsce urodzenia`")
    private String miejsceUrodzenia;

    @Column(name = "dojeżdża")
    private boolean czyDojezdza;

    @OneToOne
    @JoinColumn(name = "id_klasy")
    private Klasa idKlasy;

    @Column(name = "`data urodzenia`")
    private String dataUrodzenia;

    @ManyToMany
    @JoinTable(
            name = "relacje",
            joinColumns = {@JoinColumn(name = "id_ucznia")},
            inverseJoinColumns = {@JoinColumn(name = "id_rodzica")}
    )
    private List<Rodzic> rodzice;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_ucznia")
    private List<Oceny> oceny;

    @Override
    public String toString() {
        return "Uczen{" +
                "Id_Ucznia=" + Id_Ucznia +
                ", name='" + name + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", haslo='" + haslo + '\'' +
                ", email='" + email + '\'' +
                ", miejsceZamieszkania='" + miejsceZamieszkania + '\'' +
                ", miejsceUrodzenia='" + miejsceUrodzenia + '\'' +
                ", czyDojezdza=" + czyDojezdza +
                ", idKlasy=" + idKlasy +
                ", dataUrodzenia='" + dataUrodzenia + '\'' +
                '}';
    }

    public List<Oceny> getOceny() {
        return oceny;
    }

    public void setOceny(List<Oceny> oceny) {
        this.oceny = oceny;
    }


    public int getId_Ucznia() {
        return Id_Ucznia;
    }

    public void setId_Ucznia(int id_Ucznia) {
        Id_Ucznia = id_Ucznia;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMiejsceZamieszkania() {
        return miejsceZamieszkania;
    }

    public void setMiejsceZamieszkania(String miejsceZamieszkania) {
        this.miejsceZamieszkania = miejsceZamieszkania;
    }

    public String getMiejsceUrodzenia() {
        return miejsceUrodzenia;
    }

    public void setMiejsceUrodzenia(String miejsceUrodzenia) {
        this.miejsceUrodzenia = miejsceUrodzenia;
    }

    public boolean isCzyDojezdza() {
        return czyDojezdza;
    }

    public void setCzyDojezdza(boolean czyDojezdza) {
        this.czyDojezdza = czyDojezdza;
    }

    public Klasa getIdKlasy() {
        return idKlasy;
    }

    public void setIdKlasy(Klasa idKlasy) {
        this.idKlasy = idKlasy;
    }

    public String getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(String dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public List<Rodzic> getRodzice() {
        return rodzice;
    }

    public void setRodzice(List<Rodzic> rodzice) {
        this.rodzice = rodzice;
    }
}