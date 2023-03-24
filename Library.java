import java.sql.*;
import java.util.ArrayList;

public class Library {
    public static void main(String arg[]) {
        Connection connection = null;
        try {
            UI joptionUi = new UI();

            connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            String createTableQuery = "CREATE TABLE IF NOT EXISTS   Books " +
                    "(" +
                    "id INTEGER not NULL AUTO_INCREMENT, " +
                    " isbn VARCHAR(255), " +
                    " title VARCHAR(255), " +
                    " datePublished VARCHAR(30), " +
                    " numberOfPages INTEGER, " +
                    " author VARCHAR(255), " +
                    " edition VARCHAR(255), " +
                    " genre VARCHAR(30), " +
                    " price DECIMAL(15,2), " +
                    " PRIMARY KEY ( id )" +
                    ")";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(createTableQuery);

            int option;
            do {
                option = joptionUi.selectOption();
                switch (option) {
                    case 1 -> {
                        Book book = new Book();
                        joptionUi.createNewBook(connection, false, book);
                    }
                    case 2 -> {
                        ArrayList<Book> books = Library.getExistingBooks(connection);
                        joptionUi.displayAllBooks(books);
                    }
                    case 3 -> {
                        int bookId = joptionUi.updateBook();
                        Book book = Library.getBook(connection,bookId);
                        joptionUi.createNewBook(connection, true, book);
                    }
                    case 4 -> {
                        int bookId = joptionUi.deleteBook();
                        Library.delete(connection, bookId);
                    }
                    default -> {
                    }
                }
            } while (option < 5);

        } catch (Exception exception) {
            System.out.println(exception);

        }

    }

    public static ArrayList<Book> getExistingBooks(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(
                "select * from Books");
        ArrayList<Book> books = new ArrayList<Book>();
        while (resultSet.next()) {
            Book book;
            book = new Book(resultSet.getString("title"),
                    resultSet.getString("author"),
                    resultSet.getString("isbn"),
                    resultSet.getString("datePublished"),
                    resultSet.getInt("numberOfPages"),
                    resultSet.getString("edition"),
                    resultSet.getString("genre"),
                    resultSet.getDouble("price"));
            book.setId(resultSet.getInt("id"));
            books.add(book);

        }

        resultSet.close();
        stmt.close();

        return books;

    }


    public static void delete(Connection connection, int bookId) throws SQLException {

        Statement statement = connection.createStatement();
        String DELETE_BOOK = "delete from Books where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK);
        preparedStatement.setInt(1, bookId);

        preparedStatement.executeUpdate();


    }

    public static Book getBook(Connection connection, int bookId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("select * from Books where id = ?");
        stmt.setInt(1, bookId);
        ResultSet resultSet = stmt.executeQuery();
        Book book;
        while (resultSet.next()) {

            book = new Book(resultSet.getString("title"),
                    resultSet.getString("author"),
                    resultSet.getString("isbn"),
                    resultSet.getString("datePublished"),
                    resultSet.getInt("numberOfPages"),
                    resultSet.getString("edition"),
                    resultSet.getString("genre"),
                    resultSet.getDouble("price"));
            book.setId(resultSet.getInt("id"));

            return book;

        }

        resultSet.close();
        stmt.close();

        return null;


    }
}
