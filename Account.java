import java.util.ArrayList;


class Account extends AccountBasics {
    String _owner;
    ArrayList<Transaction> _transactions;
    ArrayList<AddOrTake> _transactions2;

    public Account(String name, int id, String owner) {
        super(name, id);
        this._owner = owner;
        this._transactions = new ArrayList<>();
        this._transactions2 = new ArrayList<>();
    }
    // Displaying polymorphism as it has different process but more or less
    // the same functionality
    @Override
    public void updateTotal() {
        this._accountTotal = 0;
        for (Portfolio portfolio : this._portfolios) {
            this._accountTotal += portfolio.updateTotalForAcc();
        }
        this._totalYield = this._accountTotal - this._startingCash;
        this._yieldPct = this._totalYield / this._startingCash;
    }

    public String displayAccountData() {
        return "Account name: " + this._name + " | Owner: " + this._owner
                + " | Total Balance: $" + this._accountTotal + " | Account Yield: $"
                + this._totalYield + " | All time change(+/-): " + this._yieldPct + "%\n\n";
    }
    
    public String displayTransactions() {
        String output = "";
        // Creating the Output String in the next two For loops
        for (AddOrTake transaction2 : this._transactions2) {
            output += "Type: " + transaction2._type + " | Total: $"
                    + transaction2._amount + " | Portfolio: " + transaction2._portfolioName + "\n\n";
        }
        for (Transaction transaction : this._transactions) {
            output += "Stock: " + transaction._stockId + "Type: " + transaction._type 
            + " | Number of Shares: " + transaction._amountOfShares + " | Total: $ "
            + transaction._amount + " | Portfolio: " + transaction._portfolioName + "\n\n";
        }

        return output;
    }
}
