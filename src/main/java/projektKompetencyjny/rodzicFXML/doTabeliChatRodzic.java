package projektKompetencyjny.rodzicFXML;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class doTabeliChatRodzic {

    private SimpleStringProperty data;
    private SimpleStringProperty godzina;
    private SimpleStringProperty autor;
    private SimpleStringProperty wiadomosc;
    private SimpleIntegerProperty id;

    public doTabeliChatRodzic(String data, String godzina, String autor, String wiadomosc, Integer id) {
        this.data = new SimpleStringProperty(data);
        this.godzina = new SimpleStringProperty(godzina);
        this.autor = new SimpleStringProperty(autor);
        this.wiadomosc = new SimpleStringProperty(wiadomosc);
        this.id = new SimpleIntegerProperty(id);
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

    public void setData(String data) {
        this.data.set(data);
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }

    public String getGodzina() {
        return godzina.get();
    }

    public void setGodzina(String godzina) {
        this.godzina.set(godzina);
    }

    public SimpleStringProperty godzinaProperty() {
        return godzina;
    }

    public String getAutor() {
        return autor.get();
    }

    public void setAutor(String autor) {
        this.autor.set(autor);
    }

    public SimpleStringProperty autorProperty() {
        return autor;
    }

    public String getWiadomosc() {
        return wiadomosc.get();
    }

    public void setWiadomosc(String wiadomosc) {
        this.wiadomosc.set(wiadomosc);
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
