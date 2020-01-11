package projektKompetencyjny;

import javafx.beans.property.SimpleStringProperty;

public class doTabeliUwagi {
    private Uczen uczen;
    private SimpleStringProperty uwaga;
    private SimpleStringProperty data;


    public doTabeliUwagi(String uwaga, String data) {
        this.uwaga = new SimpleStringProperty(uwaga);
        this.data = new SimpleStringProperty(data);
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
}
