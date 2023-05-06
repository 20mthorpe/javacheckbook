import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    // find account by id and return the Account
    public static Account find_account(ArrayList<Account> list, int id) {
        for(Account act: list) {
            if(act.get_id() == id) {
                return act;
            }
        }
        System.out.println("Account not found.");
        return null;
    }

    // Main
    public static void main(String[] args) {

        //initial welcome
        System.out.println("Hello and welcome!");
        System.out.println("This program is designed to keep track of bank accounts.");
        System.out.println("Please select an option from the following menu.");

        //initialize an array to hold the accounts
        ArrayList<Account> accounts = new ArrayList<>();

        // Create a loop for the menu
        boolean run = true;

        // Make a scanner to get input
        Scanner scan = new Scanner(System.in);

        // Main Menu Loop
        while(run) {
            System.out.println();
            System.out.println("1. Create New Account");
            System.out.println("2. Delete An Account");
            System.out.println("3. Show All Account Info");
            System.out.println("4. Find account by ID");
            System.out.println("5. Add Deposit");
            System.out.println("6. Make Withdrawal");
            System.out.println("7. Save to File");
            System.out.println("8. Retrieve From File");
            System.out.println("9. Exit the System");
            System.out.print("Choice: ");

            int choice = scan.nextInt();

            // Create a new account
            if(choice == 1) {
                // Get all attributes then add to the list
                System.out.print("What name will the account be under? ");
                scan.nextLine();
                String name = scan.nextLine();
                System.out.print("What is the starting balance of this account? ");
                float balance = scan.nextFloat();
                Account act = new Account(name, balance);
                accounts.add(act);
            }

            // Delete an account
            if(choice == 2) {
                // Find account, then delete it from the list
                System.out.print("What is the account id? ");
                int id = scan.nextInt();
                Account act = find_account(accounts, id);
                if(act != null) {
                    Iterator<Account> iterator = accounts.iterator();
                    while(iterator.hasNext()){
                        Account account = iterator.next();
                        if(account == act) {
                            accounts.remove(account);
                        }
                    }
                }
            }

            // Display all account info
            else if(choice == 3) {
                // Loop through list, displaying each accounts information
                for(Account act: accounts) {
                    System.out.println();
                    act.display();
                }
            }

            // Find the Account
            else if(choice == 4) {
                // Use find_account function, then display the information
                System.out.print("What is the account id? ");
                int id = scan.nextInt();
                Account act = find_account(accounts, id);
                if(act != null) {
                    System.out.println("Here is the account information: ");
                    act.display();
                }
            }

            // Make a Deposit
            else if(choice == 5){
                // First find account, then make the deposit & display
                System.out.print("What is the account id? ");
                int id = scan.nextInt();
                Account act = find_account(accounts, id);
                if(act != null) {
                    System.out.print("How much would you like to deposit? ");
                    float deposit = scan.nextInt();
                    act.deposit(deposit);
                    System.out.println("Here is the updated account information:");
                    act.display();
                }
            }

            // Make a Withdrawal
            else if(choice == 6){
                // First find account, then make the withdrawal & display
                System.out.print("What is the account id? ");
                int id = scan.nextInt();
                Account act = find_account(accounts, id);
                if(act != null) {
                    System.out.print("How much would you like to withdraw? ");
                    float withdraw = scan.nextInt();
                    act.withdraw(withdraw);
                    System.out.println("Here is the updated account information:");
                    act.display();
                }
            }

            // Save to External File
            else if(choice == 7) {
                // Write to file
                try(BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt"))){
                    for(Account act: accounts){
                        writer.write("ID: " + act.get_id() + "\n");
                        writer.write("Name: " + act.get_name() + "\n");
                        writer.write("Balance: " + Float.toString(act.get_balance()) + "\n");
                    }
                    System.out.println("All account information has been successfully saved");
                } catch (IOException ex){
                    System.out.println("There was an error in saving the information");
                    ex.printStackTrace();
                }
            }

            // Retrieve from external File
            else if(choice == 8){
                // Read each line, create new accounts, add them to the list
                try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))){

                    String line;
                    int id = 0;
                    String name = null;
                    float balance = 0.0f;

                    while((line = reader.readLine()) != null) {

                        if (line.startsWith("ID: ")) {
                            id = Integer.parseInt(line.substring("ID: ".length()));
                        } else if (line.startsWith("Name: ")) {
                            name = line.substring("Name: ".length());
                        }  else if (line.startsWith("Balance: ")) {
                            balance = Float.parseFloat(line.substring("Balance: ".length()));
                        }

                        // This is the last variable saved so this means it is time to make the object
                        if(line.startsWith("Balance: ")){
                            Account act = new Account(id, name, balance);
                            accounts.add(act);
                        }
                    }
                    System.out.println("The file has successfully been read and saved");
                } catch (IOException ex){
                    System.out.println("The File Could not be read");
                    ex.printStackTrace();
                }
            }

            // Exit
            else if(choice == 9){
                run = false;
            }

            // Invalid Input
            else if(choice > 9) {
                System.out.println("Invalid input");
            }
        }
        System.out.println("Goodbye!");
    }
}