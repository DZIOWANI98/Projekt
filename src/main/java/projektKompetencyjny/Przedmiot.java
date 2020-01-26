package projektKompetencyjny;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "przedmioty")
public class Przedmiot {

    @Id
    @Column(name = "id_przedmiotu")
    private int id_przedmiotu;

    @Column(name = "nazwa_przedmiotu")
    private String nazwa_przedmiotu;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_przedmiotu")
    private List<Nauczyciel> nauczyciele;

    @OneToMany
    @JoinColumn(name = "id_przedmiotu")
    private List<Oceny> oceny = new ArrayList<>();

    public int getId_przedmiotu() {
        return id_przedmiotu;
    }

    public void setId_przedmiotu(int id_przedmiotu) {
        this.id_przedmiotu = id_przedmiotu;
    }

    public String getNazwa_przedmiotu() {
        return nazwa_przedmiotu;
    }

    public void setNazwa_przedmiotu(String nazwa_przedmiotu) {
        this.nazwa_przedmiotu = nazwa_przedmiotu;
    }

    public List<Oceny> getOceny() {
        return oceny;
    }

    public void setOceny(List<Oceny> oceny) {
        this.oceny = oceny;
    }

}
