package projektKompetencyjny;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
    private String data;

    @Column(name = "rodzaj_oceny")
    private String rodzaj_oceny;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
}
