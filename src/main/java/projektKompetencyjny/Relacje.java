package projektKompetencyjny;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "Relacje")
public class Relacje {

    @Column(name = "id_ucznia")
    private int id_ucznia;

    @Column(name = "id_rodzica")
    private int id_rodzica;


    public int getId_ucznia() {
        return id_ucznia;
    }

    public void setId_ucznia(int id_ucznia) {
        this.id_ucznia = id_ucznia;
    }

    public int getId_rodzica() {
        return id_rodzica;
    }

    public void setId_rodzica(int id_rodzica) {
        this.id_rodzica = id_rodzica;
    }
}
