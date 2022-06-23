# CapstoneProject
Capstone Project – Nidhi Shah

The Capstone project given to the QA Automation cohort from Activate Work consists of two parts.
1.API Testing.
2.Selenium Web driver Testing. 

1.API Testing: 
Given to us three endpoint we were asked to write tests such as: 
https://gorest.co.in/public/v2/users
https://gorest.co.in/public/v2/posts
http://api.openweathermap.org/data/2.5/weather?q=New York&appid=1f3c5ae0f38df8fd7bc09ad6874a4039
The following tests were asked:
1.	Content-Type header is present.
2.	Check the HTTP status code for 404.
3.	Check the HTTP status code for 200.
4.	Check that the response code is one of 200, 201, or 202 (hint: use “oneOf”).
5.	Check that the Response time is less than 300ms.
6.	For endpoint [v2/users], write a test script:
a.	To verify that ID:3129 contains the name: “Samir Acharya”
b.	To verify that the response contains “Bankim Pillai”
•	The challenge here was, the end point data was live and kept changing hence some of the test cases fail, as data changes are very frequent. 
•	I wrote the test scripts in chai assertion library and wrote the initial scripts in postman, 
then ran the collection via JSON link in newman CLI.I also created a freestyle project in Jenkins and
configured build section to run newman tests via Jenkins. 
•	All screen shots are present in the document Capstone_NidhiSHah_APITesting.docx

2.Selenium Web driver Testing

We were given the task to automate 19 prewritten test cases which can be found in NidhiShah_CapstoneTestCaseDocument.xls using Selenium web driver.
The Application under test was: https://www.alexandnova.com

Introduction
This is a ecommerce website which sells kids’ stuff like clothing, shoes etc.,
This website has a login page reached via Home>Account path. The login page has login screen and register link to register new customers. 
Registration form has 4 fields – first name, last name, email, and password. The website does not register if email and password are left blank.
The website doesn’t register us if we give an invalid email. The website doesn’t register us if password given is less than 5 characters long. 
The website also consists of a search page, which helps us to search products. Search box is part of home page. 
On selecting a product and clicking on the product link it takes us to a product page where all details of the product are displayed. 
We can select the size, color and quantity of product here and then add it to cart. We can change quantity of product in cart too. 
Further we can checkout this product.

Challenges
•	This website presented itself to be very unpredictable and unstable.
•	The validations kept changing through out the testing period. One day the website would say “Sorry! Please try that again. “ 
And another time it said, “Invalid email.” When testing for invalid email registration. 
Such and similar cases seen throughout invalid validation testing. Hence code had to be updated very frequently. 
•	Another challenge was to deal with ReCAPTCHA. ReCAPTCHA’s are to make sure person entering website data is a human and not automation.
 The only way we could handle these were put a hard wait whenever expected and solve the reCAPTCHA’s manually before it times out. 
 Many a times the reCAPTCHA’s were so long and so many that login failed as it timed out then we changed the reCAPTCHA solving time from 10s to 20s and that worked better. 
•	The website was also unstable when automating as few of the fields never got filed in while it was there in the script. 
Adding waits here and slowing down a bit helped to ensure all fields got filled correctly.
 Small waits like implicit waits and Thread.sleep() for very short times like 1s helped a lot to ensure proper delivery of data to the website.

The technical challenges were 
•	Figuring out xpaths that were not absolute. 
•	Dividing the test code into Base Test and Test classes.Where Base test had all the initial reports set up. and each Test class had test cases for a particular scenario.
•	Researching and learning a lot of topics in extent reports.
•	Learning use of getattribute() of custom attributes. 
•	Learnt and implemented the POM framework for Selenium Web driver scripts. 
•	Learning and implementing a new way of writing test NG files that enabled serial and parallel testing of multiple browsers.
•	Learnt and implemented Data Provider for one of the test cases.TC_0003

Technical Specifications:
I built a Maven project on IntelliJ (paid version) and setup the project for Java 8.0.
I then added the following Dependencies and plugin.

Dependencies added:
 org.seleniumhq.selenium,selenium-java, version 4.0  - Selenium Dependency
 org.testng,testng, version 6.14.3  - TestNG dependency 
 org.testng,reportng, version 1.2.2 – TestNG Dependency
 commons-io, commons-io, version 2.11 – Commons io used for file handling during screen shots 
com.aventstack, extentreports, version 3.1.5 – Exten report dependency to create visually rich reports

 Plugins added:Inaddition to the mavenquick start plugins I added the following
 org.apache.maven.plugins, maven-compiler-plugin,version 3.5.1
 org.apache.maven.plugins, maven-surefire-plugin, version 3.0.0-M6

Project Layout:
•	Pom.xml and testing.xml in the root folder.
•	All Page object Model related files in src/test/java/com/alexnova/nidhishah/pages folder
•	All testcases in src/test/java/com/alexnova/nidhishah /tests folder.
•	Extent report initializing and flushing in BaseTest.java
•	Each Test class is for the test scenario of that page and has multiple tests in it.
•	We made use of TestNG annotations within the Test Classes.
•	The browser is picked by parameterizing the testng.xml file. This is a special way of initializing browser from testng.xml file, 
•	the Select browser method initializes drivers and is present in the src/test/java/com/alexnova/nidhishah/library folder. 

My project can also be seen at : https://github.com/nidhishaah/CapstoneProject.git
In addition, this project will be submitted with a presentation Capstone_NidhiShah.ppt and a word document documenting the Jenkins run of this project Capstone_Jenkins_NidhiShah.docx

Overall, it was a pleasure learning and working in the QA Automation Cohort of Activate Work. Thankyou Igor. 



 


