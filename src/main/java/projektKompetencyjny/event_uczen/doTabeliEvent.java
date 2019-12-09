package projektKompetencyjny.event_uczen;

import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;

public class doTabeliEvent {
    private SimpleStringProperty event;
    private SimpleStringProperty data;


    public doTabeliEvent(String event, String data) {
        this.event = new SimpleStringProperty(event);
        this.data = new SimpleStringProperty(data);
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
}
