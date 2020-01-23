package projektKompetencyjny.event_uczen;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;

public class doTabeliEvent {
    private SimpleStringProperty event;
    private SimpleStringProperty data;
    private SimpleIntegerProperty id;


    public doTabeliEvent(String event, String data, int id) {
        this.event = new SimpleStringProperty(event);
        this.data = new SimpleStringProperty(data);
        this.id = new SimpleIntegerProperty(id);
    }

    public String getEvent() {
        return event.get();
    }

    public void setEvent(String event) {
        this.event.set(event);
    }

    public SimpleStringProperty eventProperty() {
        return event;
    }

    public String getData() {
        return data.get();
    }

    public void setData(String data) {
        this.data.set(data);
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }


    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }
}
