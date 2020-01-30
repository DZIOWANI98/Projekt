package projektKompetencyjny.admin;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class doTabeliUiAdminRodzic {

    private SimpleStringProperty imie;
    private SimpleStringProperty nazwisko;
    private SimpleIntegerProperty id;
    private SimpleStringProperty email;
    private SimpleStringProperty uczen;

    public doTabeliUiAdminRodzic(String imie, String nazwisko, Integer id, String email, String uczen) {
        this.imie = new SimpleStringProperty(imie);
        this.nazwisko = new SimpleStringProperty(nazwisko);
        this.id = new SimpleIntegerProperty(id);
        this.email = new SimpleStringProperty(email);
        this.uczen = new SimpleStringProperty(uczen);
    }


    public String getImie() {
        return imie.get();
    }

    public void setImie(String imie) {
        this.imie.set(imie);
    }

    public SimpleStringProperty imieProperty() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko.get();
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }

    public SimpleStringProperty nazwiskoProperty() {
        return nazwisko;
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

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public SimpleStringProperty emailProperty() {
        return email;
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
}
