package projektKompetencyjny;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @Column(name = "id_admina")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int id_admina;

    @Column(name = "email")
    private String email;

    @Column(name = "haslo")
    private String haslo;


    public int getId_admina() {
        return id_admina;
    }

    public void setId_admina(int id_admina) {
        this.id_admina = id_admina;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
}
