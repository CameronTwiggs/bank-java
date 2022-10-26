package com.bofa;

import java.util.*;
import java.io.*;

class Main implements java.io.Serializable
{
    Map<String,Customer> customerMap;
    Main()
    {
        customerMap = new HashMap <String,Customer>();
    }
//    Makes a empty hashmap to collect a list of all of the account holders, the key is going to be a string and the value
//    be a customer object

    public static void main(String []args)
    {
        Scanner sc = new Scanner(System.in);
        Customer customer;
        String username,password;double amount;
        Main bank = new Main();
//      Main refers to the "Main" method which creates a new hashmap that will represent the bank/database
        int choice;
        outer: while(true)
//      The "Starting point" whenever the code refers to this "outer" keyword, they are referring to this case statement.
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
//           first case switch
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
//                    uses methods that comes from the custermap object, this checks if that username is in the hashMap
                    {
                        System.out.println("Username already exists. Set again : ");
                        username = sc.next();
                    }
                    System.out.println("Set a password (minimum 8 chars; minimum 1 digit, 1 lowercase, 1 uppercase, 1 special character[!@#$%^&*_]) :");
                    password = sc.next();
                    sc.nextLine();
                    while(!password.matches((("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}"))))
//                    regex for password validation.
                    {
                        System.out.println("Invalid password condition. Set again :");
                        password=sc.next();
                    }
                    System.out.print("Enter initial deposit : ");
                    amount=sc.nextDouble();
                    customer = new Customer(username,password,name,address,phone,amount,new Date());
//                  "customer" variable is instantiated here.
                    bank.customerMap.put(username,customer);
//                  this than puts that new "customer" value to the hashmap.
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
//                               second case switch which is nested.
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
//                                        customer has a method that uses the arguments passed by the user to increase the balance (.deposit())
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
                                        if(amount > 300_000)
                                        {
                                            System.out.println("Transfer limit exceeded. Contact bank manager.");
                                            break;
                                        }
                                        if(bank.customerMap.containsKey(username))
                                        {
                                            Customer payee = bank.customerMap.get(username);
//                                          Once we can confirm that the targeted user exists, we assign them to payee.
                                            payee.deposit(amount,new Date());
                                            customer.withdraw(amount,new Date());
//                                          Reminder: customer is already assigned to the one who originally signed in.

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
//                                      All this does is loop through the "transaction" array found in the orignal "customer" object.
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

//                           1. means that they are going to loop through the hashmap
//                           2. means nothing happens.
                            System.out.print("\nEnter your choice : ");
                            choice = sc.nextInt();
                            sc.nextLine();
                            switch(choice)
                            {
                                case 1:
                                    System.out.println("Customers information");
                                    System.out.println("username name address phone balance");
                                    for (Customer x : bank.customerMap.values()) {
                                        System.out.println(x.username + " " + x.name + " " + x.address + " "+ x.phone + " " + x.balance);
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
// Saving of object in a file
                        FileOutputStream file = new FileOutputStream(filename);
                        ObjectOutputStream out = new ObjectOutputStream(file);
                        out.writeObject(bank);
                        out.close();
                        file.close();
                        System.out.println("Object has been serialized");
//                        Standard steps for saving data to a physical file on the machine.

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


