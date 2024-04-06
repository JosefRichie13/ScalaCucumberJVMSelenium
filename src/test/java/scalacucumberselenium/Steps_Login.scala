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
      case "locked" =>
        driverMethods.typeText(configs.lockedUser, selectors.userName)
        driverMethods.typeText(configs.password, selectors.password)
      case "no_username" =>
        driverMethods.typeText(configs.password, selectors.password)
      case "no_password" =>
        driverMethods.typeText(configs.validUser, selectors.userName)
      case "wrong_username" =>
        driverMethods.typeText(configs.wrongUser, selectors.userName)
        driverMethods.typeText(configs.password, selectors.password)
      case "wrong_password" =>
        driverMethods.typeText(configs.validUser, selectors.userName)
        driverMethods.typeText(configs.wrongPassword, selectors.password)
      case _ =>
        throw new IllegalArgumentException("Incorrect User Type : " + userType)
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

  Then("""I should see the login error message {string}"""){(message: String) =>
    assertTrue(driverMethods.getTextFromElement(selectors.errorMessage).contains(message))
  }

  When("""I logout of the webpage"""){() =>
    driverMethods.clickButton(selectors.menu)
    Thread.sleep(5000)
    driverMethods.clickButton(selectors.logoutButton)
  }

}
