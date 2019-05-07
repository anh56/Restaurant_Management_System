import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

// Display the contents of the tables in the Restaurant database
public class DisplayQueryResults extends JFrame {
    // JDBC driver and database URL
    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;databaseName=Books;user=ANH;password=12300";

    // default query selects all rows from authors table
    static final String DEFAULT_QUERY = "SELECT * FROM Course";

    private ResultSetTableModel tableModel;
    private JTextArea queryArea;

    // create ResultSetTableModel and GUI
    public DisplayQueryResults(){
        super("Displaying Query Results");
        // create ResultSetTableModel and display database table
        try{
            // specify location of database on filesystem
            System.setProperty("db2j.system.home", "D:\\Semester 4\\Principles of Database Management\\SQL Server\\MSSQL14.SQLEXPRESS\\MSSQL\\DATA");
            // create TableModel for results of query SELECT * FROM Course
            tableModel = new ResultSetTableModel(JDBC_DRIVER, DATABASE_URL, DEFAULT_QUERY);

            // set up JTextArea in which user types queries
            queryArea = new JTextArea(DEFAULT_QUERY, 3, 100);
            queryArea.setWrapStyleWord(true);
            queryArea.setLineWrap(true);

            JScrollPane scrollPane = new JScrollPane(queryArea,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            // set up JButton for submitting queries
            JButton submitButton = new JButton("Submit Query");

            // create Box to manage placement of queryArea and submitButton in GUI
            Box box = Box.createHorizontalBox();
            box.add(scrollPane);
            box.add(submitButton);

            // create JTable delegate for tableModel
            JTable resultTable = new JTable(tableModel);

            // place GUI components on content pane
            Container container = getContentPane();
            container.add(box, BorderLayout.NORTH);
            container.add(new JScrollPane(resultTable), BorderLayout.CENTER);

            // create event listener for submitButton
            submitButton.addActionListener(
                    new ActionListener() {
                        // pass query to table model
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // perform a new query
                            try{
                                tableModel.setQuery(queryArea.getText());
                            }
                            // catch SQLExceptions when performing a new query
                            catch (SQLException sqlException){
                                JOptionPane.showMessageDialog(null, sqlException.getMessage(),
                                        "Database Error", JOptionPane.ERROR_MESSAGE);
                            }

                            // try to recover from invalid user query by executing default query
                            try{
                                tableModel.setQuery(DEFAULT_QUERY);
                                queryArea.setText(DEFAULT_QUERY);
                            }
                            // catch SQLException when performing default query
                            catch (SQLException sqlException2){
                                JOptionPane.showMessageDialog(null, sqlException2.getMessage(),
                                        "Database Error", JOptionPane.ERROR_MESSAGE);

                                // ensure database connection is closed
                                tableModel.disconnectFromDatabase();

                                System.exit(1); // terminate application
                            } // end inner catch
                        } // end outer catch
                    } // end actionPerformed
                 // end ActionListener inner class
            ); // end call to addActionListener
            // set window size and display window
            setSize(500, 250);
            setVisible(true);
        } // end try
        // catch ClassNotFoundException thrown by ResultSetTableModel if database driver not found
        catch (ClassNotFoundException classNotFound){
            JOptionPane.showMessageDialog(null, "Database Driver Not Found",
                    "Driver Not Found", JOptionPane.ERROR_MESSAGE);
            System.exit(1); // terminate application
        } // end catch
        // catch SQLException thrown by ResultSetTableModel
        // if problems occur while setting up database
        // connection and querying database
        catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null, sqlException.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);

            // ensure database connection is closed
            tableModel.disconnectFromDatabase();

            System.exit(1); // terminate application
        }

        // dispose of window when user quits application (this overrides the default of HIDE_ON_CLOSE)
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // ensure database connection is closed when user quits application
        addWindowListener(
                new WindowAdapter() {
                    // disconnect from database and exit when window has closed
                    @Override
                    public void windowClosed(WindowEvent e) {
                        tableModel.disconnectFromDatabase();
                        System.exit(0);
                    }
                }
        );
    } // end DisplayQueryResults constructor

    // execute application
    public static void main(String args[]){
        new DisplayQueryResults();
    }
} // end class DisplayQueryResults
