package step_def;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import pages.LoginPage;
import pages.dashboardPage;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;
import utilities.Driver;

public class loginStepDef {

    dashboardPage Dashboardpage=new dashboardPage(); //Abstrac classa ulaşmak için oluşturulan dummy clasa ait obje üretilmesi
    LoginPage loginPage=new LoginPage();
    Actions action=new Actions(Driver.getDriver());

    @Given("Kullanici ilgili URL ile sayfaya ulasir.")
    public void kullanici_ilgili_url_ile_sayfaya_ulasir() {

        String url = ConfigurationReader.getProperty("url");
        Driver.getDriver().get(url);
        BrowserUtils.waitFor(2);

    }

    @And("Kullanici {string} butonuna tiklar.")
    public void kullanici_butonuna_tiklar(String string) {

        Dashboardpage.acceptCookies.click();
        BrowserUtils.waitFor(2);
        Dashboardpage.loginbutton.click();
        BrowserUtils.waitFor(2);

    }
    @And("Kullanici Kurumsal Giriş butonuna tiklar.")
    public void kullanici_kurumsal_giriş_butonuna_tiklar() {

      action.click(Dashboardpage.institutionalButton).build().perform(); // normal click metodunda hata attığı için action class ile click yaptım
      BrowserUtils.waitFor(2);

    }

    @And("Kullanici açilan sayfada ilgili alanlari invalid verilerle doldurur ve Giriş Yap butonuna tiklar.")
    public void kullanici_açilan_sayfada_ilgili_alanlari_invalid_verilerle_doldurur_ve_giriş_yap_butonuna_tiklar() {

        BrowserUtils.switchToWindow("Param™ | Türkiye'nin Elektronik Parası"); //yeni açılan tab da işlem yapmak için kullandığım method
        BrowserUtils.waitFor(2);
        loginPage.userName.sendKeys("12345678901");
        BrowserUtils.waitFor(2);
        loginPage.userpassword.sendKeys("123456");
        BrowserUtils.waitFor(2);
        action.click(loginPage.signInButton).build().perform();
        BrowserUtils.waitFor(2);
    }

    @Then("Kullanici ekranda çikan {string} uyari mesajini dogrular.")
    public void kullanici_ekranda_çikan_uyari_mesajini_dogrular(String actualAlertmessage) {

        String expectedMessage=loginPage.allertMessage.getText();
        Assert.assertTrue(expectedMessage.contains(actualAlertmessage));

    }

    @When("Kullanici Hesap Olustur butonuna tiklar.")
    public void kullanici_hesap_olustur_butonuna_tiklar() {

        BrowserUtils.switchToWindow("Param™ | Türkiye'nin Elektronik Parası");
        BrowserUtils.waitFor(2);
        action.click(loginPage.accountButton).build().perform();
        BrowserUtils.waitFor(2);

    }

    @When("Kullanici ilgili alanlari valid verilerle doldurur ve Devam butonuna tiklar.")
    public void kullanici_ilgili_alanlari_valid_verilerle_doldurur_ve_devam_butonuna_tiklar() {

        loginPage.nameArea.sendKeys("test");
        BrowserUtils.waitFor(2);
        loginPage.surnameArea.sendKeys("test");
        BrowserUtils.waitFor(2);
        loginPage.emailArea.sendKeys("testparam@test.com");
        BrowserUtils.waitFor(2);
        loginPage.gsmNumberArea.sendKeys("5554443311");
        BrowserUtils.waitFor(2);
        loginPage.checkBox1.click();
        BrowserUtils.waitFor(2);
        loginPage.checkBox2.click();
        BrowserUtils.waitFor(2);
        loginPage.checkBox3.click();
        BrowserUtils.waitFor(2);
        action.click(loginPage.continueButton).build().perform();
        BrowserUtils.waitFor(2);
    }

    @When("Kullanici açilan OTP ekraninda ilgili alan invalid veri ile doldurur ve Onayla butonuna tiklar.")
    public void kullanici_açilan_otp_ekraninda_ilgili_alan_invalid_veri_ile_doldurur_ve_onayla_butonuna_tiklar() {

        for (int i = 1; i <7 ; i++) {
            String locator="//input[@name='input"+i+"']";
            Driver.getDriver().findElement(By.xpath(locator)).sendKeys(""+i+"");
            BrowserUtils.waitFor(2);

        }
        //Yukarıda bulunan for loop ile ilgili olarak locatorler arasında belirli bir ilişki vardı
        //her hücrede locator de bulunan rakam bir artıyordu
        /*loginPage.codeCell1.sendKeys("1");
        BrowserUtils.waitFor(2);
        loginPage.codeCell2.sendKeys("2");
        BrowserUtils.waitFor(2);
        loginPage.codeCell3.sendKeys("3");
        BrowserUtils.waitFor(2);
        loginPage.codeCell4.sendKeys("4");
        BrowserUtils.waitFor(2);
        loginPage.codeCell5.sendKeys("5");
        BrowserUtils.waitFor(2);
        loginPage.codeCell6.sendKeys("6");
        BrowserUtils.waitFor(2);*/
       action.click(loginPage.confirmButton).build().perform();
        BrowserUtils.waitFor(2);

    }

    @Then("Kullanici ekranda çikan {string} uyari mesajini dogrularr")
    public void kullanici_ekranda_çikan_uyari_mesajini_dogrularr(String actualAlertmessageOTP) {

        String expectedMessage=loginPage.allertMessageOTP.getText();
        Assert.assertTrue(expectedMessage.contains(actualAlertmessageOTP));

    }



}
