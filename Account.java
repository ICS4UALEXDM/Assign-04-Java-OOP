import java.util.ArrayList;


class Account extends AccountBasics {
    String _owner;

    public Account(String name, int id, String owner) {
        super(name, id);
        this._owner = owner;
    }

    public void updateTotal() {
        this._accountTotal = 0;
        for (Portfolio portfolio : this._portfolios) {
            this._accountTotal += portfolio.updateTotalForAcc();
        }
        this._totalYield = this._accountTotal - this._startingCash;
        this._yieldPct = this._totalYield / this._startingCash;
    }
}
