package Model;

/**
 *
 * @author Legion
 */
public class EastAsiaCountry extends Country {

    private String countryTerrain;
    
    public EastAsiaCountry() {
        super();
    }

    public EastAsiaCountry(String countryCode, String countryName, double totalArea, String countryTerrain) {
        super(countryCode, countryName, totalArea);
        this.countryTerrain = countryTerrain;
    }

    @Override
    public void display() {
        System.out.println(String.format("%-20s%-20s%-20.1f%-20s", super.countryCode, super.countryName,
                 super.totalArea, this.countryTerrain));
    }

}
