package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class WebBase {
public static WebDriver driver;

    public String browserstack_username = "";
    public String browserstack_accesskey = "";
    public String sauceLab_username = "";
    public String sauceLab_accesskey = "";
    public String browserStackuserName="";
@BeforeMethod
@Parameters({"useCloudEnv", "cloudEnvName", "os", "os_version", "browserName", "browserVersion", "url"})
  public static void setup(@Optional("false") boolean useCloudEnv) throws MalformedURLException {
    if (useCloudEnv==false){
    driver=getLocalDriver("chrome");}
    if (useCloudEnv==true){
        driver=getCloudDriver("browserstack","OS X","Sierra","chrome",
                "88","ganahamrioui1","PumTSx2dywjMsyXLZpmZ");
    }
    driver.get("https://us.louisvuitton.com/");
}
  public static WebDriver getLocalDriver(@Optional("ChromeDriver") String browserName){
      if (browserName.equalsIgnoreCase("chrome")){
          WebDriverManager.chromedriver().setup();
          driver=new ChromeDriver();
      }
      if (browserName.equalsIgnoreCase("firefox")){
          WebDriverManager.firefoxdriver().setup();
          driver=new FirefoxDriver();
      }

      return driver;
  }

  public static WebDriver getCloudDriver(String envcloudName,String os,String os_Version,String browser,String browserVersion,
                                         String envUsername ,String envAccessKey ) throws MalformedURLException {
      DesiredCapabilities cap = new DesiredCapabilities();
      cap.setCapability("browserName",browser);
      cap.setCapability("bowser_version",browserVersion);
      cap.setCapability("os",os);
      cap.setCapability("os_version",os_Version);

      if (envcloudName.equalsIgnoreCase("browserStack")){
          driver = new RemoteWebDriver(new URL("http://" + envUsername + ":" + envAccessKey +
                  "@hub-cloud.browserstack.com/wd/hub"),cap);
      }
      return driver;
  }
  @AfterMethod
  public static void closeDriver(){
    driver.quit();
  }
  public static void main(String[] args) throws MalformedURLException {
      setup(true);
  }
}
