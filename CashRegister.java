import java.util.*;
import java.util.regex.*;
import java.io.*;


public class CashRegister {
    static Scanner scan = new Scanner(System.in);

    static ArrayList<Integer> quantity = new ArrayList<>();
    static ArrayList<String> products = new ArrayList<>();
    static ArrayList<Double> price = new ArrayList<>();
    static ArrayList<String> username = new ArrayList<>();
    static ArrayList<String> password = new ArrayList<>();

    // this page is the log in page
    public static void beginning() {
        while (true) {

            System.out.println("--------------------------------------------");
            System.out.println("    Please pick and option");
            System.out.println("        1.Register");
            System.out.println("        2.Log in");
            System.out.println("        3.Exit");

            System.out.println("     Enter answer: ");
            int answer = scan.nextInt();

            switch(answer) {
                
                case 1:
                register1();
                break;
                case 2:
                login();
                break;
                case 3:
                terminate();
                default:
                System.out.println("INVALID INPUT. PLEASE INPUT A VALID OPTION.");
            }
            
        }
    }

    public static void register1() {
        
        scan.nextLine();

        while(true) {

            System.out.println("Enter Username: ");
            String user = scan.nextLine();

            Pattern p = Pattern.compile("^[a-zA-Z0-9]{3,20}$");
            Matcher m = p.matcher(user);
            if(m.matches()) {
                if(username.contains(user)) {
                    System.out.println("Username is already takes. Choose Another");    
                    break;
                }
                else {
                    username.add(user);
                    register2();
                    break;
                }
            }
            else{
                System.out.println("Username must be alpanumeric and between 3-20 characters long.");
                username.clear();
            }
        }
    }
    public static void register2(){
        while(true) {
            System.out.println("Enter Password: ");
            String pass = scan.nextLine();

            Pattern p1 = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,20}$");
            Matcher m1 = p1.matcher(pass);

            if(m1.matches()) {

                password.add(pass);
                break;

            }
            else {

                System.out.println("Password must Contain at least one uppercase letter, one number, and must be 6-20 characters long.");
                break;
            }
        }
        beginning();  
    }

    public static void login() {
        String user1;
        char answer;

        user1 = scan.nextLine();
        if(username.isEmpty()) {
            System.out.println("No Registerd Username. please proceed to Register page.");
            return;
        }

        while (true) {
            System.out.println("Username: ");
            user1 = scan.nextLine();

            if(username.contains(user1)) {
                break;
            }
            else{
                
                while(true) {
                    System.out.println("Invalid Username. please Try again (y/n): ");
                    answer = scan.next().toLowerCase().charAt(0);

                    if(answer == 'y') {
                        return;

                    }
                    else if(answer == 'n') {
                        terminate();
                    }
                    else {
                        System.out.println("INVALID INPUT.");
                    }
                } 
            }
        }
        
        while (true) {
            System.out.println("Password: ");
            String pass = scan.nextLine();

            int index = username.indexOf(user1);

            if(index >= 0 && index < password.size() && pass.equals(password.get(index)))  {
                start();
            }
            else{

                System.out.println("Invalid Password or Username. Please Try Again. (y/n): ");
                answer = scan.next().toLowerCase().charAt(0);

                if(answer == 'y') {
                    return;
                }
                else if(answer == 'n') {
                    terminate();
                }
                else{
                    System.out.println("INVALID INPUT.");
                }
            }
        }
        
    }

    // displays Actions
    public static void start() {
        while (true) {
            System.out.println("-------------------------------------------");
            System.out.println("            CASH REGISTER");
            System.out.println("           PLEASE ENTER ACTION");
            System.out.println("          1. Add Items");
            System.out.println("          2. Remove Items");
            System.out.println("          3. Confirm & Pay");
            System.out.println("          4. Exit");
            System.out.println("-------------------------------------------");

            int action = scan.nextInt();
            scan.nextLine(); // consume newline

            switch (action) {
                case 1:
                    menu();
                    break;
                case 2:
                    removeProducts();
                    break;
                case 3:
                    checkout();
                    break;
                case 4:
                    terminate();
                    return;
                default:
                    System.out.println("Invalid Choice. Please input a correct response.");
            }
        }
    }

    public static void menu() {
        System.out.println("-------- Ice Cream Menu --------");
        System.out.println("  a1: Cone Ice cream 20.00 Php");
        System.out.println("  a2: Cup Ice cream 25.00 Php");
        System.out.println("  a3: Bread Ice Cream 30.00 Php");
        System.out.println("  a4: Special Ice Cream Cup 40.00 Php");
        System.out.println("  a5: Deluxe Ice Cream Cup 50.00 Php");

        addProducts();
    }

    public static void addProducts() {
        while (true) {
            String code;
            int qtt;
            char answer;

            System.out.print("\nSelect Product Code: ");
            code = scan.nextLine().trim().toLowerCase();

            if (!Arrays.asList("a1", "a2", "a3", "a4", "a5").contains(code)) {
                System.out.println("Invalid code. Please try again.");
                continue;
            }

            System.out.print("Enter quantity: ");
            qtt = scan.nextInt();
            scan.nextLine(); // consume newline

            switch (code) {
                case "a1":
                    products.add("Cone Ice Cream");
                    price.add(20.00);
                    quantity.add(qtt);
                    break;
                case "a2":
                    products.add("Cup Ice Cream");
                    price.add(25.00);
                    quantity.add(qtt);
                    break;
                case "a3":
                    products.add("Bread Ice Cream");
                    price.add(30.00);
                    quantity.add(qtt);
                    break;
                case "a4":
                    products.add("Special Ice Cream Cup");
                    price.add(40.00);
                    quantity.add(qtt);
                    break;
                case "a5":
                    products.add("Deluxe Ice Cream Cup");
                    price.add(50.00);
                    quantity.add(qtt);
                    break;
            }

            System.out.println("Product Added.");

            System.out.print("Do you want to add another product? (y/n): ");
            answer = scan.next().toLowerCase().charAt(0);
            scan.nextLine(); // consume newline

            if (answer == 'n') break;
        }
    }

    public static void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No Products Listed.");
            return;
        }

        System.out.println("\nProducts Selected:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i) + " - Php " + price.get(i) + " - Quantity: " + quantity.get(i));
        }
    }

    public static void removeProducts() {
        if (products.isEmpty()) {
            System.out.println("No Products listed.");
            return;
        }

        while (true) {
            displayProducts();

            System.out.print("Enter product number to remove: ");
            int productNum = scan.nextInt();

            if (productNum < 1 || productNum > products.size()) {
                System.out.println("Invalid product number.");
                continue;
            }

            System.out.print("Enter how many quantities to remove: ");
            int removeVal = scan.nextInt();
            scan.nextLine(); // consume newline

            int currentQtt = quantity.get(productNum - 1);

            if (removeVal > currentQtt) {
                System.out.println("Amount exceeds current quantity.");
            } else {
                quantity.set(productNum - 1, currentQtt - removeVal);
                System.out.println("New quantity: " + (currentQtt - removeVal));
            }

            System.out.print("Do you want to remove another product? (y/n): ");
            char answer = scan.next().toLowerCase().charAt(0);
            scan.nextLine(); // consume newline

            if (answer == 'n') break;
        }
    }

    public static void checkout() {
        if (products.isEmpty()) {
            System.out.println("No products to checkout.");
            return;
        }

        double totalPrice = 0;

        System.out.println("\n-------- Receipt --------");
        for (int i = 0; i < products.size(); i++) {
            double itemTotal = price.get(i) * quantity.get(i);
            totalPrice += itemTotal;
            System.out.println(products.get(i) + " - " + quantity.get(i) + " x Php " + price.get(i) + " = Php " + itemTotal);
        }

        System.out.println("-------------------------");
        System.out.println("Total Price: Php " + totalPrice);
        System.out.print("Enter payment amount: ");
        double payment = scan.nextDouble();
        scan.nextLine(); // consume newline

        if (payment < totalPrice) {
            System.out.println("Insufficient payment. Please try again.");
            return;
        }

        double change = payment - totalPrice;
        System.out.println("Payment Successful. Change: Php " + change);
        System.out.println("Transaction Complete.");

        createrecipt();
        writerecipt();

        System.out.println("Transaction Saved!");

        products.clear();
        price.clear();
        quantity.clear();

    }

    public static void terminate() {
        System.out.println("Thank you for using this cash register.");
        System.exit(0);
    }

    public static void createrecipt() {
        File file = new File("Transactions.txt");

        try {
            if(file.createNewFile()) {
                System.out.println("Transaction Record successfully made!");
            }
            else {
                System.out.println("Record Already Exist.");
            }
        }
        catch (IOException e) {
            System.out.println("Failed to create Record. please try again later.");
            e.printStackTrace();
        }
    }   

    public static void writerecipt() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Transactions.txt", true))) {
            writer.write("------------------------------------");
            writer.write("      Transactions\n");
            writer.write("Date: " + new Date().toString() + "\n");

            if(!username.isEmpty()) {
                writer.write("Username: " + username.get(username.size() - 1) + "\n");
            }
            else {
                writer.write("Username: N/A \n");
            }

            writer.write("------------------------------------");

            writer.write(String.format("%-25s %-10s %-10s %-10s\n", "Product", "Qty", "Price", "Total"));

            double grandtotal = 0;

            for (int i = 0; i < products.size(); i++) {
                String prd = products.get(i);
                int qty = quantity.get(i);
                double prc = price.get(i);
                double total = prc * qty;
                grandtotal += total;
                 writer.write(String.format("%-25s %-10d %-10.2f %-10.2f\n", prd, qty, prc, total));
            }
            
            writer.write("------------------------------------\n");
            writer.write(String.format("Total: %.2f PHP\n", grandtotal));
            writer.write("------------------------------------\n");
        }

        catch (IOException e) {
            System.out.println("FAILED TO WRITE RECIPT.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        beginning();
    }
}
