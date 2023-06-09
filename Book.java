import java.sql.*;

/**
 * Model to manage book
 */
public class Book {

    private String title;
    private String author;
    private String ISBN;
    private String datePublished;
    private int numberOfPages;
    private String edition;
    private String genre;
    private double price;


    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book() {

    }

    /**
     * MAiin constructor for a book
     * @param title
     * @param author
     * @param ISBN
     * @param datePublished
     * @param numberOfPages
     * @param edition
     * @param genre
     * @param price
     */
    public Book(String title, String author, String ISBN, String datePublished, int numberOfPages, String edition, String genre, double price) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.datePublished = datePublished;
        this.numberOfPages = numberOfPages;
        this.edition = edition;
        this.genre = genre;
        this.price = price;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Display a book on the console
     */
    public void printBook() {
        System.out.println("Id: " + this.id + "\nTitle: " + this.title + "ISBN: " + this.ISBN + "\nAuthor: " + this.author + "\nDate published :" + this.datePublished + "\nNumber Of Pages: " + this.numberOfPages + "\nEdition:" + this.edition + "\nGenre:" + this.genre + "\nPrice: " + this.price + "\n------------------------ \n");
    }


    /**
     * Insert book into the database
     * @param connection connection to the database
     * @throws SQLException
     */

    public void insertIntoBook(Connection connection) throws SQLException {
        String INSERT_USERS_SQL = "INSERT INTO Books" +
                "  (title, isbn, datePublished, numberOfPages, author, edition, genre, price) VALUES " +
                " (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
        preparedStatement.setString(1, this.getTitle());
        preparedStatement.setString(2, this.getISBN());
        preparedStatement.setString(3, this.getDatePublished());
        preparedStatement.setInt(4, this.getNumberOfPages());
        preparedStatement.setString(5, this.getAuthor());
        preparedStatement.setString(6, this.getEdition());
        preparedStatement.setString(7, this.getGenre());
        preparedStatement.setDouble(8, this.getPrice());

        System.out.println(preparedStatement);

        preparedStatement.executeUpdate();

    }

    /**
     * Update the book in the database
     * @param connection connection to the database
     * @throws SQLException
     */
    public void updateBook(Connection connection) throws SQLException {
        String INSERT_USERS_SQL = "UPDATE  Books SET title= ?, isbn= ?, datePublished= ?, numberOfPages = ?,  author= ?, edition = ?, genre= ?, price=? where id= ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
        preparedStatement.setString(1, this.getTitle());
        preparedStatement.setString(2, this.getISBN());
        preparedStatement.setString(3, this.getDatePublished());
        preparedStatement.setInt(4, this.getNumberOfPages());
        preparedStatement.setString(5, this.getAuthor());
        preparedStatement.setString(6, this.getEdition());
        preparedStatement.setString(7, this.getGenre());
        preparedStatement.setDouble(8, this.getPrice());
        preparedStatement.setInt(9,this.getId());

        System.out.println(preparedStatement);

        preparedStatement.executeUpdate();

    }

}
