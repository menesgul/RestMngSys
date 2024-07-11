package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReservationDB {

    public Connection getConnected() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/enesgul", "root", "enes6113905");
    }

    public ArrayList<Reservat> getRes() throws SQLException {
        Statement st = getConnected().createStatement();
        ResultSet rs = st.executeQuery("select * from reservations");
        ArrayList<Reservat> reservations = new ArrayList<Reservat>();
        while (rs.next()) {
            Reservat res = new Reservat();
            res.setresID(rs.getInt(1));
            res.setName(rs.getString(2));
            res.setSurname(rs.getString(3));
            res.setDate(rs.getString(4));
            res.setHowManyPeople(rs.getString(5));
            reservations.add(res);
        }
        return reservations;
    }

    public void saveRes(Reservat r) throws SQLException {
        String query = "insert into reservations values(?,?,?,?,?)";
        PreparedStatement ps = getConnected().prepareStatement(query);
        ps.setInt(1, r.getresID());
        ps.setString(2, r.getName());
        ps.setString(3, r.getSurname());
        ps.setString(4, r.getDate());
        ps.setString(5, r.getHowManyPeople());
        ps.executeUpdate();
    }

    public void deleteRes(int resID) throws SQLException {
        String query = "delete from reservations where resID=?";
        PreparedStatement ps = getConnected().prepareStatement(query);
        ps.setInt(1, resID);
        ps.executeUpdate();
    }
}
