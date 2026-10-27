1. The class does too many jobs

The registrationStudent() method calculates tuition, applies a discount, prints a confirmation, saves to a file, "sends" an email, and logs the time. Riel's heuristic says a class should capture one and only one key abstraction.

2. All data is public

Every field is public. Riel's heuristic says all data should be hidden within its class. Anything can change these fields, and CourseRegistration has no way to stop it.

3. Missing classes for Student and Course

Student details and course details are squeezed into one class instead of having their own. Riel recommends modeling the main real-world ideas in the system as their own classes.

4. Errors handled poorly

If saving the file fails, the code prints an error and keeps going. The file is also not closed if an error occurs.

