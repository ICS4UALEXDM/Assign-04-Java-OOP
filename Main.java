import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.sampled.Port;

public class Main {
    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<>();
        ArrayList<Portfolio> portfolios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // splitting the input line
                String[] inputs = line.split(", ");
                // The method name is the first thing in the column
                String methodName = inputs[0];
                // This is the arguments that will be passed to the methods
                String[] arguments = new String[inputs.length - 1];
                // Copying the contents from inputs to the arguments array. 
                // This excludes the first item which is the methodName
                System.arraycopy(inputs, 1, arguments, 0, arguments.length);
                executeStep(accounts, portfolios, methodName, arguments);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This function is a match case that Takes the input and decides what to do with the output
    private static void executeStep(ArrayList<Account> accounts, ArrayList<Portfolio> portfolios, String methodName, String[] arguments) {
        switch (methodName) {
            case "Account":
                Account newAccount = new Account(arguments[0], Integer.parseInt(arguments[1]), arguments[2]);
                accounts.add(newAccount);
                System.out.println("Account Made");
                break;
            case "Portfolio":
                for (Account account : accounts) {
                    if (account._id == Integer.parseInt(arguments[2])) {
                        Portfolio portfolio = new Portfolio(arguments[0], Integer.parseInt(arguments[1]), account);
                        portfolios.add(portfolio);
                        System.out.println("Portfolio made");
                    }
                }
                break;
            case "portfolio.depositFunds":
                float depositAmount = Float.parseFloat(arguments[1]);
                for (Portfolio portfolio : portfolios) {
                    if (portfolio._id == Integer.parseInt(arguments[0])) {
                        portfolio.depositFunds(depositAmount);
                        System.out.println("Deposited");
                    }
                }
                
                break;
            case "portfolio.withdrawFunds":
                int withdrawAmount = Integer.parseInt(arguments[1]);
                for (Portfolio portfolio : portfolios) {
                    if (portfolio._id == Integer.parseInt(arguments[0])) {
                        portfolio.withdrawFunds(withdrawAmount);
                    }
                }
                
                break;
            case "portfolio.buyStock":
                String stock = arguments[1];
                int quantity = Integer.parseInt(arguments[2]);
                for (Portfolio portfolio : portfolios) {
                    if (portfolio._id == Integer.parseInt(arguments[0])) {
                        portfolio.buyStock(stock, quantity);
                    }
                }
                
                break;
            case "portfolio.updateTotal":
                for (Portfolio portfolio : portfolios) {
                    if (portfolio._id == Integer.parseInt(arguments[0])) {
                        portfolio.updateTotal();
                    }
                }
                
                break;
            case "portfolio.displayPortfolioData":
                for (Portfolio portfolio : portfolios) {
                    if (portfolio._id == Integer.parseInt(arguments[0])) {
                        System.out.println(portfolio.displayPortfolioData());
                    }
                }
                
                break;
            default:
                System.out.println("Invalid method name: " + methodName);
                break;
        }
    }
}