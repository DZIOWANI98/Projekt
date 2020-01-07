package projektKompetencyjny;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "uwagi")
public class Uwagi {

    @Id
    @Column(name = "id_uwagi")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int id_uwagi;

    @Column(name = "data")
    private String data;

    @Column(name = "uwaga")
    private String uwaga;


}
