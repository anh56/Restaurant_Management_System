import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class DisplayQueryResults extends JFrame {
    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;databaseName=Restaurant;user=doanynhi;password=cselaba1604";

    static final String DEFAULT_QUERY = "SELECT * FROM Course";

    private ResultSetTableModel tableModel;
    private JTextArea queryArea;

    public DisplayQueryResults(){
        super("Displaying Query Results");
        try{
            System.setProperty("db2j.system.home", "D:\\Semester 4\\Principles of Database Management\\SQL Server\\MSSQL14.SQLEXPRESS\\MSSQL\\DATA");
            tableModel = new ResultSetTableModel(JDBC_DRIVER, DATABASE_URL, DEFAULT_QUERY);

            queryArea = new JTextArea(DEFAULT_QUERY, 3, 100);
            queryArea.setWrapStyleWord(true);
            queryArea.setLineWrap(true);

            JScrollPane scrollPane = new JScrollPane(queryArea,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            JButton submitButton = new JButton("Submit Query");

            Box box = Box.createHorizontalBox();
            box.add(scrollPane);
            box.add(submitButton);

            JTable resultTable = new JTable(tableModel);

            Container container = getContentPane();
            container.add(box, BorderLayout.NORTH);
            container.add(new JScrollPane(resultTable), BorderLayout.CENTER);

            submitButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try{
                                tableModel.setQuery(queryArea.getText());
                            }
                            catch (SQLException sqlException){
                                JOptionPane.showMessageDialog(null, sqlException.getMessage(),
                                        "Database Error", JOptionPane.ERROR_MESSAGE);
                            }

                            try{
                                tableModel.setQuery(DEFAULT_QUERY);
                                queryArea.setText(DEFAULT_QUERY);
                            }
                            catch (SQLException sqlException2){
                                JOptionPane.showMessageDialog(null, sqlException2.getMessage(),
                                        "Database Error", JOptionPane.ERROR_MESSAGE);

                                tableModel.disconnectFromDatabase();

                                System.exit(1);
                            }
                        }
                    }
            );
            setSize(500, 250);
            setVisible(true);
        }
        catch (ClassNotFoundException classNotFound){
            JOptionPane.showMessageDialog(null, "Database Driver Not Found",
                    "Driver Not Found", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null, sqlException.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);

            tableModel.disconnectFromDatabase();

            System.exit(1);
        }

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        tableModel.disconnectFromDatabase();
                        System.exit(0);
                    }
                }
        );
    }

    public static void main(String args[]){
        new DisplayQueryResults();
    }
}
