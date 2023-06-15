import java.util.ArrayList;

class Portfolio extends AccountBasics {
    float _availableFunds;
    ArrayList<GrowthStock> _growthStocks;
    ArrayList<GrowthIncomeStock> _growthIncomeStocks;
    public Portfolio(String name, int id) {
        super(name, id);
        this._availableFunds = 0;
        this._growthIncomeStocks = new ArrayList<>();
        this._growthStocks = new ArrayList<>();
    }

    public float updateTotalForAcc() {
        this._accountTotal = 0;
        for (GrowthStock growthStock : this._growthStocks) {
            this._accountTotal += growthStock._price;
        } 
        for (GrowthIncomeStock growthIncomeStock : this._growthIncomeStocks) {
            this._accountTotal += growthIncomeStock._price;
        }
    }
}