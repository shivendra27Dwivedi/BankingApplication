package javaProgam;

import java.io.*;
//import java.util.*;

public class Banking {
    private String CustomerName;
    private double amount;
    private double balance;
    private int AccountNumber = 110091109;
    private double prevBalance;
    //private Date date;

    private int retry;
    Console c = System.console();

    public void transaction(){
        try{
        FileWriter fw=new FileWriter("out.txt",true);
        BufferedWriter bw=new BufferedWriter(fw);
        //date = new Date();
        bw.write(CustomerName);
        bw.write((int) amount);
        bw.write((int) balance);
        bw.write((int) prevBalance);
        bw.newLine(); 
        bw.close();
        }
        catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }
    void CreateAccount() 
    {
        int choice = 0;
        System.out.println();
        System.out.println("*********************************************************************");
        

        choice = Integer.parseInt(c.readLine(">>>>Enter 1 to create an account: \n>>>>Enter anykey to exit:  "));
        if (choice == 1)
        {
            do
            {
               this.CustomerName = c.readLine((">>>>Enter your name: "));
                for (int i = 0; i < CustomerName.length(); i++)
                {
                    if (Character.isLetter(CustomerName.charAt(i)) || CustomerName.charAt(i) == ' ')
                        retry = 0;
                    else
                    {
                        System.out.println("***************WARNING!!***********");
                        System.out.println("Name should contain only letters ");
                        retry = 1;
                        break;
                    }
                }
            }while (retry == 1);

        }
        else
        {
            System.exit(0);
        }

        do
        {
            try
            {
                System.out.println();
                this.balance = Double.parseDouble(c.readLine(">>>>Please deposit initial balance >= 5000 \n "));
                retry = 0;
                if (balance >= 5000)
                {
                    System.out.println();
                    System.out.println();
                    System.out.println("***********ACCOUNT  CREATED  SUCCESSFULLY**********");
                    System.out.println("COSTUMER NAME:  "+ CustomerName);
                    System.out.println("ACCOUNT NUMBER: " + AccountNumber);
                    System.out.println("****THANK YOU FOR CHOOSING JAVA DEVELOPERS CO-OPERATIVE BANK**** ");
                    System.out.println();
                    System.out.println();

                }
                else if (balance < 5000) {
                    System.out.println();
                    System.out.println("***************WARNING!!***********");
                    System.out.println("Initial Balance cannot be less than 5000");
                    System.out.println();
                    retry = 1;
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("***************WARNING!!***********");
                System.out.println("   Please enter amount in digits!");
                retry = 1;
            }
        } while (retry == 1);
    }

    void deposit()
    {

        do
        {
            try
            {
                System.out.println();
                System.out.println("**************************************");
                System.out.println(">>>>Enter 0 to terminate Transaction");
                this.amount = Double.parseDouble(c.readLine(">>>>Enter Amount to Deposit:\n "));
                if(amount>0)
                {
                    balance +=amount;
                    System.out.println("Amount deposited successfully");
                    this.prevBalance = amount;
                    retry = 0;
                }
                else if(amount == 0)
                {
                    System.out.println("*****Transaction Terminated!*****");
                    retry = 0;
                }
                else if(amount < 0)
                {
                    System.out.println("amount cannot be negative");
                    retry = 1;
                }
            }
            catch(NumberFormatException e)
                {
                    System.out.println("***************WARNING!!***********");
                    System.out.println("    ENTER AMOUNT ONLY IN DIGITS!\n\n");
                    retry  = 1;
                }
        }while(retry==1);
    }

    void withdraw()
    {
        do
        {
            try
            {
                System.out.println("**************************************");
                System.out.println(">>>>Enter 0 to terminate Transaction");
                this.amount = Double.parseDouble(c.readLine(">>>>Enter Amount to Withdraw: \n"));
                retry = 0;
                if(amount <= balance && amount > 0 )
                {
                    balance -=amount;
                    System.out.println("Amount withdrawn successfully");
                    prevBalance = -amount;
                    retry = 0;
                }
                else if(amount > balance)
                {
                    System.out.println("Insufficient Funds, your account has "+balance+" Rupees");
                    retry = 1;
                }
                else if(amount == 0)
                {
                    System.out.println("*****Transaction Terminated!*****  ");
                    retry = 0;
                }
                else if(amount<0)
                {
                    System.out.println("Amount cannot be negative");
                    retry = 1;
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println("***************WARNING!!***********");
                System.out.println("    ENTER AMOUNT ONLY IN DIGITS!\n\n");
                    retry  = 1;
            }
        }while(retry==1);



        
    }

    void PreviousTransaction(double prevBalance)
    {
        if (prevBalance > 0)
        {
            System.out.println("Last balance deposted was  " + prevBalance);
        } else
        {
            System.out.println("Last balance withdrawn was  " + Math.abs(prevBalance));
        }
    }

    void showBalance(double balance)
    {
        System.out.println("Updated balance is: " + balance);
    }

    void showAccountInfo(int AccountNumber, String CustomerName, double balance)
    {
        System.out.println("COSTUMER  NAME: " + CustomerName);
        System.out.println("ACCOUNT NUMBER: " + AccountNumber);
        System.out.println("UPDATED BALANCE: " + balance);
    }

    void showMenu()
    {
        CreateAccount();
        transaction();
        int op = 0;
        do
        {
            System.out.println();
            System.out.println("**************************************");
            System.out.println("SELECTED OPERATION TO BE PERFORMED ");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Show Previous Transaction");
            System.out.println("4. Show Balance");
            System.out.println("5. Show Account Information");
            System.out.println("6. Exit");
            System.out.println("**************************************");
            System.out.println();
            do
            {
                try
                    {
                    op = Integer.parseInt(c.readLine());
                    retry = 0;
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("Please select valid option: "+e);
                        retry = 1;
                    }
            }while(retry == 1);
           
            switch (op)
            {              
                case 1:
                    System.out.println();
                    System.out.println("************************************************");
                    deposit();
                    System.out.println("************************************************");
                    break;

                case 2:
                    System.out.println();
                    System.out.println("**************************************************");
                    withdraw();
                    System.out.println("**************************************************");
                    break;

                case 3:
                    PreviousTransaction(prevBalance);
                    break;

                case 4:
                    System.out.println();
                    System.out.println("***************************************************");
                    showBalance(balance);
                    System.out.println("***************************************************");
                    break;

                case 5:
                    System.out.println();
                    System.out.println("*****************************************************");
                    showAccountInfo(AccountNumber, CustomerName, balance);
                    System.out.println("*****************************************************");
                    break;

                case 6:
                    System.out.println();
                    System.out.println("*****************************************************************");
                    System.out.println("*****************THANK YOU FOR USING OUR SERVICES***************");
                    System.out.println("*****************************************************************");
                    break;

                default:
                    System.out.println("Please select valid option: ");
              }
        } while (op != 6);
    }
}