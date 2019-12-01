package projektKompetencyjny;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lekcja")
public class Lekcja {

    @Column(name = "id_lekcji")
    private int id_lekcji;

    @Column(name = "id_godziny")
    private int id_godziny;

    @Column(name = "id_przedmiotu")
    private int id_przedmiotu;

    @Column(name = "id_nauczyciela")
    private int id_nauczyciela;

    @Column(name = "sala")
    private int nr_sali;

    public int getId_lekcji() {
        return id_lekcji;
    }

    public void setId_lekcji(int id_lekcji) {
        this.id_lekcji = id_lekcji;
    }

    public int getId_godziny() {
        return id_godziny;
    }

    public void setId_godziny(int id_godziny) {
        this.id_godziny = id_godziny;
    }

    public int getId_przedmiotu() {
        return id_przedmiotu;
    }

    public void setId_przedmiotu(int id_przedmiotu) {
        this.id_przedmiotu = id_przedmiotu;
    }

    public int getId_nauczyciela() {
        return id_nauczyciela;
    }

    public void setId_nauczyciela(int id_nauczyciela) {
        this.id_nauczyciela = id_nauczyciela;
    }

    public int getNr_sali() {
        return nr_sali;
    }

    public void setNr_sali(int nr_sali) {
        this.nr_sali = nr_sali;
    }
}
