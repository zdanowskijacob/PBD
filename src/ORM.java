import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ORM {
    private ResultSet res;
    private LinkedList<User> li;
    public ORM(ResultSet res) {
        this.res = res;
    }

    public void create(){
        try {
            li = new LinkedList<User>();
            while (res.next()) {
                li.add(new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void appendList(User u){
        li.addLast(u);
    }

    public LinkedList<User> getList(){
        return li;
    }
    public int getNextIndex() {
        if(li.size() == 0) {
            return 1;
        }
        else
            return li.size() + 1;
    }


}
