package atom.mining.coin;

import com.google.gson.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static atom.mining.base.NetworkProcessing.get;
import static atom.mining.base.NetworkProcessing.getURL;

/**
 * @author all-creator
 * @version 1.0
 */
public class Coins {

    public static final String COIN_URL = "https://api.minerstat.com/v2/coins";

    private static final Gson gson = new GsonBuilder().setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    /**
     * @param coins list of coin names
     * @return list of coins with the given name's
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
     * @param algorithm - list of algorithms used by coins
     * @return list of coins with the given algorithm
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
