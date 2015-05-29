package house.my;

import house.my.web.controllers.HomeController;

import org.junit.Test;

public class HomeControllerTest {

  private HomeController controller = new HomeController();

  @Test
  public void homePageMobileSitePreference() {
    // Model model = new ExtendedModelMap();
    // assertEquals("home", controller.home(SitePreference.MOBILE, model));
  }

  @Test
  public void homePageTabletSitePreference() {
    // Model model = new ExtendedModelMap();
    // assertEquals("home", controller.home(SitePreference.TABLET, model));
  }

  @Test
  public void homePageNormalSitePreference() {
    // Model model = new ExtendedModelMap();
    // assertEquals("home", controller.home(SitePreference.NORMAL, model));
  }

}
