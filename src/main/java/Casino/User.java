
package Casino;

public class User {
    private double Cash;
    private String Name;
    private int AccountNumber;
    private double Limit;
    private double Debt;
    
    public double GiveBallance() {
        return Cash;
    }
    
    //constructor
    public User(double Cash, String Name, int AccountNumber, double Limit, double Debt) {
        this.Cash = Cash;
        this.Name = Name;
        this.AccountNumber = AccountNumber;
        this.Limit = Limit;
        this.Debt = Debt;
    }
    
    //getter
    public double getCash() {
        return Cash;
    }

    public String getName() {
        return Name;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public double getLimit() {
        return Limit;
    }

    public double getDebt() {
        return Debt;
    }
    
    //setter
    public void setCash(double Cash) {
        this.Cash = Cash;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setAccountNumber(int AccountNumber) {
        this.AccountNumber = AccountNumber;
    }

    public void setLimit(double Limit) {
        this.Limit = Limit;
    }

    public void setDebt(double Debt) {
        this.Debt = Debt;
    }
    
    
}
