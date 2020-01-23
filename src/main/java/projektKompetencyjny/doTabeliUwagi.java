package projektKompetencyjny;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class doTabeliUwagi {
    private Uczen uczen;
    private SimpleStringProperty uwaga;
    private SimpleStringProperty data;
    private SimpleIntegerProperty id;


    public doTabeliUwagi(String uwaga, String data, Integer id) {
        this.uwaga = new SimpleStringProperty(uwaga);
        this.data = new SimpleStringProperty(data);
        this.id = new SimpleIntegerProperty(id);
    }

    public String getUwaga() {
        return uwaga.get();
    }

    public void setUwaga(String uwaga) {
        this.uwaga.set(uwaga);
    }

    public SimpleStringProperty uwagaProperty() {
        return uwaga;
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
