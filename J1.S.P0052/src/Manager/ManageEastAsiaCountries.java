package Manager;

import Model.EastAsiaCountry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Legion
 */
public class ManageEastAsiaCountries {

    private ArrayList<EastAsiaCountry> asiaCountriesList;

    public ManageEastAsiaCountries() {
        this.asiaCountriesList = new ArrayList<>();
    }

    public ManageEastAsiaCountries(ArrayList<EastAsiaCountry> asiaCountriesList) {
        this.asiaCountriesList = asiaCountriesList;
    }

    public ArrayList<EastAsiaCountry> getAsiaCountriesList() {
        return asiaCountriesList;
    }

    public void setAsiaCountriesList(ArrayList<EastAsiaCountry> asiaCountriesList) {
        this.asiaCountriesList = asiaCountriesList;
    }
    

    /**
     * add a new country
     */
    public void addCountryInformation() {

        //enter code of the country (must in upper-case, not contains space)
        String code = Validation.Inputter.inputStringInForm("Enter code of country: ", "[A-Z]+");

        //enter name of the country (either in lower or upper-case, can contains space)
        String name = Validation.Inputter.inputStringInForm("Enter name of country: ", "[a-zA-Z ]+");

        //enter total area of the country (must be positive)
        Double area = Validation.Inputter.inputDouble("Enter total Area: ", false);

        String terrain = Validation.Inputter.inputStringInForm("Enter terrain of country: ", "[a-zA-Z ]+");

        if (isCodeExist(code)) {
            System.out.println("Can not have 2 country with same code!");
        } else {
            this.asiaCountriesList.add(new EastAsiaCountry(code, name, area, terrain));
            System.out.println("Add successfully");
        }
    }

    public void getRecentlyEnteredInformation() {
        if (!asiaCountriesList.isEmpty()) {
            asiaCountriesList.get(asiaCountriesList.size() - 1).display();
        } else {
            System.out.println("List is empty!");
        }
    }

    public void sortInformationByAscendingOrder() {
        Collections.sort(asiaCountriesList,
                (EastAsiaCountry t, EastAsiaCountry t1) -> t.getCountryName().compareTo(t1.getCountryName()));
    }

    public ArrayList<EastAsiaCountry> searchInformationByName(String name) {
        ArrayList<EastAsiaCountry> eastAsiaCountrys = new ArrayList<>();
        for (EastAsiaCountry eac : asiaCountriesList) {
            if (eac.getCountryName().toLowerCase().contains(name.toLowerCase())) {
                eastAsiaCountrys.add(eac);
            }
        }
        return eastAsiaCountrys;
    }

    //return true if Code in list, false in otherwise
    public boolean isCodeExist(String code) {
        for (EastAsiaCountry eastAsiaCountries : asiaCountriesList) {
            if (eastAsiaCountries.getCountryCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public void printData(ArrayList<EastAsiaCountry> arr) {
        if (arr.isEmpty()) {
            System.out.println("List is empty");
        } else {
            for (EastAsiaCountry eastAsiaCountry : arr) {
                eastAsiaCountry.display();
                System.out.println();
            }
        }
    }
}
