1.	How was your Assignment #2 solution organized?
Initially, assignment 2 was implemented enitrely within a single class via nested loops. 

2.	What design changes did you make for Assignment #3?
I broke the single ETLPipeline class into six smaller classes, each responsible for different aspects of the ETL Pipeline.

3.	What classes or abstractions did you introduce and why?
Employee, EmployeeParser, PayCalculator, EmployeeReader, EmployeeWriter and ETLPipeline. I introduced these for two main reasons. The first being easier debugging, and secondly assigning each section/rule of the pipeline to a class made it easier to manage.

4.	How did you divide responsibilities differently?
In my original code, ETLPipeline did everything. In assignment 3, through the use of classes, data isnt manipulated directly, but instead passes through each of the necessary classes.

5.	Why do you believe your Assignment #3 design is an improvement?

I believe although longer, assignment 3 is an improvement for its readability and maintenance. If someone was handed this code, they wouldn't have to fix nested loops or the logic that comes with them to maintain the pipeline. 
