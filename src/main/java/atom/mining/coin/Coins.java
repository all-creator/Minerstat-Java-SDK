package atom.mining.coin;

import com.google.gson.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static atom.mining.base.NetworkProcessing.get;
import static atom.mining.base.NetworkProcessing.getURL;

/**
 * This is the basic method for working with data on Minerstat coins. The methods implemented in it operate and return lists, including empty ones.
 * @author all-creator
 * @version 1.0
 */
public class Coins {

    /**
     * URL Address of Coin Minerstat API
     */
    public static final String COIN_URL = "https://api.minerstat.com/v2/coins";

    private static final Gson gson = new GsonBuilder().setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    /**
     * This method gets a list of {@code coins} by the names passed, if no such coins are found, an empty list will be returned
     * @param coins list of coin names
     * @return list of coins with the given name's
     * @see Coin
     */
    public static List<Coin> of(String... coins) {
        var result = new ArrayList<Coin>();
        try {
            final var jsonArray = JsonParser.parseString(get(getURL(COIN_URL, "list", coins))).getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                result.add(gson.fromJson(jsonElement, Coin.class));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    /**
     * This method gets a list of coins running on the transmitted {@code algorithms}, if no such coins are found, an empty list will be returned
     * @param algorithm list of algorithms used by coins
     * @return list of coins with the given algorithm
     * @see Coin
     */
    public static List<Coin> ofAlgo(String... algorithm) {
        var result = new ArrayList<Coin>();
        try {
            final var jsonArray = JsonParser.parseString(get(getURL(COIN_URL, "algo", algorithm))).getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                result.add(gson.fromJson(jsonElement, Coin.class));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
