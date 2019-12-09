package projektKompetencyjny;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Table(name = "plan")
public class Plan {

    @Column(name = "id_klasy")
    private int id_klasy;

    @Column(name = "id_lekcji")
    private int id_lekcji;

    public int getId_klasy() {
        return id_klasy;
    }

    public void setId_klasy(int id_klasy) {
        this.id_klasy = id_klasy;
    }

    public int getId_lekcji() {
        return id_lekcji;
    }

    public void setId_lekcji(int id_lekcji) {
        this.id_lekcji = id_lekcji;
    }
}
