package atom.mining.coin;

/**
 * Base Class for Coin Statistics
 * @author all-creator
 * @version 1.0
 * @see Coins
 */
public class Coin {

    private final String id;
    private final String coin;
    private final String name;
    private final String type;
    private final String algorithm;
    private final long networkHashrate;
    private final float difficulty;
    private final double reward;
    private final String rewardUnit;
    private final float rewardBlock;
    private final double price;
    private final double volume;
    private final long updated;


    /**
     * Generates a Coin object using {@link Coins#of(String... name)}, if the coin is not found returns null
     * @param name of coin
     * @return Coin object with all data from minerstat
     * @see Coins
     */
    public Coin get(String name) {
        final var coin = Coins.of(name);
        return coin.size() > 0 ? coin.get(0) : null;
    }

    /**
     * @param id Unique identifier of the coin.
     * @param coin Coin's ticker.
     * @param name Coin's name.
     * @param type Coin's type. It can be coin or pool, where pool is multi pool, such as NiceHash, Zpool, etc.
     * @param algorithm Coin's algorithm.
     * @param networkHashrate Coin's network hashrate in H/s. If coin has no data on network hashrate, the network hashrate is -1.
     * @param difficulty Coin's difficulty. If coin has no data on difficulty, the difficulty is -1.
     * @param reward Coin's reward for 1 H/s for 1 hour of mining based on the most current difficulty. If coin has no data on reward, the reward is -1.
     * @param rewardUnit Coin's reward unit. If a coin is multi pool, the reward unit can be BTC or XMR or whichever reward is provided by the multi pool.
     * @param rewardBlock Coin's block reward. If coin has no data on the block's reward, the block's reward is -1.
     * @param price Coin's price in USD. If coin has no data on price, the price is -1.
     * @param volume Coin's last 24h volume in USD. If coin has no data on volume, the volume is -1.
     * @param updated The UNIX timestamp of the last time the coin was updated (on Minerstat).
     */
    Coin(String id, String coin, String name, String type, String algorithm, long networkHashrate, float difficulty,
         double reward, String rewardUnit, float rewardBlock, double price, double volume, long updated) {
        this.id = id;
        this.coin = coin;
        this.name = name;
        this.type = type;
        this.algorithm = algorithm;
        this.networkHashrate = networkHashrate;
        this.difficulty = difficulty;
        this.reward = reward;
        this.rewardUnit = rewardUnit;
        this.rewardBlock = rewardBlock;
        this.price = price;
        this.volume = volume;
        this.updated = updated;
    }

    /**
     * @return Unique identifier of the coin.
     */
    public String getId() {
        return id;
    }

    /**
     * @return Coin's ticker.
     */
    public String getCoin() {
        return coin;
    }

    /**
     * @return Coin's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Coin's type. It can be coin or pool, where pool is multi pool, such as NiceHash, Zpool, etc.
     */
    public String getType() {
        return type;
    }

    /**
     * @return Coin's algorithm.
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * @return Coin's network hashrate in H/s. If coin has no data on network hashrate, the network hashrate is -1.
     */
    public long getNetworkHashrate() {
        return networkHashrate;
    }

    /**
     * @return Coin's difficulty. If coin has no data on difficulty, the difficulty is -1.
     */
    public float getDifficulty() {
        return difficulty;
    }

    /**
     * @return Coin's reward for 1 H/s for 1 hour of mining based on the most current difficulty. If coin has no data on reward, the reward is -1.
     */
    public double getReward() {
        return reward;
    }

    /**
     * @return Coin's reward unit. If a coin is multi pool, the reward unit can be BTC or XMR or whichever reward is provided by the multi pool.
     */
    public String getRewardUnit() {
        return rewardUnit;
    }

    /**
     * @return Coin's block reward. If coin has no data on the block's reward, the block's reward is -1.
     */
    public float getRewardBlock() {
        return rewardBlock;
    }

    /**
     * @return Coin's price in USD. If coin has no data on price, the price is -1.
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return Coin's last 24h volume in USD. If coin has no data on volume, the volume is -1.
     */
    public double getVolume() {
        return volume;
    }

    /**
     * @return The UNIX timestamp of the last time the coin was updated (on Minerstat).
     */
    public long getUpdated() {
        return updated;
    }
}
