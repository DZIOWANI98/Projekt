package projektKompetencyjny.adminStuffff;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class doTabeliChatAdmin {

    private SimpleStringProperty data;
    private SimpleStringProperty godzina;
    private SimpleStringProperty autor;
    private SimpleStringProperty wiadomosc;
    private SimpleIntegerProperty id;

    public doTabeliChatAdmin(String data, String godzina, String autor, String wiadomosc, Integer id) {
        this.data = new SimpleStringProperty(data);
        this.godzina = new SimpleStringProperty(godzina);
        this.autor = new SimpleStringProperty(autor);
        this.wiadomosc = new SimpleStringProperty(wiadomosc);
        this.id = new SimpleIntegerProperty(id);
    }

    public void setData(String data) {
        this.data.set(data);
    }

    public void setGodzina(String godzina) {
        this.godzina.set(godzina);
    }

    public void setAutor(String autor) {
        this.autor.set(autor);
    }

    public void setWiadomosc(String wiadomosc) {
        this.wiadomosc.set(wiadomosc);
    }

    public void setIdWiadomosci(int idWiadomosci) {
        this.id.set(idWiadomosci);
    }

    @Override
    public String toString() {
        return "doTabeliChatAdmin{" +
                "data=" + data +
                ", godzina=" + godzina +
                ", autor=" + autor +
                ", wiadomosc=" + wiadomosc +
                ", idWiadomosci=" + id +
                '}';
    }

    public String getData() {
        return data.get();
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }

    public String getGodzina() {
        return godzina.get();
    }

    public SimpleStringProperty godzinaProperty() {
        return godzina;
    }

    public String getAutor() {
        return autor.get();
    }

    public SimpleStringProperty autorProperty() {
        return autor;
    }

    public String getWiadomosc() {
        return wiadomosc.get();
    }

    public SimpleStringProperty wiadomoscProperty() {
        return wiadomosc;
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }
}
