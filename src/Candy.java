public class Candy {

    private int candyID;
    private String candyName;

    private float monetaryValue;

    private float utilityValue;

    public Candy(int candyID, String candyName, float monetaryValue, float utilityValue) {
        this.candyID = candyID;
        this.candyName = candyName;
        this.monetaryValue = monetaryValue;
        this.utilityValue = utilityValue;
    }

    public int getCandyID() {
        return candyID;
    }

    public void setCandyID(int candyID) {
        this.candyID = candyID;
    }

    public String getCandyName() {
        return candyName;
    }

    public void setCandyName(String candyName) {
        this.candyName = candyName;
    }

    public float getMonetaryValue() {
        return monetaryValue;
    }

    public void setMonetaryValue(float monetaryValue) {
        this.monetaryValue = monetaryValue;
    }

    public float getUtilityValue() {
        return utilityValue;
    }

    public void setUtilityValue(float utilityValue) {
        this.utilityValue = utilityValue;
    }
}
