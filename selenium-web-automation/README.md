## Description
A simple Selenium test scripts to test an ecommerce website at https://demo.gondolatest.com/

## Automated Tests
[E2E_Workflow](./E2E_Workflow.md)

## Prerequisites
1.  Java Development Kit 8 (or above)
2.  Maven [ http://maven.apache.org/ ]
3.  Git (optional) [ http://git-scm.com/downloads ]
4.  IDE (optional) [[eclipse](https://www.eclipse.org/downloads/), [vscode](https://code.visualstudio.com/download) or [IntelliJ](https://www.jetbrains.com/idea/)]
## Getting Started
1. Checkout source 
   - Download the zip source at https://github.com/logigear-japan/automation-test-script-sample 
   - Or use git command: git clone https://github.com/logigear-japan/automation-test-script-sample.git
2. Choose your browser in selenium-web-automation\source\config\testng-gondolatest.xml
3. Download the chrome and firefox webdriver for your operating system and browser (current project support  chrome 91.0.4472.124 (64-bit), firefox 89.0.2 (64-bit))
   - Download chrome webdriver [here](https://chromedriver.chromium.org/downloads) which is matched with your chrome version and operating system. Then extract it and put in selenium-web-automation\source\config\drivers\\{your os}
   - Download firefox webdriver (gecko) [here](https://github.com/mozilla/geckodriver/releases) which is matched with your firefox version and operating system. Then extract it and put in selenium-web-automation\source\config\drivers\\{your os}
4. Open the windows command prompt navigate to the project directory selenium-web-automation\source
5. Run the command to start the tests: mvn clean test
All steps are recorded in this video