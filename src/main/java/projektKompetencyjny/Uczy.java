package projektKompetencyjny;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table(name = "Uczy")
public class Uczy {

    @Column(name = "id_nauczyciela")
    private int id_nauczyciela;

    @Column(name = "id_klasy")
    private int id_klasy;

}