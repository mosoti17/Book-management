import java.sql.*;

public class Library {
    public static void main(String arg[]) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            String createTableQuery = "CREATE TABLE IF NOT EXISTS   Books " +
                    "(id INTEGER not NULL, " +
                    " title VARCHAR(255), " +
                    " datePublished VARCHAR(30), " +
                    " numberOfPages INTEGER, " +
                    " edition VARCHAR(255), " +
                    " genre VARCHAR(30), " +
                    " description VARCHAR(1000), " +
                    " PRIMARY KEY ( id ))";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(createTableQuery);
            //Book book = new Book("Test 2", "Elvis", "20/10/2022", 100, "1st", "computer", "Computer book");
            //book.insertIntoBook(connection, 3);
            Library.delete(connection, 1);
            Library.getExistingBooks(connection);
        } catch (Exception exception) {
            System.out.println(exception);

        }

    }

    public static void insertIntoBook(Connection connection, Book book) throws SQLException {
        String INSERT_USERS_SQL = "INSERT INTO Books" +
                "  (id,title, datePublished, numberOfPages, edition, genre, description) VALUES " +
                " (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
        preparedStatement.setInt(1, 2);
        preparedStatement.setString(2, book.getTitle());
        preparedStatement.setString(3, book.getDatePublished());
        preparedStatement.setInt(4, book.getNumberOfPages());
        preparedStatement.setString(5, book.getEdition());
        preparedStatement.setString(6, book.getGenre());
        preparedStatement.setString(7, book.getDescription());

        System.out.println(preparedStatement);

        preparedStatement.executeUpdate();

    }


    public static void getExistingBooks(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(
                "select * from Books");
        Book book;
        while (resultSet.next()) {
            book = new Book(resultSet.getString("title"),
                    resultSet.getString("datePublished"),
                    resultSet.getString("datePublished"),
                    resultSet.getInt("numberOfPages"),
                    resultSet.getString("edition"),
                    resultSet.getString("genre"),
                    resultSet.getString("description"));
            book.setId(resultSet.getInt("id"));

            book.printBook();

        }

        resultSet.close();
        stmt.close();

    }


    public static void delete(Connection connection, int bookId) throws SQLException {

        Statement statement = connection.createStatement();
        String DELETE_BOOK = "delete from Books where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK);
        preparedStatement.setInt(1, bookId);

        preparedStatement.executeUpdate();


    }
}
