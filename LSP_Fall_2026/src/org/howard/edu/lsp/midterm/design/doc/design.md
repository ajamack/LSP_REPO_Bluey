Class: Student
Responsibilities: Store the student's ID, name, and email; provide student details; make sure its own data is valid
Collaborators: None


Class: Course
Responsibilities: Store the course code and credit hours; provide course details; make sure credit hours are positive
Collaborators: None

Class: Registration
Responsibilities: Represent one student signed up for one course; store the tuition amount and the date and time of registration; provide registration details
Collaborators: Student, Course

Class: TuitionCalculator
Responsibilities: Calculate tuition using the rate per credit hour; apply the 10% discount for 12 or more credit hours
Collaborators: Course

Class: RegistrationRepository
Responsibilities: Save registration records; report an error if a record cannot be saved
Collaborators: Registration

Class: ConfirmationNotifier
Responsibilities: Send a registration confirmation to the student
Collaborators: Student, Registration

Class: RegistrationLogger
Responsibilities: Record that a registration was processed and when it happened
Collaborators: Registration

Class: RegistrationService
Responsibilities: Run the registration process in order: create the Registration, get the tuition, save the record, send the confirmation, and log the event; stop and report a problem if saving fails
Collaborators: Student, Course, Registration, TuitionCalculator, RegistrationRepository, ConfirmationNotifier, RegistrationLogger