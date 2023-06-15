class Transaction {
    // initializing fields
    String _stockId;
    int _amountOfShares;
    float _transactionTotal;
    float _sharePrice;
    String _fromPortfolio;

    public Transaction(String stockId, int amountOfShares, float transactionTotal, float sharePrice,
            String fromPortfolio) {
        // giving fields their values
        this._stockId = stockId;
        this._amountOfShares = amountOfShares;
        this._transactionTotal = transactionTotal;
        this._sharePrice = sharePrice;
        this._fromPortfolio = fromPortfolio;
    }
}