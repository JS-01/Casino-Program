package Games;

import Casino.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;

public class Games {

    //imports ect
    private static Scanner input = new Scanner(System.in);
    private static Random random = new Random();
    private static ArrayList<Card> Deck = new ArrayList<>();
    private static ArrayList<Card> Used = new ArrayList<>();
    private static ArrayList<User> AccountList = new ArrayList<>();

    private static Card CurrentCardHigherLower;

    private static int ComputerPontoon;
    private static int UserPontoon;

    //main menu
    public static void MainMenu(int x) {
        System.out.println("What game would you like to play?");
        System.out.println("1 - Higher or Lower");
        System.out.println("2 - Pontoon");
        System.out.println("3 - roulette");
        System.out.println("3 - ");

        System.out.println("0 - Exit");

        int UserGuess = input.nextInt();

        switch (UserGuess) {
            case 1:
                HigherOrLower(x);
                break;
            case 2:
                Pontoon(x);
                break;
            case 3:
                Roulette(x);
                break;
            case 4:
                System.exit(0);
                break;
        }
    }

    //games
    //currently working on
    public static void HigherOrLower(int x) {
        int AccountIndex = x;
        //generate the deck
        DeckMaker();

        //Suffle the deck
        Collections.shuffle(Deck);
        AccountList = Casino.GetUserList();
        //have the user bet
        double AmountBet;
        while (true) {
            System.out.println("Please enter the amount you would like to bet: ");
            AmountBet = input.nextDouble();
            if (AmountBet > AccountList.get(x).getLimit()) {
                System.out.println("Please enter a new amount as this is greater than your limit");
            }
            if (AmountBet <= AccountList.get(x).getLimit()) {
                break;
            }

        }

        //have the computer pick a random card
        int DeckPosition = GenerateRandomDeckIndex();
        System.out.println("The current carc is: " + Deck.get(DeckPosition).PrintCard());

        while (true) {
            //have the user guess higher or lower
            System.out.println("Do you think the next card will be higher, lower or the same?");
            System.out.println("1 - Higher");
            System.out.println("2 - Lower");
            System.out.println("3 - The Same");

            //loop until 1, 2 or 3 is entered
            int Answer = 0;
            int UserInput;
            while (true) {
                try {
                    UserInput = input.nextInt();
                    if (UserInput == 1) {
                        Answer = CheckHigherOrLower(DeckPosition);
                        break;
                    }
                    if (UserInput == 2) {
                        Answer = CheckHigherOrLower(DeckPosition);
                        break;
                    }
                    if (UserInput == 3) {
                        Answer = CheckHigherOrLower(DeckPosition);
                        break;
                    } else {
                        System.out.println("Please type: ");
                        System.out.println("1 - Higher");
                        System.out.println("2 - Lower");
                        System.out.println("3 - The Same");
                    }
                } catch (Exception e) {
                    System.out.println("Error: e");
                }
            }

            if (Answer == UserInput) {
                System.out.println("Woo, you won!");
                AddMoney(AmountBet, x);
            } else {
                System.out.println("Sorry, you lost");
                RemoveMoney(AmountBet, x);
            }
            char Continue;
            while (true) {
                try {
                    System.out.println("Would you like to continue? y/n");
                    Continue = input.next().charAt(0);
                    if (Continue == 'y' || Continue == 'n') {
                        break;
                    } else {
                        System.out.println("Please enter either y/n");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }
            }

            if (Continue == 'n') {
                for (int i = 0; i < Deck.size(); i++) {
                    Deck.remove(i);
                }
                for (int i = 0; i < Used.size(); i++) {
                    Used.remove(i);
                }
                break;
            }

            Used.add(Deck.get(DeckPosition));
            Deck.remove(DeckPosition);
            if (Used.size() == 52) {
                CurrentCardHigherLower = Deck.get(0);
                HigherOrLower(AccountIndex);
            }
            System.out.println("The current card is: " + CurrentCardHigherLower);
        }

    }

    public static int CheckHigherOrLower(int a) {
        Card CurrentCard = Deck.get(a);

        //generate the next card
        int DeckPosition;
        while (true) {
            DeckPosition = GenerateRandomDeckIndex();

            if (DeckPosition != a) {
                break;
            }
        }
        Card NextCard = Deck.get(DeckPosition);
        CurrentCardHigherLower = NextCard;

        if (CurrentCard.getCardNumber() > NextCard.getCardNumber()) {
            return 1;
        }
        if (CurrentCard.getCardNumber() < NextCard.getCardNumber()) {
            return 2;
        }
        if (CurrentCard.getCardNumber() == NextCard.getCardNumber()) {
            return 3;
        }
        return 0;
    }

    //work in progress
    public static void Pontoon(int x) {
        //Generate Deck
        DeckMaker();

        //have the user bet
        double AmountBet;
        while (true) {
            System.out.println("Please enter the amount you would like to bet: ");
            AmountBet = input.nextDouble();
            if (AmountBet > AccountList.get(x).getLimit()) {
                System.out.println("Please enter a new amount as this is greater than your limit");
            }
            if (AmountBet <= AccountList.get(x).getLimit()) {
                break;
            }

        }

        //do the Computer's turns
        boolean ComputerBust = false;
        boolean ComputerFiveCardTrick = false;
        for (int i = 0; i < 5; i++) {
            int DeckPosition1 = GenerateRandomDeckIndex();
            ComputerPontoon = ComputerPontoon + (Deck.get(DeckPosition1).getCardNumber());
            Used.add(Deck.get(DeckPosition1));
            Deck.remove(DeckPosition1);
            if (CheckTotalNumber() == 1) {
                break;
            }
            if (CheckTotalNumber() == 2) {
                int StickOrNot = random.nextInt((2 - 1) + 1) + 1;
                if (StickOrNot == 1) {
                    break;
                }
            }
            if (CheckTotalNumber() == 3) {
                ComputerBust = true;
                break;
            }
            if (i == 4) {
                ComputerFiveCardTrick = true;
                break;
            }
        }
        DeckMaker();

        //do the Users's turns
        boolean UserBust = false;
        boolean UserFiveCardTrick = false;
        for (int i = 0; i < 5; i++) {
            int DeckPosition1 = GenerateRandomDeckIndex();
            UserPontoon = UserPontoon + (Deck.get(DeckPosition1).getCardNumber());

            System.out.println(Deck.get(DeckPosition1).PrintCard());

            Used.add(Deck.get(DeckPosition1));
            Deck.remove(DeckPosition1);

            if (UserPontoon > 15 && UserPontoon < 21) {
                char AnotherCard;
                while (true) {
                    try {
                        System.out.println("Would you like another card");
                        AnotherCard = input.next().charAt(0);
                        if (AnotherCard == 'y' || AnotherCard == 'n') {
                            break;
                        } else {
                            System.out.println("Please enter either y/n");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e);
                    }
                }

                if (AnotherCard == 'n') {
                    break;
                }
            }

            if (UserPontoon > 21) {
                System.out.println("Sorry you are bust");
                UserBust = true;
                break;
            }

            if (i == 4) {
                UserFiveCardTrick = true;
                break;
            }
        }
        DeckMaker();

        //say who won
        if (ComputerPontoon < UserPontoon) {
            System.out.println("Woo, you won!");
        }
        if (ComputerPontoon > UserPontoon) {
            System.out.println("Sorry, you lost");
        }
        if (UserFiveCardTrick == true && ComputerFiveCardTrick == false) {
            System.out.println("Woo, you won!");
        }
        if (ComputerFiveCardTrick == true && UserFiveCardTrick == false) {
            System.out.println("Sorry, you lost");
        }
        if (ComputerFiveCardTrick == true && UserFiveCardTrick == true) {
            if (ComputerPontoon < UserPontoon) {
                System.out.println("Woo, you won!");
            }
            if (ComputerPontoon > UserPontoon) {
                System.out.println("Sorry, you lost");
            }
        }

    }

    public static int CheckTotalNumber() {
        if (ComputerPontoon == 21) {
            return 1;
        }
        if (ComputerPontoon == 19 || ComputerPontoon == 18 || ComputerPontoon == 20) {
            return 2;
        }
        if (ComputerPontoon < 21) {
            return 3;
        }
        return -1;
    }

    public static void Roulette(int x) {

    }

    //deck
    public static void DeckMaker() {
        Hearts();
        Clubs();
        Spades();
        Diamonds();
    }

    public static void Hearts() {
        for (int i = 1; i < 14; i++) {
            Card card = new Card(i, "H");
            Deck.add(card);
        }
    }

    public static void Clubs() {
        for (int i = 1; i < 14; i++) {
            Card card = new Card(i, "C");
            Deck.add(card);
        }
    }

    public static void Spades() {
        for (int i = 1; i < 14; i++) {
            Card card = new Card(i, "S");
            Deck.add(card);
        }
    }

    public static void Diamonds() {
        for (int i = 1; i < 14; i++) {
            Card card = new Card(i, "D");
            Deck.add(card);
        }
    }

    public static void AddMoney(double x, int y) {
        double CurrentAmount = AccountList.get(y).getCash();
        AccountList.get(y).setCash((y + CurrentAmount));
    }

    public static void RemoveMoney(double x, int y) {
        double CurrentAmount = AccountList.get(y).getCash();
        AccountList.get(y).setCash((CurrentAmount - y));
    }

    public static int GenerateRandomDeckIndex() {
        int DeckSize = Deck.size();
        int DeckPosition = random.nextInt(DeckSize);
        return DeckPosition;
    }

}
