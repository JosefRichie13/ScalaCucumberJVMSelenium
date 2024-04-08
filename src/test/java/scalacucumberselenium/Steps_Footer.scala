package scalacucumberselenium

import io.cucumber.scala.{EN, ScalaDsl}
import org.junit.Assert._

class Steps_Footer extends ScalaDsl with EN{

  val driverMethods = new Driver
  val configs = new Configs
  val selectors = new Selectors

  Given("""I confirm that the footer is {string}"""){ (visibleStatus: String) =>
    if(visibleStatus == "not visible"){
      assertFalse(driverMethods.elementVisibleOrNot(selectors.footer))
    }
    else {
      assertTrue(driverMethods.elementVisibleOrNot(selectors.footer))
    }
  }

  And("""I click on the {string} icon in the footer""") { (footerIcon: String) =>
    footerIcon match {
      case "Twitter" =>
        driverMethods.clickButton(selectors.footerTwitter)
      case "Facebook" =>
        driverMethods.clickButton(selectors.footerFacebook)
      case "LinkedIn" =>
        driverMethods.clickButton(selectors.footerLinkedin)
      case _ =>
        throw new IllegalArgumentException("Incorrect Footer icon : " + footerIcon)
    }
    Thread.sleep(5000)
  }

  Then("""I should see the redirect link as {string}""") { (redirectURL: String) =>
    driverMethods.switchBetweenTwoTabs(1)
    assertEquals(driverMethods.getTheCurrentURL, redirectURL)
  }
}
