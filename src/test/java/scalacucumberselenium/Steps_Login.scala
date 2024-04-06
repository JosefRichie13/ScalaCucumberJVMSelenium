package scalacucumberselenium

import io.cucumber.scala.{EN, ScalaDsl, Scenario}
import org.junit.Assert._


class Steps_Login extends ScalaDsl with EN {

  val driverMethods = new Driver
  val configs = new Configs
  val selectors = new Selectors

  Given("""I open the web page"""){ () =>
    driverMethods.loadAURL(configs.mainURL)
  }

  When("""I login as a {string} user"""){ (userType: String) =>
    userType match {
      case "standard" =>
        driverMethods.typeText(configs.validUser, selectors.userName)
        driverMethods.typeText(configs.password, selectors.password)
    }
    driverMethods.clickButton(selectors.loginButton)
  }

  Then("""I should see {string} in the {string}"""){(message: String, page: String) =>
    page match {
      case "homepage" =>
        assertEquals(driverMethods.getTextFromElement(selectors.homepageTitle), message)
        assertFalse(driverMethods.elementVisibleOrNot(selectors.loginButton))
      case "loginpage" =>
        assertEquals(driverMethods.getTextFromElement(selectors.loginpageTitle), message)
        assertTrue(driverMethods.elementVisibleOrNot(selectors.loginButton))
      case _ =>
        throw new IllegalArgumentException("Incorrect Page : " + page)
    }
  }


}
