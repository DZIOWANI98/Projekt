package projektKompetencyjny;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "uczniowie")
public class Uczen {

    @Id
    @Column(name = "id_ucznia")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int Id_Ucznia;

    public int getId_Ucznia() {
        return Id_Ucznia;
    }

    public void setId_Ucznia(int id_Ucznia) {
        Id_Ucznia = id_Ucznia;
    }

    @Column(name = "imię")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "nazwisko")
    private String nazwisko;

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }


    @Column(name = "hasło")
    private String haslo;

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }


    @Column(name = "email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Uczen{" +
                "Id_Ucznia=" + Id_Ucznia +
                ", name='" + name + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", haslo='" + haslo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}