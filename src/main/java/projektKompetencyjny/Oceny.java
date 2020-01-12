package projektKompetencyjny;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "oceny")
public class Oceny {

    @Id
    @Column(name = "id_oceny")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int id_oceny;

    @ManyToOne
    @JoinColumn(name = "id_ucznia")
    private Uczen uczen;
    @ManyToOne
    @JoinColumn(name = "id_przedmiotu")
    private Przedmiot id_przedmiotu;
    @Column(name = "ocena")
    private int ocena;

    public Przedmiot getId_przedmiotu() {
        return id_przedmiotu;
    }

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "rodzaj_oceny")
    private String rodzaj_oceny;

    @Column(name = "semestr")
    private int nr_semestru;

    public int getNr_semestru() {
        return nr_semestru;
    }

    public void setNr_semestru(int nr_semestru) {
        this.nr_semestru = nr_semestru;
    }

    public void setId_przedmiotu(Przedmiot id_przedmiotu) {
        this.id_przedmiotu = id_przedmiotu;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public Uczen getUczen() {
        return uczen;
    }

    public void setUczen(Uczen uczen) {
        this.uczen = uczen;
    }


    public int getId_oceny() {
        return id_oceny;
    }

    public void setId_oceny(int id_oceny) {
        this.id_oceny = id_oceny;
    }

    public String getRodzaj_oceny() {
        return rodzaj_oceny;
    }

    public void setRodzaj_oceny(String rodzaj_oceny) {
        this.rodzaj_oceny = rodzaj_oceny;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
