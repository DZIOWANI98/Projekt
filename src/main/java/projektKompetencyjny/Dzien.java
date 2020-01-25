package projektKompetencyjny;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "dzien")
public class Dzien {

    @Id
    @Column(name = "`Id_dnia`")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int Id_dnia;

    @Column(name = "`Dzien`")
    private String dzien;

    public String getDzien() {
        return dzien;
    }

    public int getId_dnia() {
        return Id_dnia;
    }
}
