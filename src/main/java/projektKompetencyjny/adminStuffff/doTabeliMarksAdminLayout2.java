package projektKompetencyjny.adminStuffff;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class doTabeliMarksAdminLayout2 {
    private SimpleStringProperty data;
    private SimpleIntegerProperty ocena;
    private SimpleStringProperty typOceny;
    private SimpleIntegerProperty id;


    public doTabeliMarksAdminLayout2(String data, int ocena, String typOceny, int id) {
        this.data = new SimpleStringProperty(data);
        this.ocena = new SimpleIntegerProperty(ocena);
        this.typOceny = new SimpleStringProperty(typOceny);
        this.id = new SimpleIntegerProperty(id);
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

    public String getData() {
        return data.get();
    }

    public void setData(String data) {
        this.data.set(data);
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }

    public int getOcena() {
        return ocena.get();
    }

    public void setOcena(int ocena) {
        this.ocena.set(ocena);
    }

    public SimpleIntegerProperty ocenaProperty() {
        return ocena;
    }

    public String getTypOceny() {
        return typOceny.get();
    }

    public void setTypOceny(String typOceny) {
        this.typOceny.set(typOceny);
    }

    public SimpleStringProperty typOcenyProperty() {
        return typOceny;
    }
}
