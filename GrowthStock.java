import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GrowthStock {
    String _identifier;
    float _price;
    int _numberOwned;
    float _dayChange;
    float _low;
    float _high;
    float _pctChange;
    float _open;

    public GrowthStock(String identifier) {
        this._identifier = identifier;
        this._numberOwned = 0;
    }

    public void updatePrice() {
        try {
            // Create a url link that will be used to search up the data from the API
            String symbol = this._identifier;
            String apiKey = "ci50bf9r01qp1s6rgh40ci50bf9r01qp1s6rgh4g";
            URL url = new URL("https://finnhub.io/api/v1/quote?symbol=" + symbol + "&token=" + apiKey);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method (GET, POST, etc.)
            connection.setRequestMethod("GET");

            // Get the response code to see if the url call was successful
            int responseCode = connection.getResponseCode();

            // Read the response from the input
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            String data = response.toString();
            String[] datapoints = data.split(",");
            int startingPointPrice = datapoints[0].indexOf(":") + 1;
            this._price = Float.parseFloat(datapoints[0].substring(startingPointPrice));
            int startingPointOpen = datapoints[5].indexOf(":") + 1;
            this._open = Float.parseFloat(datapoints[5].substring(startingPointOpen));
            int startingPointPct = datapoints[2].indexOf(":") + 1;
            this._pctChange = Float.parseFloat(datapoints[2].substring(startingPointPct));
            int startingPointDayChange = datapoints[1].indexOf(":") + 1;
            this._dayChange = Float.parseFloat(datapoints[1].substring(startingPointDayChange));
            int startingPointLow = datapoints[4].indexOf(":") + 1;
            this._low = Float.parseFloat(datapoints[4].substring(startingPointLow));
            int startingPointHigh = datapoints[3].indexOf(":") + 1;
            this._high = Float.parseFloat(datapoints[3].substring(startingPointHigh));
            System.out.println(this._price);
            System.out.println(this._dayChange);
            System.out.println(this._low);
            System.out.println(this._high);
            System.out.println(this._pctChange);
            System.out.println(this._open);

            // Print the response
            System.out.println(data);

            // Disconnect the connection
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
