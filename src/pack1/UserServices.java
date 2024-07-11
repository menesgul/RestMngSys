package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserServices {
    public Connection getConnected() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/enesgul", "root", "enes6113905");
    }

    public ArrayList<User> getUsers() throws SQLException {
        Statement st = getConnected().createStatement();
        ResultSet rs = st.executeQuery("select * from users");
        ArrayList<User> users = new ArrayList<User>();
        while (rs.next()) {
            User user = new User();
            user.setUserID(rs.getInt(1));
            user.setUsername(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setUserRole(rs.getString(4));
            users.add(user);
        }
        return users;
    }
}
