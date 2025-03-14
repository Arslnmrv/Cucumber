package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.GMIBankPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;

public class GMIBankStepDefinitions {
    GMIBankPage gmiBankPage = new GMIBankPage();


    @When("kullanici gmibank sayfasina gider")
    public void kullanici_gmibank_sayfasina_gider() {
        Driver.getDriver().get(ConfigReader.getProperty("GMIBankUrl"));
    }

    @Then("sayfaya giris yapar")
    public void sayfaya_giris_yapar() {
        gmiBankPage.GMIBankLogin();
    }

    @Then("sayfaya giris yaptigini dogrular")
    public void sayfaya_giris_yaptigini_dogrular() {
        Assert.assertTrue( gmiBankPage.girisOnayWebElementi.isDisplayed());
    }

    @When("gmibank signout butonuna tiklar")
    public void gmibank_signout_butonuna_tiklar() {
        gmiBankPage.girisOnayWebElementi.click();
        gmiBankPage.signOutWebElementi.click();
    }

    @When("gmibank sayfasindan cikis yaptigini dogrular")
    public void gmibank_sayfasindan_cikis_yaptigini_dogrular() {
       Assert.assertTrue(gmiBankPage.signOutOnayWebElementi.isDisplayed());
    }

    @Then("gmibank signin butonuna tiklar")
    public void gmibankSigninButonunaTiklar() {
        gmiBankPage.ilkGirisButonu.click();
        gmiBankPage.signInButonu.click();
    }

    @And("gmibank password kutusuna {string} yazar")
    public void gmibankPasswordKutusunaYazar(String yanlisUserName) {
        gmiBankPage.gmibankUsernameKutusu.sendKeys(yanlisUserName);
    }

    @And("gmibank username kutusuna {string} yazar")
    public void gmibankUsernameKutusunaYazar(String yanlisPassword) {
        gmiBankPage.gmiBankPasswordKutusu.sendKeys(yanlisPassword);

    }

    @And("gmibank signin butonuna basar")
    public void gmibankSigninButonunaBasar() {
        gmiBankPage.gmiBankSigInOkButonu.click();
    }

    @Then("gmibank giris yapilamadigini test eder")
    public void gmibankGirisYapilamadiginiTestEder() {
        Assert.assertTrue(gmiBankPage.hataliGirisWebElementi.isDisplayed());
    }

    @Then("kullanici gecerli username ve password girer")
    public void kullaniciGecerliUsernameVePasswordGirer() {
        gmiBankPage.gmibankUsernameKutusu.sendKeys(ConfigReader.getProperty("GMIBankValidUsername"));
        gmiBankPage.gmiBankPasswordKutusu.sendKeys(ConfigReader.getProperty("GMIBankValidPassword"));
    }

    @Then("kullanici userIcona tiklar")
    public void kullaniciUserIconaTiklar() {
        gmiBankPage.girisOnayWebElementi.click();
    }

    @Then("kullanici user settings sayfasinda olmalidir")
    public void kullaniciUserSettingsSayfasindaOlmalidir() {
        gmiBankPage.userInfoWebElementi.click();
        String expectedUrl = "https://www.gmibank.com/account/settings";
        String actualUrl = Driver.getDriver().getCurrentUrl();
        System.out.println(expectedUrl.equals(actualUrl) ? "PASS" : "FAILED");
    }

    @And("kullanici language dropdown da secenekleri gorur")
    public void kullaniciLanguageDropdownDaSecenekleriGorur() {
        Select select = new Select(gmiBankPage.languageDropDown);
        gmiBankPage.languageDropDown.click();
        List<WebElement> languageOptions = select.getOptions();
        System.out.println("languageOptions1= " + languageOptions.get(0).getText());
        System.out.println("languageOptions2 = " + languageOptions.get(1).getText());

    }

    @Then("kullanici English ve Turkish den baska dil seceneginin olmadigini dogrular")
    public void kullaniciEnglishVeTurkishDenBaskaDilSecenegininOlmadiginiDogrular() {
        Select select = new Select(gmiBankPage.languageDropDown);
        List<WebElement> languageOptions = select.getOptions();
        for (WebElement w: languageOptions){
            boolean languages = w.getText().equals("Türkçe") || w.getText().equals("English");
            Assert.assertTrue(languages);
        }
    }

    @Then("kullanici My Opertions a tiklar")
    public void kullaniciMyOpertionsATiklar() {
        gmiBankPage.myOperationWebElementi.click();
    }

    @Then("kullanici Manage Customers i secer")
    public void kullaniciManageCustomersISecer() {
        gmiBankPage.manageCustomersWebElementi.click();
    }

    @And("kullanici Create a New Customer a tiklar")
    public void kullaniciCreateANewCustomerATiklar() {
        gmiBankPage.createNewCustomer.click();
    }

    @And("kullanici  {string} girer")
    public void kullaniciGirer(String ssn) {
        gmiBankPage.searcSSN.sendKeys(ConfigReader.getProperty("ssn"));
    }

    @And("kullanici Search button a tiklar")
    public void kullaniciSearchButtonATiklar() {
        gmiBankPage.searchButton.click();
    }

    @Then("kullanici fail message i gorur")
    public void kullaniciFailMessageIGorur() throws InterruptedException {
        Thread.sleep(3000);
Assert.assertTrue(gmiBankPage.failedMessage.isDisplayed());
    }


}
