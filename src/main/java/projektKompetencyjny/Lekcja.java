package projektKompetencyjny;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "lekcja")
public class Lekcja {

    @Id
    @Column(name = "id_lekcji")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int id_lekcji;

    @OneToOne
    @JoinColumn(name = "id_godziny")
    private Godzina id_godziny;

    @OneToOne
    @JoinColumn(name = "id_przedmiotu")
    private Przedmiot id_przedmiotu;

    @OneToOne
    @JoinColumn(name = "id_nauczyciela")
    private Nauczyciel id_nauczyciela;

    @OneToOne
    @JoinColumn(name = "id_klasy")
    private Klasa id_klasy;

    @Column(name = "sala")
    private int nr_sali;

    public int getId_lekcji() {
        return id_lekcji;
    }

    public void setId_lekcji(int id_lekcji) {
        this.id_lekcji = id_lekcji;
    }

    public Godzina getId_godziny() {
        return id_godziny;
    }

    public void setId_godziny(Godzina id_godziny) {
        this.id_godziny = id_godziny;
    }

    public Przedmiot getId_przedmiotu() {
        return id_przedmiotu;
    }

    public void setId_przedmiotu(Przedmiot id_przedmiotu) {
        this.id_przedmiotu = id_przedmiotu;
    }

    public Nauczyciel getId_nauczyciela() {
        return id_nauczyciela;
    }

    public void setId_nauczyciela(Nauczyciel id_nauczyciela) {
        this.id_nauczyciela = id_nauczyciela;
    }

    public Klasa getId_klasy() { return id_klasy; }

    public void setId_klasy(Klasa id_klasy) { this.id_klasy = id_klasy; }

    public int getNr_sali() {
        return nr_sali;
    }

    public void setNr_sali(int nr_sali) {
        this.nr_sali = nr_sali;
    }
}
