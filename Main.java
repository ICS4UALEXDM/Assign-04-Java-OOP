import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
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
            FileWriter writer = new FileWriter("output.txt");
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
                executeStep(writer, accounts, portfolios, methodName, arguments);

            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This function is a match case that Takes the input and decides what to do with the output
    private static void executeStep(FileWriter writer, ArrayList<Account> accounts, ArrayList<Portfolio> portfolios, String methodName, String[] arguments) {
        try {
            switch (methodName) {
                case "Account":
                    Account newAccount = new Account(arguments[0], Integer.parseInt(arguments[1]), arguments[2]);
                    accounts.add(newAccount);
                    writer.write("Account Made\n\n");
                    break;
                case "Portfolio":
                    for (Account account : accounts) {
                        if (account._id == Integer.parseInt(arguments[2])) {
                            Portfolio portfolio = new Portfolio(arguments[0], Integer.parseInt(arguments[1]), account);
                            portfolios.add(portfolio);
                            writer.write("Portfolio made\n\n");
                        }
                    }
                    break;
                case "portfolio.depositFunds":
                    float depositAmount = Float.parseFloat(arguments[1]);
                    for (Portfolio portfolio : portfolios) {
                        if (portfolio._id == Integer.parseInt(arguments[0])) {
                            portfolio.depositFunds(depositAmount);
                            writer.write("Deposited\n\n");
                        }
                    }

                    break;
                case "portfolio.withdrawFunds":
                    int withdrawAmount = Integer.parseInt(arguments[1]);
                    for (Portfolio portfolio : portfolios) {
                        if (portfolio._id == Integer.parseInt(arguments[0])) {
                            portfolio.withdrawFunds(withdrawAmount);
                            writer.write("Withdrawn\n\n");
                        }
                    }

                    break;
                case "portfolio.buyStock":
                    String stock = arguments[1];
                    int quantity = Integer.parseInt(arguments[2]);
                    for (Portfolio portfolio : portfolios) {
                        if (portfolio._id == Integer.parseInt(arguments[0])) {
                            portfolio.buyStock(stock, quantity);
                            writer.write("Stock Bought\n\n");
                        }
                    }

                    break;
                case "portfolio.sellStock":
                    stock = arguments[1];
                    quantity = Integer.parseInt(arguments[2]);
                    for (Portfolio portfolio : portfolios) {
                        if (portfolio._id == Integer.parseInt(arguments[0])) {
                            portfolio.sellStock(stock, quantity);
                            writer.write("Stock sold\n\n");
                        }
                    }

                    break;
                case "portfolio.updateTotal":
                    for (Portfolio portfolio : portfolios) {
                        if (portfolio._id == Integer.parseInt(arguments[0])) {
                            portfolio.updateTotal();
                            writer.write("Updated\n\n");
                        }
                    }

                    break;
                case "portfolio.displayPortfolioData":
                    for (Portfolio portfolio : portfolios) {
                        if (portfolio._id == Integer.parseInt(arguments[0])) {
                            writer.write(portfolio.displayPortfolioData());
                        }
                    }

                    break;
                case "account.displayTransactions":
                    for (Account account : accounts) {
                        if (account._id == Integer.parseInt(arguments[0])) {
                            writer.write(account.displayTransactions());
                        }
                    }
                    break;
                case "portfolio.showStocks":
                    for (Portfolio portfolio : portfolios) {
                        if (portfolio._id == Integer.parseInt(arguments[0])) {
                            writer.write(portfolio.showStocks());
                        }
                    }
                    break;
                case "account.displayAccountData":
                    for (Account account : accounts) {
                        if (account._id == Integer.parseInt(arguments[0])) {
                            writer.write(account.displayAccountData());
                        }
                    }
                    break;
                default:
                    writer.write("Invalid method name: " + methodName);
                    break;
            }
        } catch (IOException e) {
            System.out.println(e);
        } 
    }
}