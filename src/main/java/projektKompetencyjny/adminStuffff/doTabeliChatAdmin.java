package projektKompetencyjny.adminStuffff;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class doTabeliChatAdmin {

    private SimpleStringProperty data;
    private SimpleStringProperty godzina;
    private SimpleStringProperty autor;
    private SimpleStringProperty wiadomosc;
    private SimpleIntegerProperty idWiadomosci;

    public doTabeliChatAdmin(String data, String godzina, String autor, String wiadomosc, Integer idWiadomosci) {
        this.data = new SimpleStringProperty(data);
        this.godzina = new SimpleStringProperty(godzina);
        this.autor = new SimpleStringProperty(autor);
        this.wiadomosc = new SimpleStringProperty(wiadomosc);
        this.idWiadomosci = new SimpleIntegerProperty(idWiadomosci);
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
        this.idWiadomosci.set(idWiadomosci);
    }

    @Override
    public String toString() {
        return "doTabeliChatAdmin{" +
                "data=" + data +
                ", godzina=" + godzina +
                ", autor=" + autor +
                ", wiadomosc=" + wiadomosc +
                ", idWiadomosci=" + idWiadomosci +
                '}';
    }
}
