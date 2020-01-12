package projektKompetencyjny.adminStuffff;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class doTabeliMarksAdmin {

    private SimpleStringProperty uczen;
    private SimpleStringProperty klasowka;
    private SimpleStringProperty praceDomowe;
    private SimpleStringProperty kartkowka;
    private SimpleStringProperty odpowiedz;
    private SimpleDoubleProperty srednia;

    public doTabeliMarksAdmin(String uczen, String klasowka, String praceDomowe, String kartkowka, String odpowiedz, Double srednia) {
        this.uczen = new SimpleStringProperty(uczen);
        this.klasowka = new SimpleStringProperty(klasowka);
        this.praceDomowe = new SimpleStringProperty(praceDomowe);
        this.kartkowka = new SimpleStringProperty(kartkowka);
        this.odpowiedz = new SimpleStringProperty(odpowiedz);
        this.srednia = new SimpleDoubleProperty(srednia);
    }


    public String getUczen() {
        return uczen.get();
    }

    public void setUczen(String uczen) {
        this.uczen.set(uczen);
    }

    public SimpleStringProperty uczenProperty() {
        return uczen;
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

    public double getSrednia() {
        return srednia.get();
    }

    public void setSrednia(double srednia) {
        this.srednia.set(srednia);
    }

    public SimpleDoubleProperty sredniaProperty() {
        return srednia;
    }
}
