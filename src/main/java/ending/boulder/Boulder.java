package ending.boulder;

public class Boulder {
    private int boulderHealth;

    public Boulder(int initialHealth) {
        this.boulderHealth = initialHealth;
    }

    public void attemptToBrakeBoulder() {
        // Logic for attempting to break the boulder will go here.
        // For example, this could reduce the boulder's health.
    }

    public int getBoulderHealth() {
        return boulderHealth;
    }
}
