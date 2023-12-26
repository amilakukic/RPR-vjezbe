package ba.unsa.etf.rpr;
import java.sql.*;
import java.util.ArrayList;

public class GeografijaDAO {

    private static GeografijaDAO instance ;
    private static Connection conn;
    private String url = "jdbc:sqlite:/C:/Users/amila/OneDrive/Desktop/bazaFinal.db";

    private GeografijaDAO() {
        try {
            this.conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static GeografijaDAO getInstance() {
        if (instance == null) instance = new GeografijaDAO();
        return instance;
    }


    public static void removeInstance() throws SQLException {
        instance.conn.close(); instance = null;
    }

    public ArrayList<Grad> gradovi() {
        ArrayList<Grad> g = new ArrayList<>();
        String queryGradovi = "SELECT g.id, g.naziv, g.broj_stanovnika, d.naziv as drzava FROM Grad g join Drzava d on g.drzava=d.id ORDER BY broj_stanovnika DESC";
        try (PreparedStatement s = conn.prepareStatement(queryGradovi)) {
            ResultSet resultSet = s.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String naziv = resultSet.getString("naziv");
                int brojStanovnika = resultSet.getInt("broj_stanovnika");
                Drzava drzava = new Drzava();
                drzava.setNaziv(resultSet.getString("drzava"));
                Grad grad = new Grad(id, naziv, brojStanovnika, drzava);
                g.add(grad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return g;
    }

    //vraća null ako država ne postoji, u suprotnom vraća glavni grad te države iz baze podataka
    public Grad glavniGrad(String drzava) {
        try {
            String glGradquery = "SELECT g.* FROM Grad g, Drzava d WHERE g.id = d.glavni_grad AND d.naziv = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(glGradquery)) {
                preparedStatement.setString(1, drzava);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String naziv = resultSet.getString("naziv");
                    int brojStanovnika = resultSet.getInt("broj_stanovnika");
                    int drzavaId = resultSet.getInt("drzava");
                    String drzavaNaziv = resultSet.getString("drzava");
                    Drzava d = new Drzava(drzavaId, drzavaNaziv, null);
                    return new Grad(id, naziv, brojStanovnika, d);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //briše državu iz baze podataka, kao i sve gradove u toj državi
    public void obrisiDrzavu(String drzava) {
        try {
            conn.setAutoCommit(false);
            //brisanje gradova
            String obrisiQuery = "DELETE FROM Grad WHERE drzava = (SELECT id FROM Drzava WHERE naziv = ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(obrisiQuery)) {
                preparedStatement.setString(1, drzava);
                preparedStatement.executeUpdate();
            }
            //brisanje drzave
            String deleteDrzavaQuery = "DELETE FROM Drzava WHERE naziv = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(deleteDrzavaQuery)) {
                preparedStatement.setString(1, drzava);
                preparedStatement.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //dodaje novi grad u bazu podataka
    public void dodajGrad(Grad grad) {
        String dodajQuery = "INSERT INTO Grad (naziv, broj_stanovnika, drzava) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(dodajQuery)) {
            preparedStatement.setString(1, grad.getNaziv());
            preparedStatement.setInt(2, grad.getBrojStanovnika());
            preparedStatement.setInt(3, grad.getIdDrzava().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //dodaje novu državu u bazu podataka
    public void dodajDrzavu(Drzava drzava) {
        try {
            String dodajtQuery = "INSERT INTO Drzava (naziv, glavni_grad) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(dodajtQuery)) {
                preparedStatement.setString(1, drzava.getNaziv());

                // Postavljamo ID glavnog grada
                if (drzava.getGlavniGrad() != null) {
                    preparedStatement.setInt(2, drzava.getGlavniGrad().getId());
                } else {
                    preparedStatement.setNull(2, Types.INTEGER);
                }

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //ažurira pripadajući red u bazi podataka za dati grad
    public void izmijeniGrad(Grad grad) {
        try {
            String izmijeniQuery = "UPDATE Grad SET naziv = ?, broj_stanovnika = ?, drzava = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(izmijeniQuery)) {
                preparedStatement.setString(1, grad.getNaziv());
                preparedStatement.setInt(2, grad.getBrojStanovnika());
                preparedStatement.setInt(3, grad.getIdDrzava().getId());
                preparedStatement.setInt(4, grad.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   // vraća null ako država ne postoji, u suprotnom vraća državu iz baze podataka
    public Drzava nadjiDrzavu(String drzava) {
        try {
            String nadjiQuery = "SELECT  FROM Drzava WHERE naziv  ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(nadjiQuery)) {
                preparedStatement.setString(1, drzava);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String naziv = resultSet.getString("naziv");
                    int glavniGradId = resultSet.getInt("glavni_grad");
                    Grad glavniGrad = new Grad();
                   // glavniGrad.getNaziv()
                    return new Drzava(id, naziv, glavniGrad);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*private void popuniBazu() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("INSERT INTO Drzava (naziv, glavni_grad) VALUES ('Francuska', null)");
            stmt.executeUpdate("INSERT INTO Drzava (naziv, glavni_grad) VALUES ('Velika Britanija', null)");
            stmt.executeUpdate("INSERT INTO Drzava (naziv, glavni_grad) VALUES ('Austrija', null)");

            int idFrancuske = getIdDrzave("Francuska");
            int idVelikeBritanije = getIdDrzave("Velika Britanija");
            int idAustrije = getIdDrzave("Austrija");

            stmt.executeUpdate("INSERT INTO Grad (naziv, broj_stanovnika, drzava) VALUES ('Pariz', 2200000, " + idFrancuske + ")");
            stmt.executeUpdate("INSERT INTO Grad (naziv, broj_stanovnika, drzava) VALUES ('London', 8900000, " + idVelikeBritanije + ")");
            stmt.executeUpdate("INSERT INTO Grad (naziv, broj_stanovnika, drzava) VALUES ('Beč', 1800000, " + idAustrije + ")");
            stmt.executeUpdate("INSERT INTO Grad (naziv, broj_stanovnika, drzava) VALUES ('Manchester', 545000, " + idVelikeBritanije + ")");
            stmt.executeUpdate("INSERT INTO Grad (naziv, broj_stanovnika, drzava) VALUES ('Graz', 280000, " + idAustrije + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private int getIdDrzave(String naziv) throws SQLException {
        String query = "SELECT id FROM Drzava WHERE naziv = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, naziv);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        }
        return -1;
    }*/

}
