import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        Account account = new Account("Savings", 0, "Alex De Meo");
        Portfolio portfolio = new Portfolio("Tester", 0, account);
        portfolio.depositFunds(40000);
        portfolio.withdrawFunds(6000);
        portfolio.buyStock("AAPL", 3);
        portfolio.updateTotal();
        System.out.println(portfolio.displayPortfolioData());
    }
}