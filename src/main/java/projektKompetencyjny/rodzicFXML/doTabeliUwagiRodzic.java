package projektKompetencyjny.rodzicFXML;

import javafx.beans.property.SimpleStringProperty;

public class doTabeliUwagiRodzic {
    private SimpleStringProperty uwaga;
    private SimpleStringProperty data;
    private SimpleStringProperty nauczyciel;


    public doTabeliUwagiRodzic(String data, String uwaga, String nauczyciel) {
        this.uwaga = new SimpleStringProperty(uwaga);
        this.data = new SimpleStringProperty(data);
        this.nauczyciel = new SimpleStringProperty(nauczyciel);
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

    public String getNauczyciel() {
        return nauczyciel.get();
    }

    public void setNauczyciel(String nauczyciel) {
        this.nauczyciel.set(nauczyciel);
    }

    public SimpleStringProperty nauczycielProperty() {
        return nauczyciel;
    }
}
