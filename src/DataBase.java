import java.sql.*;

public class DataBase {
    private String url;
    private Connection c;

    DataBase(String url) {
        this.url = url;
    }

    public void connect() {
        try {
            c = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnect() {
        try {
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet selectAll() {
        Statement s;
        ResultSet res = null;
        try {
            s = c.createStatement();
            res = s.executeQuery("SELECT * FROM USERS");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public boolean addToDb(User u) {
        Statement s;
        String query = buildAddQuery(u);

        try {
            s = c.createStatement();
            s.executeUpdate(query);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteFromDb(int index) {
        Statement s;
        String query = "DELETE FROM USERS WHERE ID=" + index;

        try {
            s = c.createStatement();
            s.executeUpdate(query);

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateDb(User u, int index) {
        Statement s;
        String query = buildUpdateQuery(u, index);

        try {
            System.out.println(query);
            s = c.createStatement();
            s.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String buildUpdateQuery(User u, int index) {
        String query = "UPDATE USERS SET ";
        if (u.getName().length() > 0) {
            query += "NAME='" + u.getName() + "' ";
        }
        if (u.getName().length() > 0 && u.getSurname().length() > 0) {
            query += ", SURNAME='" + u.getSurname() + "' ";
        } else if (u.getSurname().length() > 0) {
            query += "SURNAME='" + u.getSurname() + "' ";
        }
        if ((u.getName().length() > 0 || u.getSurname().length() > 0) && u.getEmail().length() > 0) {
            query += ", EMAIL='" + u.getEmail() + "' ";
        } else if (u.getEmail().length() > 0) {
            query += "EMAIL='" + u.getEmail() + "' ";
        }

        query += "WHERE ID=" + index + ";";

        return query;
    }


    public String buildAddQuery(User u) {

        String query = "INSERT INTO USERS(NAME, SURNAME, EMAIL) VALUES ('" + u.getName() + "', '" + u.getSurname()
                + "', '" +
                u.getEmail() + "');";
        if (u.getName().length() > 0 && u.getSurname().length() > 0)
            return query;

        return null;
    }


}
