package projektKompetencyjny;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "uczy")
public class Uczy {

    @Column(name = "id_nauczyciela")
    private int id_nauczyciela;

    @Column(name = "id_przedmiotu")
    private int id_przedmiotu;

    @Column(name = "id_klasy")
    private int id_klasy;

    @Column(name = "ile_godzin")
    private int ile_godzin;

}
