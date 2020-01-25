package projektKompetencyjny;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "wiadomosci")
public class Wiadomosci {

    @Id
    @Column(name = "id_wiadomosci")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int id_wiadomosci;

    @Column(name = "wiadomosc")
    private String wiadomosc;

    @Column(name = "autor")
    private String autor;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "czas")
    private Time czas;

    @ManyToOne
    @JoinColumn(name = "id_rodzica")
    private Rodzic rodzic;

    @ManyToOne
    @JoinColumn(name = "id_nauczyciela")
    private Nauczyciel nauczyciel;


    public int getId_wiadomosci() {
        return id_wiadomosci;
    }

    public void setId_wiadomosci(int id_wiadomosci) {
        this.id_wiadomosci = id_wiadomosci;
    }

    public String getWiadomosc() {
        return wiadomosc;
    }

    public void setWiadomosc(String wiadomosc) {
        this.wiadomosc = wiadomosc;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Time getCzas() {
        return czas;
    }

    public void setCzas(Time czas) {
        this.czas = czas;
    }

    public Rodzic getRodzic() {
        return rodzic;
    }

    public void setRodzic(Rodzic rodzic) {
        this.rodzic = rodzic;
    }

    public Nauczyciel getNauczyciel() {
        return nauczyciel;
    }

    public void setNauczyciel(Nauczyciel nauczyciel) {
        this.nauczyciel = nauczyciel;
    }
}
