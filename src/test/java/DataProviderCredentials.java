import org.testng.annotations.DataProvider;



public class DataProviderCredentials {


    @DataProvider(name = "incorrectCredentials")
    public Object [][] provideIncorrectCredentials() {
        return new Object[][] {
                {"nazar@testpro.io", ""},
//                {"", "Pomidor2115"},
//                {"nazar@testpro.iom", "Pomidor2115"},
//                {"nazar@testpro.io", "Pomidor"},
//                {"nazar@testpro.iom", "Pomidor"},

        };
    }


}
