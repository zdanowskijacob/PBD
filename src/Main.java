import javax.swing.*;

public class Main {

    private static DataBase db = new DataBase("jdbc:sqlite:.\\resources\\ABD.db");
    private static ORM orm;

    public static void main(String[] args){

        db.connect();
        orm = new ORM(db.selectAll());
        orm.create();

        JFrame frame = new GUI("PBD", db, orm);
        frame.setVisible(true);


    }
}
