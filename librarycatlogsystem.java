import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String title;
    String author;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author;
    }
}

public class librarycatlogsystem {
    private static ArrayList<Book> catalog = new ArrayList<>();

    public static void addBook(String title, String author) {
        Book book = new Book(title, author);
        catalog.add(book);
        System.out.println("Book added successfully!");
    }

    public static void listBooks() {
        if (catalog.isEmpty()) {
            System.out.println("No books in the catalog.");
        } else {
            System.out.println("Listing all books:");
            for (Book book : catalog) {
                System.out.println(book);
            }
        }
    }

    public static void searchByTitle(String title) {
        for (Book book : catalog) {
            if (book.title.equalsIgnoreCase(title)) {
                System.out.println("Found: " + book);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public static void searchByAuthor(String author) {
        for (Book book : catalog) {
            if (book.author.equalsIgnoreCase(author)) {
                System.out.println("Found: " + book);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nLibrary Catalog System");
            System.out.println("1. Add a Book");
            System.out.println("2. List all Books");
            System.out.println("3. Search by Title");
            System.out.println("4. Search by Author");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author name: ");
                    String author = sc.nextLine();
                    addBook(title, author);
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    System.out.print("Enter book title: ");
                    title = sc.nextLine();
                    searchByTitle(title);
                    break;
                case 4:
                    System.out.print("Enter author name: ");
                    author = sc.nextLine();
                    searchByAuthor(author);
                    break;
                case 5:
                    System.out.println("Exiting system.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
