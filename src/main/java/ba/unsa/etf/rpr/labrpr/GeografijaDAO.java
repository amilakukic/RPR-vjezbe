package ba.unsa.etf.rpr.labrpr;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeografijaDAO {
    private static GeografijaDAO instance ;
    private static Connection conn;
    private String url = "jdbc:sqlite:/C:/Users/amila/OneDrive/Desktop/bazaFinal.db";

    private GeografijaDAO() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
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
        String queryGradovi = "SELECT g.id, g.naziv, g.broj_stanovnika, d.naziv as drzava FROM Grad g, Drzava d WHERE g.id=d.glavni_grad ORDER BY broj_stanovnika DESC";
        try  {
            PreparedStatement s = conn.prepareStatement(queryGradovi);
            ResultSet resultSet = s.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String naziv = resultSet.getString("naziv");
                int brojStanovnika = resultSet.getInt("broj_stanovnika");
                Drzava drzava = new Drzava();
                drzava.setNaziv(resultSet.getString("drzava"));
                Grad grad = new Grad(id, naziv, brojStanovnika, drzava);
                g.add(grad);}
        } catch (SQLException e) {
            e.printStackTrace();}
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

    //dodaje novu državu u bazu podataka

    private int generisiIdDrzave() {
        try {
            String generisiIdQuery = "SELECT MAX(id) + 1 FROM Drzava";
            try (PreparedStatement idStatement = conn.prepareStatement(generisiIdQuery);
                 ResultSet resultSet = idStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1; // Ako ne možemo dohvatiti ID, vraćamo neki defaultni ID (na primjer, 1)
    }

    /*public void dodajDrzavu(Drzava drzava) {
        try {
            int generatedId = generisiIdDrzave();

            String dodajQuery = "INSERT INTO Drzava (id, naziv, glavni_grad) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(dodajQuery)) {
                preparedStatement.setInt(1, generatedId);
                preparedStatement.setString(2, drzava.getNaziv());

                // Postavljamo ID glavnog grada
                if (drzava.getGlavniGrad() != null) {
                    preparedStatement.setInt(3, drzava.getGlavniGrad().getId());
                } else {
                    preparedStatement.setNull(3, Types.INTEGER);
                }

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    public void dodajDrzavu(Drzava drzava) {
        try {
            String dodajtQuery = "INSERT INTO Drzava (naziv, glavni_grad) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(dodajtQuery)) {
                preparedStatement.setString(1, drzava.getNaziv());
                preparedStatement.setInt(2, drzava.getGlavniGrad().getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // dodaje novi grad u bazu podataka
    public void dodajGrad(Grad grad) {
        String dodajQuery = "INSERT INTO Grad (naziv, broj_stanovnika, drzava) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(dodajQuery)) {
            preparedStatement.setString(1, grad.getNaziv());
            preparedStatement.setInt(2, grad.getBrojStanovnika());
            if (grad.getIdDrzava() != null) {
                preparedStatement.setInt(3, grad.getIdDrzava().getId());
            } else {
                preparedStatement.setNull(3, Types.INTEGER);
            }
            preparedStatement.executeUpdate();
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


    private int generisiIdZaGrad() {
        try {
            String generisiIdQuery = "SELECT MAX(id) + 1 FROM Grad";
            try (PreparedStatement idStatement = conn.prepareStatement(generisiIdQuery);
                 ResultSet resultSet = idStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }
    /*public void dodajGrad(Grad grad) {
        try {
            int noviId = generisiIdZaGrad();

            String dodajGradQuery = "INSERT INTO Grad (id, naziv, broj_stanovnika, drzava) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(dodajGradQuery)) {
                preparedStatement.setInt(1, noviId);
                preparedStatement.setString(2, grad.getNaziv());
                preparedStatement.setInt(3, grad.getBrojStanovnika());
                preparedStatement.setInt(4, grad.getIdDrzava().getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void izmijeniGrad(Grad grad) {
        try {
            int noviId = generisiIdZaGrad();

            String izmijeniGradQuery = "UPDATE Grad SET id = ?, naziv = ?, broj_stanovnika = ?, drzava = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(izmijeniGradQuery)) {
                preparedStatement.setInt(1, noviId);
                preparedStatement.setString(2, grad.getNaziv());
                preparedStatement.setInt(3, grad.getBrojStanovnika());
                preparedStatement.setInt(4, grad.getIdDrzava().getId());
                preparedStatement.setInt(5, grad.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public Drzava nadjiDrzavu(int id) {
        try {
            String nadjiQuery = "SELECT d.id, d.naziv, g.naziv AS glavni_grad FROM Drzava d JOIN Grad g ON d.glavni_grad = g.id WHERE d.id = ?;";
            try (PreparedStatement preparedStatement = conn.prepareStatement(nadjiQuery)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String naziv = resultSet.getString("naziv");
                    String glavniGradNaziv = resultSet.getString("glavni_grad");
                    Grad glavniGrad = new Grad();
                    glavniGrad.setNaziv(glavniGradNaziv);
                    return new Drzava(id, naziv, glavniGrad);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Drzava> drzave() {
        List<Drzava> drzave = new ArrayList<>();
        String queryDrzave = "SELECT d.id, d.naziv, g.naziv AS grad FROM Drzava d, Grad g WHERE g.id= d.glavni_grad";
        try (PreparedStatement preparedStatement = conn.prepareStatement(queryDrzave);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int drzavaId = resultSet.getInt("id");
                String drzavaNaziv = resultSet.getString("naziv");
                Grad grad = new Grad();
                grad.setNaziv(resultSet.getString("grad"));
                Drzava drzava = new Drzava(drzavaId, drzavaNaziv, grad);
                drzave.add(drzava);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drzave;
    }

    //brise samo grad
    public void obrisiGrad(int gradId) {
        try {
            String obrisiGradQuery = "DELETE FROM Grad WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(obrisiGradQuery)) {
                preparedStatement.setInt(1, gradId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Drzava nadjiDrzavuIme(String naziv) {
        try {
            String nadjiQuery = "SELECT d.id, d.naziv, g.naziv AS glavni_grad FROM Drzava d JOIN Grad g ON d.glavni_grad = g.id WHERE d.naziv = ?;";
            try (PreparedStatement preparedStatement = conn.prepareStatement(nadjiQuery)) {
                preparedStatement.setString(1, naziv);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String glavniGradNaziv = resultSet.getString("glavni_grad");
                    Grad glavniGrad = new Grad();
                    glavniGrad.setNaziv(glavniGradNaziv);
                    return new Drzava(id, naziv, glavniGrad);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}