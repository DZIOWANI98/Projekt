package projektKompetencyjny;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "godzina")
public class Godzina {

    @Id
    @Column(name = "id_godziny")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int id_godziny;

    @ManyToOne
    @JoinColumn(name = "`Id_dnia`")
    private Dzien Id_dnia;

    @Column(name = "`Ktora_godzina`")
    private String godzina;

    public int getId_godziny() {
        return id_godziny;
    }

    public void setId_godziny(int id_godziny) {
        this.id_godziny = id_godziny;
    }

    public Dzien getId_dnia() {
        return Id_dnia;
    }

    public void setId_dnia(Dzien id_dnia) {
        this.Id_dnia = id_dnia;
    }

    public String getGodzina() {
        return godzina;
    }

    public void setGodzina(String godzina) {
        this.godzina = godzina;
    }
}
