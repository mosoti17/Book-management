import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UI {


    public void createNewBook(Connection connection) throws SQLException {

        JTextField titleField = new JTextField(40);
        JTextField isbnField = new JTextField(40);
        JTextField priceField = new JTextField(40);
        JTextField authorField = new JTextField(40);
        JTextField pagesField = new JTextField(40);
        JTextField editionField = new JTextField(40);
        JTextField dateField = new JTextField(40);
        JTextField genreField = new JTextField(40);


        JPanel myPanel = new JPanel(new GridLayout(18, 1, 5, 1));
        myPanel.add(new JLabel("Title:"));
        myPanel.add(titleField);
        myPanel.add(new JLabel("ISBN:"));
        myPanel.add(isbnField);
        myPanel.add(new JLabel("Price:"));
        myPanel.add(priceField);
        myPanel.add(new JLabel("Author:"));
        myPanel.add(authorField);
        myPanel.add(new JLabel("Number of Pages:"));
        myPanel.add(pagesField);
        myPanel.add(new JLabel("Edition:"));
        myPanel.add(editionField);
        myPanel.add(new JLabel("Genre:"));
        myPanel.add(genreField);
        myPanel.add(new JLabel("Date Published:"));
        myPanel.add(dateField);

//        myPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
//        myPanel.setPreferredSize(new Dimension(300, 500));
//        myPanel.setMaximumSize(new Dimension(300, 500));

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Enter book details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Book book = new Book(
                    titleField.getText(),
                    authorField.getText(),
                    isbnField.getText(),
                    dateField.getText(),
                    Integer.parseInt(pagesField.getText()),
                    editionField.getText(),
                    genreField.getText(),
                    Double.parseDouble(priceField.getText()));
            book.printBook();
            book.insertIntoBook(connection);

        }
    }


    public int selectOption() {
        JFrame f = new JFrame();
        String input = JOptionPane.showInputDialog(f," 1: Add book\n2: View All Books\n3: Delete a book\n5: Exit","Enter a option", 1);
        return Integer.parseInt(input);
    }

    public int deleteBook() {
        JFrame f = new JFrame();
        String input = JOptionPane.showInputDialog(f,"Book id","Delete book", 1);
        return Integer.parseInt(input);
    }


    public void displayAllBooks(ArrayList<Book> books) throws SQLException {
        JPanel myPanel = new JPanel(new GridLayout(0, 9, 0, 0));
        myPanel.add(new JLabel("Id"));
        myPanel.add(new JLabel("Title"));
        myPanel.add(new JLabel("ISBN"));
        myPanel.add(new JLabel("Price"));
        myPanel.add(new JLabel("Author"));
        myPanel.add(new JLabel("Number of Pages"));
        myPanel.add(new JLabel("Edition"));
        myPanel.add(new JLabel("Genre"));
        myPanel.add(new JLabel("Date Published"));
        for (Book book : books) {
            myPanel.add(new JLabel(String.valueOf(book.getId())));
            myPanel.add(new JLabel(book.getTitle()));
            myPanel.add(new JLabel(book.getISBN()));
            myPanel.add(new JLabel(String.valueOf(book.getPrice())));
            myPanel.add(new JLabel(book.getAuthor()));
            myPanel.add(new JLabel(String.valueOf(book.getNumberOfPages())));
            myPanel.add(new JLabel(book.getEdition()));
            myPanel.add(new JLabel(book.getGenre()));
            myPanel.add(new JLabel(book.getDatePublished()));
        }

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Books Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

        }


    }


}
