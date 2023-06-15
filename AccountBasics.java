import java.util.ArrayList;

public class AccountBasics {
    String _name;
    float _accountTotal;
    float _totalYield;
    int _id;
    float _yieldPct;
    float _startingCash;
    ArrayList<Portfolio> _portfolios;

    public AccountBasics(String name, int id) {
        this._startingCash = 0;
        this._accountTotal = 0;
        this._totalYield = 0;
        this._yieldPct = 0;
        this._id = id;
        this._name = name;
        this._portfolios = new ArrayList<>();
    }

    public void updateTotal() {
        this._totalYield = this._accountTotal - this._startingCash;
        this._yieldPct = this._totalYield / this._startingCash;
    }

}
