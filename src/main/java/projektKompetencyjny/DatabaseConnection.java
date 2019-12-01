package projektKompetencyjny;

import java.sql.*;
import java.util.ArrayList;


public class DatabaseConnection {

  public void createLists(Connection conn, ArrayList<String> nauczyciele, ArrayList<String> uczniowie, ArrayList<String> rodzice) {
    String SQL1 = "SELECT * FROM public.\"Uczniowie\"";
    String SQL2 = "SELECT * FROM public.\"Nauczyciele\"";
    String SQL3 = "SELECT * FROM public.\"Rodzice\"";

    try {
      Statement st1 = conn.createStatement();
      Statement st2 = conn.createStatement();
      Statement st3 = conn.createStatement();
      ResultSet rs1 = st1.executeQuery(SQL1);
      ResultSet rs2 = st2.executeQuery(SQL2);
      ResultSet rs3 = st3.executeQuery(SQL3);

      while(rs1.next()) {
        uczniowie.add(rs1.getString("Email"));
      }

      while(rs2.next()) {
        nauczyciele.add(rs2.getString("Email"));
      }

      while(rs3.next()) {
        rodzice.add(rs3.getString("Email"));
      }

      rs1.close();
      rs2.close();
      rs3.close();
      st1.close();
      st2.close();
      st3.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
public void getUczen(Connection conn, String email, String haslo) {

    String SQL = "SELECT * FROM public.\"Uczniowie\" AS \"Users\" WHERE \"Users\".\"Email\" = '" + email + "' AND \"Users\".\"Hasło\" = '" + haslo + "'";

    try {
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(SQL);

      if(rs.next() == false) {
        throw new SQLException("Niepoprawne hasło.");
      } else {
        do {
          System.out.println(rs.getInt("Id_ucznia") + "\t"
              + rs.getString("Imię") + "\t"
              + rs.getString("Naziwsko") + "\t"
              + rs.getString("Email") + "\t"
              + rs.getString("Hasło") + "\t"
              + rs.getString("Id_klasy") + "\t"
              + rs.getString("Miejsce zamieszkania") + "\t"
              + rs.getDate("Data urodzenia") + "\t"
              + rs.getString("Miejsce urodzenia") + "\t"
              + rs.getBoolean("Dojeżdża") + "\t");
        } while(rs.next());
     }

      rs.close();
      st.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

}
