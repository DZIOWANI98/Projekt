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


}
