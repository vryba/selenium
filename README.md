# selenium
Welcome to the world of Selenium WebDriver! In this Selenium test automation project I'm using Java programming language, the Page Object Model and the WebElement Wrapper. The Page Object Model is an Object Repository design pattern in Selenium WebDriver. Per seleniumhq.org this design has the following advantages:
- There is a clean separation between test code and page specific code such as locators (or their use if you’re using a UI Map) and layout
- There is a single repository for the services or operations offered by the page rather than having these services scattered throughout the tests.

In order to use utility functions to handle code that would otherwise be duplicated throughout all my tests I implemented WebElement wrapper class called Element. This is named 'Element' because it's shorter than WebElement, and we're in a 'domain' of 'web' by the nature of the library. This class wraps frequently used selenium calls with class methods of my own design.

Below diagram shows how WebElement Wrapper is used in this project:
![webelement wrapper](https://user-images.githubusercontent.com/28938415/28243569-593aeb7c-699d-11e7-9988-d39e83d73665.png)
