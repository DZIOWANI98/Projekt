package projektKompetencyjny;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "przedmioty")
public class Przedmiot {


    @Id
    @Column(name = "id_przedmiotu")
    private int id_przedmiotu;

    @Column(name = "nazwa_przedmiotu")
    private String nazwa_przedmiotu;

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
}
