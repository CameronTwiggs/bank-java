import java.util.*;
import java.io.*;

class Main implements java.io.Serializable
{
    Map<String,Customer> customerMap;
    Main()
    {
        customerMap = new HashMap <String,Customer>();
    }
    public static void main(String []args)
    {
        Scanner sc = new Scanner(System.in);
        Customer customer;
        String username,password;double amount;
        Main bank = new Main();
        int choice;
        outer: while(true)
        {
            System.out.println("\n-------------------");
            System.out.println("BANK OF JAVA");
            System.out.println("-------------------\n");
            System.out.println("1. Register account.");
            System.out.println("2. Login.");
            System.out.println("3. Update accounts.");
            System.out.println("4. Login as Bank employee.");
            System.out.println("5. Exit.");
            System.out.print("\nEnter your choice : ");
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice)
            {
                case 1:
                    System.out.print("Enter name : ");
                    String name = sc.nextLine();
                    System.out.print("Enter address : ");
                    String address = sc.nextLine();
                    System.out.print("Enter contact number : ");
                    String phone = sc.nextLine();
                    System.out.println("Set username : ");
                    username = sc.next();
                    while(bank.customerMap.containsKey(username))
                    {
                        System.out.println("Username already exists. Set again : ");
                        username = sc.next();
                    }
                    System.out.println("Set a password (minimum 8 chars; minimum 1 digit, 1 lowercase, 1 uppercase, 1 special character[!@#$%^&*_]) :");
                    password = sc.next();
                    sc.nextLine();
                    while(!password.matches((("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}"))))
                    {
                        System.out.println("Invalid password condition. Set again :");
                        password=sc.next();
                    }
                    System.out.print("Enter initial deposit : ");
                    amount=sc.nextDouble();
                    customer = new Customer(username,password,name,address,phone,amount,new Date());
                    bank.customerMap.put(username,customer);
                    break;
                case 2:
                    System.out.println("Enter username : ");
                    username = sc.next();
                    sc.nextLine();
                    System.out.println("Enter password : ");
                    password = sc.next();
                    sc.nextLine();
                    if(bank.customerMap.containsKey(username))
                    {
                        customer = bank.customerMap.get(username);
                        if(customer.password.equals(password))
                        {
                            while(true)
                            {
                                System.out.println("\n-------------------");
                                System.out.println("W E L C O M E");
                                System.out.println("-------------------\n");
                                System.out.println("1. Deposit.");
                                System.out.println("2. Transfer.");
                                System.out.println("3. Last 5 transactions.");
                                System.out.println("4. User information.");
                                System.out.println("5. Log out.");
                                System.out.print("\nEnter your choice : ");
                                choice = sc.nextInt();
                                sc.nextLine();
                                switch(choice)
                                {
                                    case 1:
                                        System.out.print("Enter amount : ");
                                        while(!sc.hasNextDouble())
                                        {
                                            System.out.println("Invalid amount. Enter again :");
                                            sc.nextLine();
                                        }
                                        amount = sc.nextDouble();
                                        sc.nextLine();
                                        customer.deposit(amount,new Date());
                                        break;
                                    case 2:
                                        System.out.print("Enter payee username : ");
                                        username = sc.next();
                                        sc.nextLine();
                                        System.out.println("Enter amount : ");
                                        while(!sc.hasNextDouble())
                                        {
                                            System.out.println("Invalid amount. Enter again :");
                                            sc.nextLine();
                                        }
                                        amount = sc.nextDouble();
                                        sc.nextLine();
                                        if(amount > 300000)
                                        {
                                            System.out.println("Transfer limit exceeded. Contact bank manager.");
                                            break;
                                        }
                                        if(bank.customerMap.containsKey(username))
                                        {
                                            Customer payee = bank.customerMap.get(username);
                                            payee.deposit(amount,new Date());
                                            customer.withdraw(amount,new Date());
                                        }
                                        else
                                        {
                                            System.out.println("Username doesn't exist.");
                                        }
                                        break;
                                    case 3:
                                        for(String transactions : customer.transactions)
                                        {
                                            System.out.println(transactions);
                                        }
                                        break;
                                    case 4:
                                        System.out.println("Accountholder name : "+customer.name);
                                        System.out.println("Accountholder address : "+customer.address);
                                        System.out.println("Accountholder contact : "+customer.phone);
                                        break;
                                    case 5:
                                        continue outer;
                                    default:
                                        System.out.println("Wrong choice !");
                                }
                            }
                        }
                        else
                        {
                            System.out.println("Wrong username/password.");
                        }
                    }
                    else
                    {
                        System.out.println("Wrong username/password.");
                    }
                    break;
                case 4 :
                    System.out.println("Enter username : ");
                    username = sc.next();
                    sc.nextLine();
                    System.out.println("Enter password : ");
                    password = sc.next();
                    sc.nextLine();
                    if(username.equals("emp") && password.equals("1234")) // sample Employee Login
                    {
                        while(true)
                        {
                            System.out.println("\n-------------------");
                            System.out.println("W E L C O M E");
                            System.out.println("-------------------\n");
                            System.out.println("1. see account information of all the customers.");
                            System.out.println("2. Log out.");
                            System.out.print("\nEnter your choice : ");
                            choice = sc.nextInt();
                            sc.nextLine();
                            switch(choice)
                            {
                                case 1:
                                    System.out.println("Customers information");
                                    System.out.println("username name address phone balance");
                                    for (Customer v : bank.customerMap.values()) {
                                        System.out.println(v.username + " " + v.name + " " + v.address + " "+ v.phone + " " + v.balance);
                                    }
                                    break;
                                case 2: continue outer;
                            }
                        }
                    }
                    break;
                case 5:
                    String filename = "information.txt";
// Serialization of all the Bank details.
                    try
                    {
//Saving of object in a file
                        FileOutputStream file = new FileOutputStream(filename);
                        ObjectOutputStream out = new ObjectOutputStream(file);
                        out.writeObject(bank);
                        out.close();
                        file.close();
                        System.out.println("Object has been serialized");
                    }
                    catch (IOException ex) {
                    }
                    System.out.println("\nThank you for choosing Bank Of Java.");
                    System.exit(1);
                    break;
                default:
                    System.out.println("Wrong choice !");
            }
        }
    }
}


