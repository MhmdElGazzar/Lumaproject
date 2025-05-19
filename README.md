# LUMA_PROJECT

📌 Project Title
LUMA E-Commerce Website – Automated Testing Project

🧪 Project Overview
This project is part of my graduation track at the Information Technology Institute (ITI), where I applied manual and automated testing techniques on LUMA – a demo e-commerce website.
It demonstrates my hands-on skills in functional testing, framework design, and real-world test scenarios using industry-standard tools and practices.

🎯 Objectives
Validate key functionalities of the LUMA website

Automate core test scenarios using Selenium and TestNG

Apply best practices such as Page Object Model (POM)

Generate readable and insightful test reports (Allure)

Run tests automatically through CI/CD using Jenkins

Document and manage test data using external JSON files

Use BDD with Cucumber for end-to-end scenarios

🧰 Tech Stack & Tools
Tool                 	    Purpose
Java            	    Main programming language
Selenium WebDriver	    Web automation
TestNG	                Test framework
Cucumber         	    BDD (Behavior-Driven Development)
POM Design Pattern	    Code organization
JavaScript Executor	    Handling complex web elements
Allure Report	        Test reporting
Jenkins	                Continuous Integration
GitHub        	        Version control
IntelliJ IDEA	        Development environment
JSON	                External test data

🔍 Types of Testing Covered
✅ Functional Testing (Manual & Automated)

✅ Positive and Negative Testing Scenarios

✅ End-to-End Testing (BDD using Cucumber)

✅ UI Interaction with JavaScript Executor

✅ Data-driven Testing with JSON

🧪 Sample Test Scenarios
🛒 User adds product to cart and completes checkout

❌ User tries login with invalid credentials (negative test)

✅ Valid user login and profile verification

📦 Product filtering, sorting, and search functionality

📤 Order history and details review

📂 Project Structure
pgsql
Copy
Edit
LumaTestingProject/
│
├── src/
│   └── main/
│       └── java/
│           └── org/
│               ├── Pages/               → Page Object classes (POM)
│               │   ├── AccountPage, CartPage, LoginPage, etc.
│               └── Utilities/           → Utility classes (Waits, Logs, DataUtil)
│
│       └── resources/
│           ├── log4j.properties         → Logging configuration
│           └── TestData.json            → External test data
│
├── test/
│   └── java/
│       ├── Base/                        → Base test setup classes
│       │   ├── TestBase, TestBaseLogin, TestBaseForEndTOEnd
│       │
│       ├── Cucumber/                    → BDD structure
│       │   ├── features/                → Feature files (.feature)
│       │   ├── Steps/                   → Step definitions
│       │   └── Runner/                  → Cucumber test runner
│       │
│       ├── E_2_E/                       → End-to-End TestNG scenarios
│       │   ├── EndToEndValidSce, E_2_E_invalidSce
│       │
│       ├── PositiveScenario/           → Positive test cases
│       │   ├── Add_To_Cart, LogOut, etc.
│       │
│       └── NegativeScenario/           → Negative test cases
│           ├── AddToCartWithoutLogin, CheckOutWithoutLogin, etc.
│
├── logs/                                → Logs generated during test execution
├── README.md                            → Project documentation


🚀 How to Run the Project
Clone the repo

Open with IntelliJ

Run mvn clean test (for TestNG tests)

For Cucumber, use the Cucumber Runner class

View reports in allure-results (generate with Allure command)

🎓 About Me
This project was created as part of my graduation from the Software Testing & QA track at ITI (Dec 2024 – May 2025), supported by Banque Misr.
I’m passionate about building robust, scalable testing solutions and delivering quality software.

📫 Contact
LinkedIn: https://www.linkedin.com/in/nourhan-ismail-7094a7282/

Email: nourhanismailemam@gmail.com

