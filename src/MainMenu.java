import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        boolean valid = false;
        Scanner s = new Scanner(System.in);
        MarginMath login = new MarginMath();
        char option;

        System.out.println(" ");
        System.out.println("   --- Welcome To ---   ");
        System.out.println("  ---  MarginMath  ---  ");
        System.out.println(" ");
        do {
            System.out.println("1. Login MarginMath");
            System.out.println("2. Create new data");
            System.out.println("0. Exit");
            System.out.println("--------------------------");
            System.out.print("Enter your option: ");
            option = s.next().charAt(0);
            switch (option) {
                case '1':
                    boolean kondisi = login.LoadData();
                    if (kondisi == true) {
                        valid = true;
                        try {
                            MarginMath.main(new String[] {});
                        } catch (Exception e) {
                            System.out.println("An exception occurred: " + e.getMessage());
                        }
                    }
                    break;
                case '2':
                    try {
                        MarginMath.main(new String[] {});
                    } catch (Exception e) {
                        System.out.println("An exception occurred: " + e.getMessage());
                    }
                    valid = true;
                    break;
                case '0':
                    System.out.println("");
                    System.out.println("Terima Kasih!");
                    valid = true;
                    break;
                default:
                    System.out.println("");
                    System.out.println("<> Invalid Option <>");
                    System.out.println("");
                    break;
            }
        } while (valid == false);
        s.close();
    }
}