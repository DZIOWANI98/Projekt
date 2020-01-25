package projektKompetencyjny;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rodzice")
public class Rodzic {

    @Id
    @Column(name = "id_rodzica")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int id;

    @Column(name = "imię")
    private String imie;

    @Column(name = "nazwisko")
    private String nazwisko;

    @Column(name = "email")
    private String email;

    @Column(name = "hasło")
    private String haslo;

    @ManyToMany(mappedBy = "rodzice")
    private List<Uczen> uczen = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_rodzica")
    private List<Wiadomosci> wiadomosci;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    @Override
    public String toString() {
        return "Rodzic{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", email='" + email + '\'' +
                ", haslo='" + haslo + '\'' +
                '}';
    }

    public List<Uczen> getUczen() {
        return uczen;
    }

    public void setUczen(List<Uczen> uczen) {
        this.uczen = uczen;
    }

    public List<Wiadomosci> getWiadomosci() {
        return wiadomosci;
    }

    public void setWiadomosci(List<Wiadomosci> wiadomosci) {
        this.wiadomosci = wiadomosci;
    }
}
