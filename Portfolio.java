import java.util.ArrayList;

class Portfolio extends AccountBasics {
    float _availableFunds;
    ArrayList<GrowthStock> _growthStocks;
    Account _account;

    public Portfolio(String name, int id, Account account) {
        super(name, id);
        this._availableFunds = 0;
        this._growthStocks = new ArrayList<>();
        this._account = account;
    }
    // Displaying polymorphism here as this function has the same functionality
    // but different process
    @Override
    public void updateTotal() {
        // Resetting the account total for the recalculations
        this._accountTotal = 0;
        // looping through the growth stocks
        for (GrowthStock stock : this._growthStocks) {
            this._accountTotal += stock._price * stock._numberOwned;
        }
        this._accountTotal += this._availableFunds;
        // updating the totals here
        this._totalYield = this._accountTotal - this._startingCash;
        this._yieldPct = this._totalYield / this._startingCash;
    }

    public float updateTotalForAcc() {
        this.updateTotal();
        return this._accountTotal;
    }

    public void depositFunds(float amount) {
        this._accountTotal += amount;
        this._availableFunds += amount;
        this._startingCash += amount;
        // Creating a transaction and adding it to the account's transaction list
        AddOrTake transaction = new AddOrTake(amount, this._name, "Deposit");
        this._account._transactions2.add(transaction);
    }

    public void withdrawFunds(float amount) {
        if (amount <= this._availableFunds) {
            // updating the available funds
            this._accountTotal -= amount;
            this._availableFunds -= amount;
            this._startingCash -= amount;
            // Creating a transaction and adding it to the account's transaction list
            AddOrTake transaction = new AddOrTake(amount, this._name, "Withdrawal");
            this._account._transactions2.add(transaction);
        } else {
            System.out.println("Not enough funds");
        }
    }

    public String displayPortfolioData() {
        return "Porfolio name: " + this._name
                + " | Total Balance: $" + this._accountTotal + " | Account Yield: $"
                + this._totalYield + " | All time change(+/-): " + this._yieldPct
                + "% | Available funds: $" + this._availableFunds;
    }

    public void buyStock(String stockId, int numberOfShares) {
        GrowthStock stock = new GrowthStock(stockId);
        this._growthStocks.add(stock);
        stock.updatePrice();
        if (numberOfShares * stock._price <= this._availableFunds) {
            float total = numberOfShares * stock._price;
            stock._numberOwned += numberOfShares;
            this._availableFunds -= total;
            Transaction transaction = new Transaction(stockId, numberOfShares, stock._price, total, this._name, "Buy");
            this._account._transactions.add(transaction);
        }
    }

    public void sellStock(String stockId, int numberOfShares) {
        for (GrowthStock stock : this._growthStocks) {
            if (stockId.equals(stock._identifier)) {
                if (stock._numberOwned >= numberOfShares) {
                    stock._numberOwned -= numberOfShares;
                    float total = numberOfShares * stock._price;
                    this._availableFunds += total;
                    Transaction transaction = new Transaction(stockId, numberOfShares, stock._price, total, this._name, "Sell");
                    this._account._transactions.add(transaction);
                } else {
                    System.out.println("You don't own enough shares!");
                }
            }
        }

    }
}