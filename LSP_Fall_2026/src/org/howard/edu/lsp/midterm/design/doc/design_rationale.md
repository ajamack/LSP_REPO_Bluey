Design Rationale: Evolving Requirements

My Part 2 design already keeps saving records (RegistrationRepository) and sending confirmations (ConfirmationNotifier) separate from the main registration steps in RegistrationService. Because of that, the new requirements can be handled by adding new classes instead of rewriting the ones that already work.

Changes to the CRC cards

RegistrationRepository becomes a general type (an interface) that only promises to save a registration record. Two new classes each handle one way of saving:

Class: FileRegistrationRepository
Responsibilities: Save registration records to a local file
Collaborators: Registration

Class: DatabaseRegistrationRepository
Responsibilities: Save registration records to a database
Collaborators: Registration

ConfirmationNotifier also becomes a general type that only promises to send a confirmation. Two new classes each handle one way of sending:

Class: EmailNotifier
Responsibilities: Send a confirmation to the student's email address
Collaborators: Student, Registration

Class: TextMessageNotifier
Responsibilities: Send a confirmation by text message to the student's phone number
Collaborators: Student, Registration

Student gets one new responsibility: store the student's phone number, since text confirmations need it.

One new class decides which options to use:

Class: RegistrationConfiguration
Responsibilities: Check the deployment environment and the confirmation preference; create the correct repository and notifier
Collaborators: RegistrationRepository, ConfirmationNotifier

RegistrationService does not change. It still saves the record and sends the confirmation the same way, it just receives its repository and notifier from RegistrationConfiguration instead of choosing them itself.