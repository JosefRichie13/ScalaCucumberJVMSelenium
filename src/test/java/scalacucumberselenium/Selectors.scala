package scalacucumberselenium

import org.openqa.selenium.By

class Selectors {

  val userName: By = By.id("user-name")
  val password: By = By.id("password")
  val loginButton: By = By.id("login-button")
  val homepageTitle: By = By.className("app_logo")
  val errorMessage: By = By.cssSelector(".error-message-container.error h3")
  val loginpageTitle: By = By.className("login_logo")
  val menu: By = By.id("react-burger-menu-btn")
  val logoutButton: By = By.id("logout_sidebar_link")
  val productBackpack: By = By.cssSelector("button[name*=sauce-labs-backpack]")
  val productBikelight: By = By.cssSelector("button[name*=sauce-labs-bike-light]")
  val productTshirt: By = By.cssSelector("button[name*=sauce-labs-bolt-t-shirt]")
  val productJacket: By = By.cssSelector("button[name*=sauce-labs-fleece-jacket]")
  val productOnesie: By = By.cssSelector("button[name*=sauce-labs-onesie]")
  val productTshirtRed: By = By.cssSelector("button[name*=allthethings]")
  val cart: By = By.className("shopping_cart_link")
  val checkout: By = By.id("checkout")
  val firstName: By = By.id("first-name")
  val lastName: By = By.id("last-name")
  val zipCode: By = By.id("postal-code")
  val continueButton: By = By.id("continue")
  val finishButton: By = By.id("finish")
  val checkoutBanner: By = By.className("complete-header")

}