package com.pratham;

import BreezySwing.*;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.System.exit;

public class LibraryGui extends GBFrame {

    private Library library = new Library();

    private GBPanel mainPanel;

    //Drop down setup
    private JMenuItem addNewBookMenu;
    private JMenuItem showAllBooksMenu;
    private JMenuItem findBookByAuthorMenu;
    private JMenuItem findBookByTitleMenu;
    private JMenuItem showAllBorrowedBooksMenu;
    private JMenuItem showAllBooksOverdueMenu;
    private JMenuItem quitMenu;

    //New Book Screen
    private JTextField addTitleField;
    private JTextField addAuthorField;
    private JButton addNewBookBtn;

    //Find by Author Screen
    private JTextField findAuthorField;
    private JButton findAuthorBtn;

    //Find by Title Screen
    private JTextField findTitleField;
    private JButton findTitleBtn;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

    public LibraryGui() {

        // add menu
        addNewBookMenu = addMenuItem("Library","Add new book");
        showAllBooksMenu = addMenuItem("Library","Show all books");
        findBookByAuthorMenu = addMenuItem("Library","Find books by Author");
        findBookByTitleMenu = addMenuItem("Library","Find book by Title");
        showAllBorrowedBooksMenu = addMenuItem("Library","Show all borrowed books");
        showAllBooksOverdueMenu = addMenuItem("Library","Show all overdue books");
        quitMenu = addMenuItem("Library","Quit");

        // add panel and show books
        mainPanel = addPanel(1, 1, 1, 1);
        populateInitialBooks();
        showBooks(library.getBooks());

        // setup title, etc
        setTitle("Library");
        setLocationRelativeTo(null); //this displays JFrame to center position of a screen
        setVisible (true);
    }

    public void populateInitialBooks() {
        try {
            library.addBook("Cat In The Hat", "Dr. Seuss");
            Book book2 = library.addBook("The Grapes of Wrath", "John Steinbeck");
            book2.checkOut("John Smith", dateFormat.parse("11-25-2021"));
            library.addBook("To Kill A Mockingbird", "Harper Lee");
            Book book4 = library.addBook("Webster’s Dictionary", "Webster");
            book4.checkOut("Jane Doe", dateFormat.parse("12-21-2021"));
            Book book5 = library.addBook("Robinson Crusoe", "Daniel Dufoe");
            book5.checkOut("Jack Knife", dateFormat.parse("10-31-2021"));
        } catch (Exception ex) {
            // ignore because we are just populating data
        }
    }

    public void makeBold(JLabel label) {
        Font font = label.getFont();
        Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
        label.setFont(boldFont);
    }

    //show the new book screen
    public void createNewBookScreen() {
        setSize(600,150);
        mainPanel.addLabel("Name", 1, 1, 1, 1);
        addTitleField = mainPanel.addTextField("", 1, 2, 2, 1);
        addTitleField.requestFocus();
        mainPanel.addLabel("Author", 2, 1, 1, 1);
        addAuthorField = mainPanel.addTextField("", 2, 2, 2, 1);
        addNewBookBtn = mainPanel.addButton("Add", 6, 1, 3, 1);
        revalidate();
    }

    public void showBooks(ArrayList<Book> books) {
        setSize(800,100+25* books.size());
        makeBold(mainPanel.addLabel("Title", 1, 1, 1, 1));
        makeBold(mainPanel.addLabel("Author", 1, 2, 1, 1));
        makeBold(mainPanel.addLabel("Borrowed By", 1, 3, 1, 1));
        makeBold(mainPanel.addLabel("Borrow Date", 1, 4, 1, 1));
        makeBold(mainPanel.addLabel("Action", 1, 5, 1, 1));
        for (int i=0; i<books.size(); i++) {
            mainPanel.addLabel(books.get(i).getTitle(), i+2, 1, 1, 1);
            mainPanel.addLabel(books.get(i).getAuthorName(), i+2, 2, 1, 1);
            String borrowerName = books.get(i).getBorrowerName();
            if ( borrowerName == null ) {
                borrowerName = "--";
            }
            mainPanel.addLabel(borrowerName, i+2, 3, 1, 1);
            Date borrowDate = books.get(i).getBorrowDate();
            String borrowDateStr;
            if ( borrowDate != null ) {
                borrowDateStr = dateFormat.format(borrowDate);
            } else {
                borrowDateStr = "--";
            }
            mainPanel.addLabel(borrowDateStr, i+2, 4, 1, 1);
            if ( books.get(i).isBorrowed() ) {
                JButton btn = mainPanel.addButton("Return", i+2, 5, 1, 1);
                btn.setActionCommand(""+i); // store book index
            } else {
                JButton btn = mainPanel.addButton("Borrow", i+2, 5, 1, 1);
                btn.setActionCommand(""+i); // store book index
            }
        }
        revalidate();
    }

    public void createFindBookByAuthorScreen() {
        setSize(600,100);
        mainPanel.addLabel("Author", 1, 1, 1, 1);
        findAuthorField = mainPanel.addTextField("", 1, 2, 2, 1);
        findAuthorField.requestFocus();
        findAuthorBtn = mainPanel.addButton("Find", 6, 1, 3, 1);
        revalidate();
    }

    public void createFindBookByTitleScreen() {
        setSize(600,100);
        mainPanel.addLabel("Title", 1, 1, 1, 1);
        findTitleField = mainPanel.addTextField("", 1, 2, 2, 1);
        findTitleField.requestFocus();
        findTitleBtn = mainPanel.addButton("Find", 6, 1, 3, 1);
        revalidate();
    }

    public void createReturnBookScreen() {
        showBooks(library.findBorrowedBooks());
    }

    public void buttonClicked(JButton buttonObj) {
        if ( buttonObj == addNewBookBtn ) {
            //validate inputs
            if ( addTitleField.getText().isEmpty() ) {
                messageBox("Title cannot be empty.\nPlease enter a title.");
                addTitleField.requestFocus();
                return;
            }
            if ( addAuthorField.getText().isEmpty() ) {
                messageBox("Author cannot be empty.\nPlease enter an author.");
                addAuthorField.requestFocus();
                return;
            }

            //add book
            library.addBook(addTitleField.getText(), addAuthorField.getText());

            //show all books
            this.remove(mainPanel); //remove previous screen
            mainPanel = addPanel(1, 1, 1, 1); //add mew panel
            showBooks(library.getBooks());
            revalidate(); //refresh screen
        }
        else if ( buttonObj == findAuthorBtn ) {
            //validate inputs
            if ( findAuthorField.getText().isEmpty() ) {
                messageBox("Author cannot be empty.\nPlease enter an author.");
                addAuthorField.requestFocus();
                return;
            }

            //show matching books
            this.remove(mainPanel); //remove previous screen
            mainPanel = addPanel(1, 1, 1, 1); //add mew panel
            showBooks(library.findBooksByAuthor(findAuthorField.getText()));
        }
        else if ( buttonObj == findTitleBtn ) {
            //validate inputs
            if ( findTitleField.getText().isEmpty() ) {
                messageBox("Title cannot be empty.\nPlease enter a title.");
                addAuthorField.requestFocus();
                return;
            }

            //show matching books
            this.remove(mainPanel); //remove previous screen
            mainPanel = addPanel(1, 1, 1, 1); //add mew panel
            showBooks(library.findBooksByTitle(findTitleField.getText()));
        }
        else if ( buttonObj.getText().equals("Return") ) {
            int index = Integer.parseInt(buttonObj.getActionCommand());
            library.getBooks().get(index).checkIn();

            //show all books
            this.remove(mainPanel); //remove previous screen
            mainPanel = addPanel(1, 1, 1, 1); //add mew panel
            showBooks(library.getBooks());
            revalidate(); //refresh screen
        }
        else if ( buttonObj.getText().equals("Borrow") ) {
            int index = Integer.parseInt(buttonObj.getActionCommand());
            //library.getBooks().get(index).checkOut();
            GBDialog dialog =  new BorrowDialog(this, library.getBooks().get(index));
            dialog.setVisible(true);

            //show all books
            this.remove(mainPanel); //remove previous screen
            mainPanel = addPanel(1, 1, 1, 1); //add mew panel
            showBooks(library.getBooks());
            revalidate(); //refresh screen
        }
    }

    //Method for Drop Down
    public void menuItemSelected(JMenuItem menuItem) {
        if (mainPanel != null) {
            this.remove(mainPanel); //remove previous screen
        }
        mainPanel = addPanel(1, 1, 1, 1); //add new panel
        if ( menuItem == addNewBookMenu ) {
            createNewBookScreen();
        } else if ( menuItem == showAllBooksMenu ) {
            showBooks(library.getBooks());
        } else if ( menuItem == findBookByAuthorMenu ) {
            createFindBookByAuthorScreen();
        } else if ( menuItem == findBookByTitleMenu ) {
            createFindBookByTitleScreen();
        } else if ( menuItem == showAllBorrowedBooksMenu ) {
            showBooks(library.findBorrowedBooks());
        } else if ( menuItem == showAllBooksOverdueMenu ) {
            showBooks(library.findOverdueBooks());
        } else if ( menuItem == quitMenu ) {
            exit(0);
        }
        revalidate();
    }

    public class BorrowDialog extends GBDialog {

        private Book book;

        private JTextField borrowerName;
        private JTextField borrowDate;
        private JButton okBtn;
        private JButton cancelBtn;

        public BorrowDialog(JFrame parent, Book book){

            super (parent);                                 // ** REQUIRED **
            setTitle ("Input");
            setDlgCloseIndicator ("Cancel");
            setLocationRelativeTo(null);
            setSize (400, 100);

            // store the book
            this.book = book;

            addLabel("Borrower Name", 1, 1, 4, 1);
            borrowerName = addTextField ("", 1,2,4,1);
            addLabel("Borrower Date (mm-dd-yyyy)", 2, 1, 4, 1);
            borrowDate = addTextField (dateFormat.format(new Date()), 2,2,4,1);
            okBtn = addButton ("OK", 3,1,1,1);
            cancelBtn = addButton ("Cancel", 3,2,1,1);
        }

        public void buttonClicked(JButton buttonObj) {
            if ( buttonObj == okBtn ) {
                if ( borrowerName.getText().isEmpty() ) {
                    messageBox("Borrower Name cannot be empty.\nPlease provide right value");
                    borrowerName.requestFocus();
                    return;
                }
                if ( borrowDate.getText().length() < 10 ) {
                    messageBox("Borrow Date is not right format\nPlease provide in form mm-dd-yyyy");
                    borrowDate.requestFocus();
                    return;
                }
                Date date;
                try {
                    date = dateFormat.parse(borrowDate.getText());
                } catch (Exception ex) {
                    messageBox("Borrow Date is not right format\nPlease provide in form mm-dd-yyyy");
                    borrowDate.requestFocus();
                    return;
                }
                book.checkOut(borrowerName.getText(), date);
            }
            dispose();
        }
    }

    public static void main(String[] args) {
        JFrame gui = new LibraryGui();
    }
}
