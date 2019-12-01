package projektKompetencyjny;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ocena")
public class Ocena {

    @Id
    @Column(name = "id_oceny")
    private int id_oceny;

    @Column(name = "ocena")
    private String ocena;

    public int getId_oceny() {
        return id_oceny;
    }

    public void setId_oceny(int id_oceny) {
        this.id_oceny = id_oceny;
    }

    public String getOcena() {
        return ocena;
    }

    public void setOcena(String ocena) {
        this.ocena = ocena;
    }
}
