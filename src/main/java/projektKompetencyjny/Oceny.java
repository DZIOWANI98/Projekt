package projektKompetencyjny;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "oceny")
public class Oceny {

    @Id
    @Column(name = "id_ucznia")
    private int id_ucznia;

    @Id
    @Column(name = "id_przedmiotu")
    private int id_przedmiotu;

    @Column(name = "data")
    private String data;

    @Id
    @Column(name = "id_oceny")
    private int id_oceny;

    @Column(name = "rodzaj_oceny")
    private String rodzaj_oceny;

    public int getId_ucznia() {
        return id_ucznia;
    }

    public void setId_ucznia(int id_ucznia) {
        this.id_ucznia = id_ucznia;
    }

    public int getId_przedmiotu() {
        return id_przedmiotu;
    }

    public void setId_przedmiotu(int id_przedmiotu) {
        this.id_przedmiotu = id_przedmiotu;
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
