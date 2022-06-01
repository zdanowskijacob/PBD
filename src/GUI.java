import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JTable table;
    private JPanel mainPanel;
    private JTextField nameText;
    private JTextField surnameText;
    private JTextField emailText;
    private JButton addBtn;
    private JButton editBtn;
    private JButton removeBtn;


    public GUI(String title, DataBase db, ORM orm) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        showDataBase(db);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User u = new User(orm.getNextIndex(), nameText.getText().toUpperCase(), surnameText.getText().toUpperCase(),
                        emailText.getText().toUpperCase());
                if (u.getSurname().length() > 0 && u.getName().length() > 0)
                    db.addToDb(u);
                else
                    System.out.println("Podaj imie i nazwisko");
                showDataBase(db);
                clearForm();
            }
        });


        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = 0;
                int row = table.getSelectedRow();
                String index = table.getModel().getValueAt(row, column).toString();

                db.deleteFromDb(Integer.parseInt(index));
                showDataBase(db);

            }
        });
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = 0;
                int row = table.getSelectedRow();
                String index = table.getModel().getValueAt(row, column).toString();
                User u = new User(Integer.parseInt(index), nameText.getText().toUpperCase(), surnameText.getText().toUpperCase(),
                        emailText.getText().toUpperCase());
                db.updateDb(u, Integer.parseInt(index));
                showDataBase(db);
                clearForm();

            }
        });
    }


    public void showDataBase(DataBase db) {
        table.setModel(DbUtils.resultSetToTableModel(db.selectAll()));
    }

    public void clearForm() {
        nameText.setText("");
        surnameText.setText("");
        emailText.setText("");
    }

}

