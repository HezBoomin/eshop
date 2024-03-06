# Reflection

## Hezekial Octora Yudha Tampubolon - 2206046714
## Module 1 - Reflection

### Exercise 1

For the two new features, i already did the features functionality correctly. 
I already follows the clean code principles by using with descriptive name and efficient 
function. I also use meaningful names except i use variables "cur" to describe the variables 
for current data if we need to compare the new and old version of the same data in a function. 
For the Secure coding, i don't think we use any authentication and authorization for this assignment. 
For input validation i don't think there is any input validation that i need to use for this assignment also.

### Exercise 2 No. 1

I feel like unit test is very useful for building an application to verify if the app feature works properly. 
I think the amount of unit test in a class should be at least 2. This is to verify if the feature functionality in
negative and positive occasions. I think the percentage of code coverage can be useful to check if we test our program enough.
achieving 100% code coverage is a good practice and can help identify untested or dead code, 
it does not guarantee the code is completely free of bugs or errors.

### Exercise 2 No 2

I think the code for the new test will be clean because my CreateProductFunctionalTest is already clean.
The code could become repetitive if we use the same setup, so it would be good if we use the prior setups and initial 
variables to reduce redundancies.

## Module 2 - Reflection

### List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

The quailty issue that I fix is Unused import. It's pretty straight forward to fix this quality issue, 
that is to delete the unused import. Inside Product.java there's an unused import that i just delete right away.
To make the code cleaner, I also delete some unused import in other files.

### Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Yes it implemented Continuous Integration and Continuous Deployment. Everytime I push my code to GitHub, there are tools
such ass JUnit, OSSF Scorecard, and Sonarcloud to test my code so my code is integreted and verified. I also implement
automatic deployment with Koyeb using a Dockerfile.

## Module 3

### Exercise

1. I applied SRP, OCP, and LSP. SRP asserts that a class should have a singular responsibility.
   OCD maintains that a module should be amenable to extension to accommodate new requirements
   while remaining impervious to direct modification.  LSP guides the design of inheritance hierarchies in object-oriented systems,
   ensuring that subclasses can be used interchangeably with their base classes without introducing unexpected behavior.

2. The advantages of applying SOLID principles is to minimize dependencies, allowing other developers to develop our app further
   without causing any problem when they try to develop one of the aspect of the app.
3. The disadvantages of not applying SOLID principles is it can cause a harmful ripple effect to others when other developers try to develop
   the app. There can be a case where if one aspect of the app is to dependent to other aspect it can cause error or problem
   to that other aspect.
## Module 4

## Reflection

1. I think the TDD flow is useful enough for me. I think the TDD flow is useful because it helps me to write a clean code and also
    helps me to write a code that is easy to maintain. It also helps me to write a code that is easy to understand and also easy to test.

2. I think my test have successfully followed F.I.R.S.T. principle. The test is Fast, Independent, Repeatable, Self-Validating, and Timely. 
    The test is fast because it only takes a few seconds to run. The test is independent because it doesn't depend on other test. The test is repeatable
    because it will give the same result if we run the test multiple times. The test is self-validating because it will give a clear result if the test pass or fail.
    The test is timely because it is written before the code is written.


[Deployment Link](https://eshop-hezboomin.koyeb.app/)


