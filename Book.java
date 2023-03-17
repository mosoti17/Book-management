import java.sql.*;

public class Book {
    private String title;
    private String author;
    private String datePublished;
    private int numberOfPages;
    private String edition;
    private String genre;
    private String description;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book(String title, String author, String datePublished, int numberOfPages, String edition, String genre, String description) {
        this.title = title;
        this.author = author;
        this.datePublished = datePublished;
        this.numberOfPages = numberOfPages;
        this.edition = edition;
        this.genre = genre;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void printBook() {
        System.out.println("Id: "+ this.id +"\nTitle: "+ this.title + "\nDate published :" + this.datePublished +"\nNumber Of Pages: "+ this.numberOfPages+ "\nEdition:"+ this.edition + "\nGenre:" + this.genre +"\nDescription: "+ this.description + "\n------------------------ \n");
    }

    public void createBookTable(Connection connection) throws SQLException{
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


    }

    public  void insertIntoBook(Connection connection, int id) throws SQLException {
        String INSERT_USERS_SQL = "INSERT INTO Books" +
                "  (id,title, datePublished, numberOfPages, edition, genre, description) VALUES " +
                " (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, this.title);
        preparedStatement.setString(3, this.datePublished);
        preparedStatement.setInt(4, this.getNumberOfPages());
        preparedStatement.setString(5, this.getEdition());
        preparedStatement.setString(6, this.getGenre());
        preparedStatement.setString(7, this.getDescription());

        System.out.println(preparedStatement);

        preparedStatement.executeUpdate();

    }

}
