package Casino;

import Games.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Casino {

    private static Scanner input = new Scanner(System.in);
    private static Random random = new Random();
    public static ArrayList<User> AccountList = new ArrayList<>();

    public static void main(String[] args) {
        MainMenu();
    }

    public static void MainMenu() {
        while (true) {
            System.out.println("Hello and welcome to my casino, what would you like to do?");
            System.out.println("1 - Game menu");
            System.out.println("2 - Account menu");

            System.out.println("0 - Exit");

            int UserInput = input.nextInt();

            switch (UserInput) {
                case 1:
                    String AccountName = GetAccountName();
                    int AccountNumber = GetAccountNumber();
                    int AccountIndex = GetAccountIndex(AccountName, AccountNumber);
                    Games.MainMenu(AccountIndex);
                    break;
                case 2:
                    AccountMenu();
                    break;
                case 0:
                    System.out.println("Bye ^o^");
                    System.exit(0);
                    break;
            }
        }
    }

    //accounts
    public static void AccountMenu() {
        String Name = GetAccountName();
        int AccountNumber = -1;
        while (true) {
            System.out.println("Do you have an account number? (y/n)");
            char UserInput = input.next().charAt(0);

            if (UserInput == 'y') {
                AccountNumber = GetAccountNumber();
                break;
            } else {
                break;
            }
        }

        System.out.println("Welcome to the Account Menu, what would you like to do?");

        while (true) {
            System.out.println("1 - Create new account");
            System.out.println("2 - Add funds");
            System.out.println("3 - Withdraw funds");
            System.out.println("4 - Check current ballance");

            System.out.println("0 - Exit");

            int UserInput = input.nextInt();

            switch (UserInput) {
                case 1:
                    CreateAccount(Name);
                    break;
                case 2:
                    AddFunds(Name, AccountNumber);
                    break;
                case 3:
                    WithDrawFunds(Name, AccountNumber);
                    break;
                case 4:
                    CheckCurrentBallence(Name, AccountNumber);
                    break;
                case 0:
                    MainMenu();
                    break;
            }
        }
    }

    public static String GetAccountName() {
        System.out.println("Please enter your name");
        input.nextLine();
        return input.nextLine();
    }

    public static int GetAccountNumber() {
        System.out.println("Please enter your account number");
        return input.nextInt();
    }

    public static void CreateAccount(String x) {
        //double Cash, String Name, int AccountNumber, double Limit, double Debt
        double Cash = 100.00;

        int AccountNumber = GenerateAccountNumber();

        double Debt = 0.00;

        String Name = x;

        System.out.println("Please enter your spending limit");
        double Limit = input.nextDouble();

        User NewUser = new User(Cash, Name, AccountNumber, Limit, Debt);
        AccountList.add(NewUser);

        System.out.println("Thank you, your account is now created your account number is: " + AccountNumber);
    }

    public static int GenerateAccountNumber() {
        int PossibleAccountNumber = -1;
        if (AccountList.isEmpty()) {
            PossibleAccountNumber = random.nextInt(100);
        } else {
            PossibleAccountNumber = random.nextInt(100 + (AccountList.size()));

            for (int i = 0; i < AccountList.size(); i++) {
                if (AccountList.get(i).getAccountNumber() == PossibleAccountNumber) {
                    PossibleAccountNumber = random.nextInt(100 + (AccountList.size()));
                } else {
                    return PossibleAccountNumber;
                }
            }
        }
        return PossibleAccountNumber;
    }

    public static void AddFunds(String x, int y) {
        int AccountIndex = GetAccountIndex(x, y);

        System.out.println("How much would you like to add?");
        double AdditionalFunds = input.nextDouble();

        AccountList.get(AccountIndex).setCash(AdditionalFunds);
    }

    public static int GetAccountIndex(String x, int y) {
        //find out what account they want

        for (int i = 0; i < AccountList.size(); i++) {
            if (AccountList.get(i).getName().equals(x) && AccountList.get(i).getAccountNumber() == y) {
                return i;
            }
        }
        return -1;
    }

    public static void WithDrawFunds(String x, int y) {
        int AccountIndex = GetAccountIndex(x, y);
        double CurrentBallance = AccountList.get(AccountIndex).GiveBallance();

        while (true) {
            System.out.println("How much would you like to withdraw?");
            double WithdrawAmount = input.nextDouble();

            if (WithdrawAmount <= CurrentBallance) {
                AccountList.get(AccountIndex).setCash((CurrentBallance - WithdrawAmount));
                break;
            } else {
                System.out.println("Sorry but you dont have enough money to withdraw that amount");
            }
        }
    }

    public static void CheckCurrentBallence(String x, int y) {
        int AccountIndex = GetAccountIndex(x, y);

        System.out.println("Your current ballance is: " + (AccountList.get(AccountIndex).GiveBallance()));
    }
    
    public static ArrayList GetUserList() {
        return AccountList;
    }

}
