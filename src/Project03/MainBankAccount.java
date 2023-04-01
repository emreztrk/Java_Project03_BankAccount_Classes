package Project03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainBankAccount {
    public static void main(String[] args) {

        Scanner scan=new Scanner(System.in);

        Actions deposit=new Actions("Deposit");
        Actions withDraw=new Actions("Withdraw");
        Actions transfer=new Actions("Transfer");
        Actions exit=new Actions("Exit");
        List<Actions> actionsList=new ArrayList<>(Arrays.asList(deposit,withDraw,transfer,exit));

        Account cus1First=new Account(1234, 100);
        Account cus1Second=new Account(4321, 200);

        Account cus2First=new Account(5678, 1000);
        Account cus2Second=new Account(8765, 2000);

        Account cus3First=new Account(9999, 500);
        Account cus3Second=new Account(1111, 400);

        Customer cus1=new Customer("user1", "password1", Arrays.asList(cus1First,cus1Second));
        Customer cus2=new Customer("user2", "password2", Arrays.asList(cus2First,cus2Second));
        Customer cus3=new Customer("user3", "password3", Arrays.asList(cus3First,cus3Second));
        List<Customer> user=new ArrayList<>(Arrays.asList(cus1,cus2,cus3));

        Customer activeUser;
        Account chosenAccount;

        while (true){
            System.out.print("Please enter your username: ");
            String username = scan.nextLine();
            System.out.print("Please enter your password: ");
            String password = scan.nextLine();

            activeUser=confirmUsernameandPassword(user, username, password);

            if (activeUser !=null){
                System.out.println("You have Successfully logged in");
                break;
            }
            System.out.println("No such user was found in the system, please try again");
        }

        while (true){
            System.out.println("Select the action you want to do");
            for (int i = 0; i < actionsList.size(); i++) {
                System.out.println(actionsList.get(i).description + " - " + (i+1));
            }
            int userChoice=scan.nextInt();

            switch (userChoice){
                case 1:{

                    while (true) {
                        System.out.println("Enter the account number you want to deposit");

                        for (Account a : activeUser.accountList)
                            System.out.println("Account No= " + a.number);

                        String chosen = scan.next();
                        chosenAccount = confirmAccountNumber(activeUser, chosen);
                        if (chosenAccount == null) {
                            System.out.println("Wrong account number!");
                            continue;
                        }
                        break;
                    }
                        System.out.println("Enter deposit amount= ");
                        int deposited= scan.nextInt();

                        chosenAccount.moneyAmount+=deposited;
                        break;
                }

                case 2:{

                    while (true){
                        System.out.println("Which account do you want to withdraw money from?");
                        for (Account a : activeUser.accountList){
                            System.out.println(a.number);
                        }
                        String choice= scan.next();
                        chosenAccount=confirmAccountNumber(activeUser,choice);
                        if (chosenAccount==null){
                            System.out.println("You Have Entered Incorrectly");
                            continue;
                        }
                        System.out.println("How much do you want to withdraw?");
                        int amountToWithdraw=scan.nextInt();
                        if (!withdrawMethod(chosenAccount, amountToWithdraw)){
                            break;
                        }
                    }
                    break;
                }
                case 3: {
                    System.out.println("We are not able to perform this operation at the moment...");
                }
                break;
                case 4:
                    System.exit(1);

                default: {
                    System.out.println("You didn't make a successful choice...");
                }
            }
        }
        }


    private static boolean withdrawMethod(Account chosenAccount, int amountToWithdraw) {

        if (chosenAccount.moneyAmount<amountToWithdraw){
            System.out.println("Insufficient Balance");
            return true;
        }
        chosenAccount.moneyAmount-=amountToWithdraw;
        System.out.println("Take your money. Remaining Amount= " + chosenAccount.moneyAmount );
        return false;
    }

    private static Account confirmAccountNumber(Customer activeUser, String chosen) {
        Integer choice=Integer.parseInt(chosen);
        for (Account a : activeUser.accountList){
            if (a.number==choice)

                return a;
        }
        return null;
    }

    public static Customer confirmUsernameandPassword(List<Customer> user, String username, String password) {
        for (int i = 0; i < user.size(); i++) {
            if (user.get(i).username.equals(username) && user.get(i).password.equals(password))

                return user.get(i);
        }
        return null;
    }
}
