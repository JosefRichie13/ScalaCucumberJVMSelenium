package scalacucumberselenium

import org.openqa.selenium.By


class Driver {

  def loadAURL(url: String): Unit ={
    webDriver.driver.get(url)
  }

  def typeText(textToType: String, element: By): Unit ={
    webDriver.driver.findElement(element).sendKeys(textToType)
  }

  def clickButton(element: By): Unit ={
    webDriver.driver.findElement(element).click()
  }

  def getTextFromElement(element: By): String ={
    webDriver.driver.findElement(element).getText
  }

  def getTextFromAListOfElements(element: By, index: Int): String ={
    webDriver.driver.findElements(element).get(index).getText
  }

  // Checks if an element is present in the DOM or not
  // Returns FALSE if element is NOT visible, which means the size will be 0
  // Returns TRUE if element is visible, which means the size will be greater than 0
  def elementVisibleOrNot(element: By): Boolean ={
    val status = webDriver.driver.findElements(element).size()
    if(status == 0){
      false
    }
    else {
      true
    }
  }

}
