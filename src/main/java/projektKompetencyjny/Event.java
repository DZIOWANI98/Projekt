package projektKompetencyjny;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @Column(name = "id_event")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int id_event;

    @Column(name = "event")
    private String event;

    @Column(name = "data")
    private LocalDate data;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @ManyToOne
    @JoinColumn(name = "id_klasy")
    private Klasa id_klasy;

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }


    public Klasa getId_klasy() {
        return id_klasy;
    }

    public void setId_klasy(Klasa id_klasy) {
        this.id_klasy = id_klasy;
    }


}
