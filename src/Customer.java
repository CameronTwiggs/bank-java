import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

class Customer implements SavingsAccount
{
    String username,password,name,address,phone;
    double balance;
    ArrayList<String> transactions;
    Customer(String username, String password, String name, String address, String phone, double balance, Date date)
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.balance = balance;
        transactions = new ArrayList<String>(5);
        addTransaction(String.format("Initial deposit - " + NumberFormat.getCurrencyInstance().format(balance)+" as on " + "%1$tD"+" at "+"%1$tT.",date));
    }
    @Override
    public void deposit(double amount,Date date)
    {
        balance += amount;
        addTransaction(String.format(NumberFormat.getCurrencyInstance().format(amount)+" credited to your account. Balance - " +NumberFormat.getCurrencyInstance().format(balance)+" as on " + "%1$tD"+" at "+"%1$tT.",date));
    }
    @Override
    public void withdraw(double amount,Date date)
    {
        if(amount>(balance-200))
        {
            System.out.println("Insufficient balance.");
            return;
        }
        balance -= amount;
        addTransaction(String.format(NumberFormat.getCurrencyInstance().format(amount)+" debited from your account. Balance - " +NumberFormat.getCurrencyInstance().format(balance)+" as on " + "%1$tD"+" at "+"%1$tT.",date));
    }
    private void addTransaction(String message)
    {
        transactions.add(0,message);
        if(transactions.size()>5)
        {
            transactions.remove(5);
            transactions.trimToSize();
        }
    }
}