package projektKompetencyjny.admin;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class doTabeliUiAdminUczen {

    private SimpleStringProperty imie;
    private SimpleStringProperty nazwisko;
    private SimpleIntegerProperty id;
    private SimpleStringProperty email;
    private SimpleStringProperty dataUrodzenia;
    private SimpleStringProperty klasa;
    private SimpleStringProperty miejsceZamieszkania;
    private SimpleStringProperty miejsceUrodzenia;
    private SimpleStringProperty dojezdza;


    public doTabeliUiAdminUczen(String imie, String nazwisko, Integer id, String email, String dataUrodzenia, String klasa, String miejsceZamieszkania
            , String miejsceUrodzenia, String dojezdza) {
        this.imie = new SimpleStringProperty(imie);
        this.nazwisko = new SimpleStringProperty(nazwisko);
        this.id = new SimpleIntegerProperty(id);
        this.email = new SimpleStringProperty(email);
        this.dataUrodzenia = new SimpleStringProperty(dataUrodzenia);
        this.klasa = new SimpleStringProperty(klasa);
        this.miejsceZamieszkania = new SimpleStringProperty(miejsceZamieszkania);
        this.miejsceUrodzenia = new SimpleStringProperty(miejsceUrodzenia);
        this.dojezdza = new SimpleStringProperty(dojezdza);

    }


    public SimpleIntegerProperty idProperty() {
        return id;
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

    public double getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
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

    public String getDataUrodzenia() {
        return dataUrodzenia.get();
    }

    public void setDataUrodzenia(String dataUrodzenia) {
        this.dataUrodzenia.set(dataUrodzenia);
    }

    public SimpleStringProperty dataUrodzeniaProperty() {
        return dataUrodzenia;
    }

    public String getKlasa() {
        return klasa.get();
    }

    public void setKlasa(String klasa) {
        this.klasa.set(klasa);
    }

    public SimpleStringProperty klasaProperty() {
        return klasa;
    }

    public String getMiejsceZamieszkania() {
        return miejsceZamieszkania.get();
    }

    public void setMiejsceZamieszkania(String miejsceZamieszkania) {
        this.miejsceZamieszkania.set(miejsceZamieszkania);
    }

    public SimpleStringProperty miejsceZamieszkaniaProperty() {
        return miejsceZamieszkania;
    }

    public String getMiejsceUrodzenia() {
        return miejsceUrodzenia.get();
    }

    public void setMiejsceUrodzenia(String miejsceUrodzenia) {
        this.miejsceUrodzenia.set(miejsceUrodzenia);
    }

    public SimpleStringProperty miejsceUrodzeniaProperty() {
        return miejsceUrodzenia;
    }

    public String getDojezdza() {
        return dojezdza.get();
    }

    public void setDojezdza(String dojezdza) {
        this.dojezdza.set(dojezdza);
    }

    public SimpleStringProperty dojezdzaProperty() {
        return dojezdza;
    }
}
