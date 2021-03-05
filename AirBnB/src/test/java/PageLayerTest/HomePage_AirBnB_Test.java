package PageLayerTest;

import Base.WebBase;
import PageLayer.HomePage_AirBnB;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class HomePage_AirBnB_Test extends WebBase {
HomePage_AirBnB homePage_airBnB = new HomePage_AirBnB();
public void init(){
    homePage_airBnB = PageFactory.initElements(driver,HomePage_AirBnB.class);
}
    @Test
    public void navigate(){
        System.out.println("hhehe");
    }
}
