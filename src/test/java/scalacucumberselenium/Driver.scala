package scalacucumberselenium

import org.openqa.selenium.{By, WebElement}

import scala.:+
import scala.collection.convert.ImplicitConversions.`list asScalaBuffer`
import scala.collection.mutable.ListBuffer


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

  def getSpecificTextFromAListOfElements(element: By, index: Int): String ={
    webDriver.driver.findElements(element).get(index).getText
  }

  def getAllTextFromAListOfElements(element: By): List[String] = {
    val elements = webDriver.driver.findElements(element)
    elements.map(_.getText).toList
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
