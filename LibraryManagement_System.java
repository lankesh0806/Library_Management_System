package Library_Management;

import java.util.Scanner;

class LibraryManagementSystem {

    public static boolean login(Scanner sc) {
        System.out.println("===== Admin Login =====");
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        if (username.equals("admin") && password.equals("admin123")) {
            System.out.println("Login successful!\n");
            return true;
        } else {
            System.out.println("Incorrect credentials.\n");
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!login(sc)) {
            System.out.println("Exiting system due to failed login.");
            return;
        }

        Library library = new Library();
        int choice;

        do {
            System.out.println("===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Delete Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Search Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author name: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(id, title, author));
                    break;
                case 2:
                    library.viewAllBooks();
                    break;
                case 3:
                    System.out.print("Enter book ID to delete: ");
                    int delId = sc.nextInt();
                    library.deleteBook(delId);
                    break;
                case 4:
                    System.out.print("Enter book ID to issue: ");
                    int issueId = sc.nextInt();
                    library.issueBook(issueId);
                    break;
                case 5:
                    System.out.print("Enter book ID to return: ");
                    int returnId = sc.nextInt();
                    library.returnBook(returnId);
                    break;
                case 6:
                    System.out.print("Enter title/author to search: ");
                    String keyword = sc.nextLine();
                    library.searchBook(keyword);
                    break;
                case 7:
                    System.out.println("Exiting Library System...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 7);

        sc.close();
    }
}

