package com.kevin;


        /*

        2. Metoda createUser ar putea primi ca parametru un obiect de tip User, asa incat, daca pe viitor te hotarasti sa adaugi si alte field-uri in clasa User,
        sa iti fie usor sa actualizezi si metoda asta, fara a fi nevoit sa ii schimbi semnatura.

        3. In metoda findAllUserRepository, la linia 41 din clasa UserRepository, instantiezi un obiect User cu nume si varsta temporare, pe care apoi le inlocuiesti cu
        datele citite din db. Ai putea evita numele si varsta temporare, daca mai intai ai citi din db, ai stoca numele si varsta in cate o variabila si doar apoi sa instantiezi userul.

        4. Foarte fain ca ai facut overload la metodele de update si delete... Te poti gandi la un mod prin care sa obtii in continuare aceeasi functionalitate, dar fara sa duplici cod?*/


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserRepository{
    private User user;
    private Connection getConnection() throws ClassNotFoundException, SQLException {

       Class.forName("org.postgresql.Driver");


        final String URL = "jdbc:postgresql://54.93.65.5:5432/kevin";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";


        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        return conn;

    }
    public void createUser(String name, int age) throws ClassNotFoundException, SQLException{
        PreparedStatement pSt=getConnection().prepareStatement("INSERT INTO users (name,age) VALUES (?,?)");
        pSt.setString(1,name);
        pSt.setInt(2, age);
        int rowsInserted=pSt.executeUpdate();

        pSt.close();
        getConnection().close();
    }
    public List findAllUserRepository()throws ClassNotFoundException, SQLException{
        Statement st = getConnection().createStatement();

        ResultSet rs = st.executeQuery("SELECT name,age FROM users");

        List<User> listaDeUser = new ArrayList();
        while (rs.next()) {
            User u = new User("Andrei",12);
            u.setName(rs.getString("name").trim());
            u.setAge(rs.getInt("age"));

            listaDeUser.add(u);
        }

        rs.close();
        st.close();
        getConnection().close();

        return listaDeUser;
    }
    public void updateNameUser(String name, int age) throws ClassNotFoundException, SQLException{
        PreparedStatement pSt = getConnection().prepareStatement("UPDATE USERS SET name=? WHERE age=?");
        pSt.setString(1, name);
        pSt.setInt(2, age);

        int rowsUpdated = pSt.executeUpdate();

        pSt.close();
        getConnection().close();
    }
    public void updateAgeUser(String name, int age) throws ClassNotFoundException, SQLException{
        PreparedStatement pSt = getConnection().prepareStatement("UPDATE USERS SET age=? WHERE name=?");
        pSt.setInt(1, age);
        pSt.setString(2, name);

        int rowsUpdated = pSt.executeUpdate();

        pSt.close();
        getConnection().close();
    }
    public void updateAgeUser(int age,String name, int oldAge) throws ClassNotFoundException, SQLException{
        PreparedStatement pSt = getConnection().prepareStatement("UPDATE USERS SET age=? WHERE name=? and age=?");
        pSt.setInt(1, age);
        pSt.setString(2, name);
        pSt.setInt(3, oldAge);

        int rowsUpdated = pSt.executeUpdate();

        pSt.close();
        getConnection().close();
    }
    public void updateNameUser(String name,String oldName, int age) throws ClassNotFoundException, SQLException{
        PreparedStatement pSt = getConnection().prepareStatement("UPDATE USERS SET name=? WHERE name=? and age=?");
        pSt.setString(1, name);
        pSt.setString(2,oldName);
        pSt.setInt(3, age);

        int rowsUpdated = pSt.executeUpdate();

        pSt.close();
        getConnection().close();
    }
    public void deleteUser(int age)throws ClassNotFoundException, SQLException{
        PreparedStatement pSt = getConnection().prepareStatement("DELETE FROM USERS WHERE age=?");
        pSt.setInt(1, age);

        int rowsDeleted = pSt.executeUpdate();
        System.out.println(rowsDeleted + " rows were deleted.");

        pSt.close();
        getConnection().close();
    }
    public void deleteUser(String name)throws ClassNotFoundException, SQLException{
        PreparedStatement pSt = getConnection().prepareStatement("DELETE FROM USERS WHERE name=?");
        pSt.setString(1, name);

        int rowsDeleted = pSt.executeUpdate();
        System.out.println(rowsDeleted + " rows were deleted.");

        pSt.close();
        getConnection().close();
    }
    public void deleteUser(String name,int age)throws ClassNotFoundException, SQLException{
        PreparedStatement pSt = getConnection().prepareStatement("DELETE FROM USERS WHERE name=? and age=?");
        pSt.setString(1, name);
        pSt.setInt(2, age);

        int rowsDeleted = pSt.executeUpdate();
        System.out.println(rowsDeleted + " rows were deleted.");

        pSt.close();
        getConnection().close();
    }
}
