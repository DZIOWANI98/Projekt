package projektKompetencyjny;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class doTabeliUcznia {

    private SimpleStringProperty przedmiot;
    private SimpleStringProperty klasowka;
    private SimpleStringProperty praceDomowe;
    private SimpleStringProperty kartkowka;
    private SimpleStringProperty odpowiedz;

    public doTabeliUcznia(String przedmiot, String klasowka, String praceDomowe, String kartkowka, String odpowiedz) {
        this.przedmiot = new SimpleStringProperty(przedmiot);
        this.klasowka = new SimpleStringProperty(klasowka);
        this.praceDomowe = new SimpleStringProperty(praceDomowe);
        this.kartkowka = new SimpleStringProperty(kartkowka);
        this.odpowiedz = new SimpleStringProperty(odpowiedz);
    }


    public String getPrzedmiot() {
        return przedmiot.get();
    }

    public void setPrzedmiot(String przedmiot) {
        this.przedmiot.set(przedmiot);
    }

    public SimpleStringProperty przedmiotProperty() {
        return przedmiot;
    }

    public String getKlasowka() {
        return klasowka.get();
    }

    public void setKlasowka(String klasowka) {
        this.klasowka.set(klasowka);
    }

    public SimpleStringProperty klasowkaProperty() {
        return klasowka;
    }

    public String getPraceDomowe() {
        return praceDomowe.get();
    }

    public void setPraceDomowe(String praceDomowe) {
        this.praceDomowe.set(praceDomowe);
    }

    public SimpleStringProperty praceDomoweProperty() {
        return praceDomowe;
    }

    public String getKartkowka() {
        return kartkowka.get();
    }

    public void setKartkowka(String kartkowka) {
        this.kartkowka.set(kartkowka);
    }

    public SimpleStringProperty kartkowkaProperty() {
        return kartkowka;
    }

    public String getOdpowiedz() {
        return odpowiedz.get();
    }

    public void setOdpowiedz(String odpowiedz) {
        this.odpowiedz.set(odpowiedz);
    }

    public SimpleStringProperty odpowiedzProperty() {
        return odpowiedz;
    }
}
