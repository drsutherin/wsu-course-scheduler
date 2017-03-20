# WSU Course Scheduler
Autoregister.java contains the source code for an application that will automatically register a user for courses at Wright State University using a Selenium script.  The script must be updated by the user to include their (1) UID, (2) password, (3) term, and (4) CRNs for the courses.

### Use
Create a Java project named `autoregister` in Eclipse IDE, then add Autoregister.java to a package named `autoregister`. After adding necessary information outlined above, as well as the [Selenium WebDriver Java libraries](http://www.seleniumhq.org/download/) (see AlertSite tutorial below), the project can be exported as a runnable JAR and scheduled to run at the exact time registration opens.

This was tested for summer 2017 courses and is functional as of March 2017.  However, links are hard-coded so any change to WINGS Express could prevent the application from working.

### References
* [AlertSite Tutorial on Creating a Runnable JAR from a Selenium Script](http://doc.alertsite.com/synthetic/monitors/selenium/create-runnable-jar-from-selenium-script-using-eclipse.htm#project)
* [Selenium HQ "Waits" Page](http://www.seleniumhq.org/docs/04_webdriver_advanced.jsp)
