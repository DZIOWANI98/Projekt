package projektKompetencyjny;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "uwagi")
public class Uwaga {

    @Id
    @Column(name = "id_uwagi")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int id_uwagi;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "uwaga")
    private String uwaga;

    @ManyToOne
    @JoinColumn(name = "id_nauczyciela")
    private Nauczyciel nauczyciel;

    @ManyToOne
    @JoinColumn(name = "id_ucznia")
    private Uczen uczen;


    public int getId_uwagi() {
        return id_uwagi;
    }

    public void setId_uwagi(int id_uwagi) {
        this.id_uwagi = id_uwagi;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getUwaga() {
        return uwaga;
    }

    public void setUwaga(String uwaga) {
        this.uwaga = uwaga;
    }

    public Nauczyciel getNauczyciel() {
        return nauczyciel;
    }

    public void setNauczyciel(Nauczyciel nauczyciel) {
        this.nauczyciel = nauczyciel;
    }

    public Uczen getUczen() {
        return uczen;
    }

    public void setUczen(Uczen uczen) {
        this.uczen = uczen;
    }
}
