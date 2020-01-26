package projektKompetencyjny;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nauczyciele")
public class Nauczyciel {

    @Id
    @Column(name = "id_nauczyciela")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int id_nauczyciela;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko")
    private String nazwisko;

    @Column(name = "email")
    private String email;

    @Column(name = "haslo")
    private String haslo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_przedmiotu")
    private Przedmiot przedmiot;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "uczy",
            joinColumns = {@JoinColumn(name = "id_nauczyciela")},
            inverseJoinColumns = {@JoinColumn(name = "id_klasy")}
    )
    private List<Klasa> klasy;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_nauczyciela")
    private List<Uwaga> uwagi;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_nauczyciela")
    private List<Wiadomosci> wiadomosci;


    public List<Uwaga> getUwagi() {
        return uwagi;
    }

    public void setUwagi(List<Uwaga> uwagi) {
        this.uwagi = uwagi;
    }

    public int getId_nauczyciela() {
        return id_nauczyciela;
    }

    public void setId_nauczyciela(int id_nauczyciela) {
        this.id_nauczyciela = id_nauczyciela;
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
        return "Nauczyciel{" +
                "id=" + id_nauczyciela +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", email='" + email + '\'' +
                ", haslo='" + haslo + '\'' +
                '}';
    }

    public List<Klasa> getKlasy() {
        return klasy;
    }

    public void setKlasy(List<Klasa> klasy) {
        this.klasy = klasy;
    }

    public List<Wiadomosci> getWiadomosci() {
        return wiadomosci;
    }

    public void setWiadomosci(List<Wiadomosci> wiadomosci) {
        this.wiadomosci = wiadomosci;
    }

    public Przedmiot getPrzedmiot() {
        return przedmiot;
    }

    public void setPrzedmiot(Przedmiot przedmiot) {
        this.przedmiot = przedmiot;
    }
}
