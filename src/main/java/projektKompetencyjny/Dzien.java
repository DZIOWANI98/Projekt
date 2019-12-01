package projektKompetencyjny;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "dzien")
public class Dzien {

    @Id
    @Column(name = "id_dnia")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int id_dnia;

    @Column(name = "dzien")
    private String dzien;

}
