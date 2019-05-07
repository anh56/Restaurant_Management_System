import javax.swing.table.AbstractTableModel;
import java.sql.*;

// A TableModel that supplies ResultSet data to a JTable.

// ResultSet rows and columns are counted from 1 and JTable
// rows and columns are counted from 0. When processing
// ResultSet rows or columns for use in a JTable, it is
// necessary to add 1 to the row or column number to manipulate
// the appropriate ResultSet column (i.e., JTable column 0 is
// ResultSet column 1 and JTable row 0 is ResultSet row 1).
public class ResultSetTableModel extends AbstractTableModel {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int numberOfRow;

    // keep track of database connection status
    private boolean connectedToDatabase = false;

    // initialize resultSet and obtain its meta data object;
    // determine number of rows
    public ResultSetTableModel(String driver, String url, String query) throws SQLException, ClassNotFoundException{
        // load database driver class
        Class.forName(driver);
        // connect to database
        connection = DriverManager.getConnection(url);
        // create Statement to query database
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );
        // update database connection status
        connectedToDatabase = true;
        // set query and execute it
        setQuery(query);
    }

    // get class that represents column type
    public Class getColumnClass(int column) throws IllegalStateException{
        // ensure database connection is available
        if(!connectedToDatabase)
            throw new IllegalStateException("Not Connected To Database");
        // determine Java class of column
        try {
            String className = metaData.getColumnClassName(column + 1);
            // return Class object that represents className
            return Class.forName(className);
        }
        // catch SQLExceptions and ClassNotFoundExceptions
        catch (Exception exception){
            exception.printStackTrace();
        }
        // if problems occur above, assume type Object
        return Object.class;
    }

    // get number of columns in ResultSet
    public int getColumnCount() throws IllegalStateException{
        // ensure database connection is available
        if (!connectedToDatabase)
            throw new IllegalStateException("Not Connected To Database");
        // determine number of columns
        try {
            return metaData.getColumnCount();
        }
        // catch SQLExceptions and print error message
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        // if problems occur above, return 0 for number of columns
        return 0;
    }

    // get name of a particular column in ResultSet
    public String getColumnName(int column) throws IllegalStateException{
        // get name of a particular column in ResultSet
        if (!connectedToDatabase)
            throw new IllegalStateException("Not Connected To Database");
        // determine column name
        try {
            return metaData.getColumnName(column + 1);
        }
        // catch SQLExceptions and print error message
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        // if problems, return empty string for column name
        return  "";
    }

    // return number of rows in ResultSet
    public int getRowCount() throws IllegalStateException{
        // ensure database connection is available
        if (!connectedToDatabase)
            throw new IllegalStateException("Not Connected To Database");
        return numberOfRow;
    }

    // obtain value in particular row and column
    public Object getValueAt(int row, int column) throws IllegalStateException{
        // ensure database connection is available
        if (!connectedToDatabase)
            throw new IllegalStateException("Not Connected To Database");
        // obtain a value at specified ResultSet row and column
        try {
            resultSet.absolute(row + 1);
            return resultSet.getObject(column + 1);
        }
        // catch SQLExceptions and print error message
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        // if problems, return empty string object
        return "";
    }

    // set new database query string
    public void setQuery(String query) throws SQLException, IllegalStateException{
        // ensure database connection is available
        if (!connectedToDatabase)
            throw new IllegalStateException("Not Connected To Database");
        // specify query and execute it
        resultSet = statement.executeQuery(query);
        // obtain meta data for ResultSet
        metaData = resultSet.getMetaData();
        // determine number of rows in ResultSet
        resultSet.last(); // move to last row
        numberOfRow = resultSet.getRow(); // get row number
        // notify JTable that model has changed
        fireTableStructureChanged();
    }

    // close Statement and Connection
    public void disconnectFromDatabase(){
        // close Statement and Connection
        try {
            statement.close();
            connection.close();
        }
        // catch SQLExceptions and print error message
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        // update database connection status
        finally {
            connectedToDatabase = false;
        }
    }
} // end class ResultSetTableModel

