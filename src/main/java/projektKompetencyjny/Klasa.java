package projektKompetencyjny;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "klasa")
public class Klasa {

    @Id
    @Column(name = "id_klasy")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int id_klasy;

    @Column(name = "nazwa_klasy")
    private String nazwa_klasy;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_klasy")
    private List<Event> wydarzenia;

    @ManyToMany(mappedBy = "klasy")
    private List<Nauczyciel> nauczyciel = new ArrayList<>();


    public int getId_klasy() {
        return id_klasy;
    }

    public void setId_klasy(int id_klasy) {
        this.id_klasy = id_klasy;
    }

    public String getNazwa_klasy() {
        return nazwa_klasy;
    }

    public void setNazwa_klasy(String nazwa_klasy) {
        this.nazwa_klasy = nazwa_klasy;
    }

    public List<Event> getWydarzenia() {
        return wydarzenia;
    }

    public void setWydarzenia(List<Event> wydarzenia) {
        this.wydarzenia = wydarzenia;
    }

    public List<Nauczyciel> getNauczyciel() {
        return nauczyciel;
    }

    public void setNauczyciel(List<Nauczyciel> nauczyciel) {
        this.nauczyciel = nauczyciel;
    }
}
