# G39 Report
**Waddle - Educational Application**

## Table of Contents

1. [Team Members and Roles](#team-members-and-roles)
2. [Summary of Individual Contributions](#summary-of-individual-contributions)
3. [Conflict Resolution Protocol](#conflict-resolution-protocol)
4. [Application Description](#application-description)
5. [Application UML](#application-uml)
6. [Application Design and Decisions](#application-design-and-decisions)
7. [Summary of Known Errors and Bugs](#summary-of-known-errors-and-bugs)
8. [Testing Summary](#testing-summary)
9. [Implemented Features](#implemented-features)
10. [Team Meetings](#team-meetings)

## Team Members and Roles

| UID      |       Name        | Role |
|:---------|:-----------------:| ---: |
| u7490271 |    Jerry Zhao     | Backend Logic and UI design and Development|
| u7348473 | Karthik Vemireddy | Firebase Development, Backend logic and Development|
| u7499989 | Matthew Richards  | Software Testing and Development |
| u7124454 |     Ryan Yoon     | UI design |
| u7474428 | Michael Ostapenko | Development |

## Summary of Individual Contributions
**u7490271, Jerry Zhao**, I contributed approximately 35% of the code. The following are my contributions:
* CourseAVL.java (AVL Tree structure) and CourseAVLTest.java
* DataReader.java and dataInstances.csv (generation of 2500 data instances)
* FirebaseWaddleDatabaseServiceClient.java, lines 106-134, 247-272, 679-728
* Commented all ViewModel and View classes
* ViewModel classes
    * CourseRecyclerViewAdapter.java, whole file
    * UsersRecyclerViewAdapter.java, whole file
    * UserViewModel.java, excluding lines 75-90
* View classes
    * DashboardFragment.java, excluding lines 59-67 and initial expandable button clicking logic
    * ProfileFragment.java, whole file
    * SearchFragment.java, excluding onQueryTextSubmit and onQueryTextChange
    * ConfirmJoinCourse.java, whole file
    * CreateClass.java, half contributions
    * MainActivity.java, whole file
    * UserPage.java, whole file
* XML files
    * activity_confirm_join_course.xml
    * activity_main.xml,
    * create_class.xml
    * fragment_profile.xml
    * fragment_search.xml
    * list_view_items.xml
    * bottom_nav.xml
    * activity_user_page.xml
    * item_view_courses.xml
    * item_view_people.xml
* Report - Writing contributions
    * Conflict Resolution Protocol, whole section
    * Application Description, whole section
    * Application Design and Decisions excluding Observer pattern, Grammar and Tokeniser and Parsers
    * Summary of Known Bugs and Errors
    * CourseAVLTest of Testing summary
    * All Implemented features excluding Search by Parsers/Tokenisers and UI-Tests
    * Team Meetings
* Scribed Meeting 4, 5, 6
* All presentation slides





**u7348473, Karthik Reddy Vemireddy**, I contributed approximately 35% of the code. The following are my contributions:
* All the search expressions:
  * CourseDescExpression.java
  * CourseExpression.java
  * EmailExpression.java
  * EmptyExpression.java
  * Exp.java
  * NameExpression.java
* Search Tokenizer SearchBarTokenizer.java
* Creation, Maintenance and linking of firebase and development around firebase.
* Search Parser parseName.java
* Tokenizer Token class Token.java
* FirebaseWaddleDatabaseServiceClient.java Wrote the whole database code and others added additional functions.
* CustomOnCompleteListener.java to listen to firebase async operations completion.
* Helped in architecting most of the data object classes.
* Added additional comments in all files.
* Responsible for code refactoring in many code files.
* ViewModel classes
    * CommentRVAdapter.java, added database listener functionality for fire-sync.
    * CommentViewModel.java, whole file
    * CreateClassViewModel.java, 75% contribution
    * DiscussionAdapter.java, added database listener functionality for fire-sync.
    * DiscussionViewModel.java, whole file
    * ListViewAdapter.java, added database listener functionality for search.
    * SignUpViewModel.java, whole file
    * UserViewModel.java, added additional database related listeners and operations.
* View classes
    * DashboardFragment.java added additional logic for MVVM architecture and database interaction.
    * ProfileFragment.java, whole file
    * SearchFragment.java onQueryTextSubmit and onQueryTextChange
    * CoursePage.java Added listeners for database operation completions.
    * CreateClass.java, half contributions
    * DiscussionPage.java additional logic for passing to viewModels.
    * LoginView.java, majority contribution
    * SignupView.java, whole file
* XML files
    * signup.xml
    * login.xml
* Report - Writing contributions
    * Grammar, tokenizer and parser.
* I additionally assisted all the working teammates to debug several bugs throughout the development
process. 


u7474428, Michael Ostapenko, I contribute 10% of the code. Here are my contributions:
* MessagesViewModel.java Class (entire class)
* MessagesFragment.java Class (filled in entire class)
* fragment_messages.xml (added the list view for viewing notifications)
* UserDTO.class: update(), getDirectMessages(), addMessage()
* MessageFactory.java Class (entire class)
* FirebaseWaddleDatabaseServiceClient.java: notifyObservers(), detachAll(), attach(), detach()
* Observer and Subject Interfaces
* Worked on and completed a password hashing algorithm which ended up being redundant due to using firebase password security
and not included in the code

I proposed and worked on an Observer design pattern for the project to react to database changes,
i.e. course changes or discussion changes and then update the user with a notification in the
notifications tab. Adding to that I also created a Factory Design pattern to generate a notification
message based of constraints. While I did not suggest it, I did work with the MVVM pattern for designing android
applications inside of the messages fragment to display those notifications properly. 

For report writing, I wrote in information on the classes and methods I coded in, i.e. the Observer design pattern, my part
of the Factory pattern I used and the notification system as a whole. 

u7499989, Matthew Richards, I contribute 10% of the code. Here are my contributions:
* DashboardTests.java
* LoginViewTests.java
* ConfigurationManager.java
* WaddleDatabaseConfiguration.java
* WaddleDatabaseServiceClient.java
* MockWaddleDatabaseServiceClient.java
* FirebaseWaddleDatabaseServiceClient.java (created skeleton, others fleshed it out)
* WaddleDatabaseServiceClientFactory.java
* LoginModel.java
* LoginViewModel.java
* CommonRegexUtil.java
* StringUtils.java
* LoginEvent.java
* LoginPageTests.java
* RegexTests.java 

I proposed the initial architecture for the project, that is, the MVVM pattern for our pages, to decouple database and UI interactions from our business logic. I wrote the first example of these, with the LoginView, LoginViewModel, and LoginModel classes, accompanied by the LoginPageTests class, in the hopes that we could establish good practices early on. These unit tests showcased the potential of the MVVM architecture to support reproducible and isolated tests: not relying on Firebase, nor the Android emulator. To facilitate this, I designed and wrote the framework for database interaction through the project: dependency injection of the WaddleDatabaseServiceClient instances. I wrote both a mock, and Firebase implementation of this interface, which is conditionally injected based on an environment.properties file for each environment (production/in-app, unit tests, espresso tests). This allows for our unit tests to be exact and not have to worry about depending on Firebase, but rather purely testing our business rules by replacing dependency on live data with a simple map of hardcoded users. 

u7124454, Ryan Yoon, I contribute 10% of the code. Here are my contributions:

* CommentRVAdapter.java class (excluding set and add functions)
* DiscussionAdapter.java class (excluding set and add functions)
* RecyclerViewAdapter.java class (excluding listToString())
* DashboardFragment.java class: expandable floating acion button implementation and initial recyclerView implementation inside the onClick()
* CoursePage.java.java class
* DiscussionPage.java class
* DiscussionDto.java class (excluding id instances)
* CommentDto.java class (excluding id instances)
* fragment_dashboard.xml
* item_discussion.xml
* comment_item.xml
* activity_course_page.xml
* activity_discussion_page.xml
* item_course.xml
* UML diagrams for the report

The discussion and comment data objects were created to make it possible to submit discussion posts and make comments onto them.

Expandable floating action buttons were implemented in the Dashboard fragment to be able to expand button actions of 
joining or creating classes to add to the Dashboard Course RecyclerView. Each item was also clickable to access each 
courses’ discussion page where users could add a discussion with a title and the content of the discussion. The 
discussions themselves were able to be clicked to access the discussion page where comments relating to the discussion 
could be posted. I did not implement any of the persistence that relates to the database.

I contributed to making the UML for the report with assistance of my group members in helping understand how to relate and
 place everything within the diagram.

## Conflict Resolution Protocol
1. Define the source of the conflict: The mediator will initiate communication with all team members and identify where the conflict lies.
2. Look beyond the incident: Party members will act professionally to not let personal emotions play a part in your actions.
3. Request solutions: The mediator will ask all party members for solutions. If a solution cannot be thought of, then the party's perspective on the situation should be said.
4. Identify solutions both disputants can support: Find the most acceptable solution, a compromise between both/all party members.
5. Agreement: The mediator will ask party members to accept one of the alternatives identified and reach a negotiated agreement


## Application Description
Waddle is an educational app designed through an MVVM architecture which enables seamless access to school content for university students and teachers. Users can join courses created by teachers, which include content, discussion forums, and participant lists. The app features a search function for finding friends and interesting courses. Users can explore the courses enrolled by other users, including their name and email. Additionally, users can engage in course discussions by posting questions and participating in group chat messaging. Waddle simplifies the educational experience, fostering collaboration and knowledge-sharing among students and teachers.

Waddle offers a user-friendly sign-up process where users can easily create an account by providing essential information such as their first name, last name, username, email, and password. During sign-up, users also have the option to choose their role as a teacher or student, which determines their course management permissions. The app is organised into four tabs: the dashboard, user/course search, notifications, and profile view. After logging in, users are initially greeted with an empty dashboard until they enroll in courses. To join a course, users can navigate to the join course pop-up where they enter a course code provided by their teacher. Alternatively, if no course code is given, users can explore the search tab to manually find and join courses of interest.

Once a user joins a course, it becomes visible on their dashboard. By clicking on the course, users can access information like course content, participants, and discussion forums. The search tab allows users to find and connect with friends, view their enrolled courses, and access their email addresses. In the profile tab, users can view their personal information, including full name, user status, and a list of enrolled courses. Additionally, a convenient logout function is available in the profile tab for users who wish to log out.

![Key Features](./images/keyfeatures.png) <br>


**Application Use Cases and Examples**<br>
Waddle is primarily targeted towards high school/university students who require a more accessible view of courses, announcements, and simple-to-use discussion forums. Teachers are also encouraged to utilise Waddle to not only be able to send out announcements, but also manage and view their course details with ease.

During a crucial week, the ANU's Wattle page is undergoing maintenance, and Kevin requires access to the COMP2100 course page to check for announcements by Bernado.
   * Kevin can log in to the application and utilise the search feature to find the COMP2100 course. 
   * Once he joins the course, he will be able to view all the announcements made by Bernado. 
   * In case Kevin is uncertain about any of Bernado's announcements, he can seek assistance by posting on the course page's discussion board, where both teachers and students can provide help.

At the start of a new semester, Anthony is interested in finding out which courses his friends are enrolled in:
   * Anthony utilises the search function in the app by entering his friend's email address, returning the results related to his friend. 
   * Anthony can then click on his friend's profile to gather more information such as the friend's email, full name, and a list of courses in which they are currently enrolled. 
   * Anthony can now easily discover the courses his friends are doing for the new semester.

Kate wants to post a question on the discussion forum regarding AVLTrees discussed in the latest COMP2100 lecture. Here's how she can do it:
   * Kate goes to the COMP2100 course page from her dashboard and selects the discussion forums tab. 
   * Within the discussion forums, Kate creates and posts a new post with a suitable title and writes her question in the post. 
   * Kate doesn't need to reload the page. As soon as Bernado or other users respond to her forum post, their answers will be displayed instantly.

## Application UML
**Front End UML**
![Front End UML](./images/frontendUML.png) <br>

**Back End UML**
![Back End UML](./images/backendUML.png) <br>


## Application Design and Decisions
*We used the following data structures in our project:*

**Data Structures**

1. **List**
    * Objective: Lists are used to primarily store a user’s courses. It is also used in custom adapter classes for displaying courses or course participants as a scrollable list in the app.
    * Locations: MockWaddleDatabaseServiceClient.java, WaddleDatabaseServiceClientFactory.java , ListViewAdapter.java, RecyclerViewAdapter.java, UserViewModel.java, AdminUserDto.java, CourseDto.java, StudentUserDto.java, TeacherUserDto.java
    * Reasons:
        * Lists were used for these classes to handle insertion/deletion operations and memory allocations. If a course is to be added/deleted within the

2. **AVL Tree**
    * Objective: AVL Trees are used for storing and managing all courses which exist in the Waddle firebase
    * Locations: CourseAVL.java
    * Reasons:
        * The addition of an arbitrary amount of classes will add complexity to the program, making it slower for the user to navigate the application.
        * Using a tree lowers insertion time to O(log n) giving a worst case of O(n log n) which is significantly less time complexity compared to that of an array.


**Design Patterns**

1. **Singleton Design Pattern**
    * Classes utilised: FirebaseWaddleDatabaseServicesClient.java
    * Since the singleton design pattern ensures only one instance of a class is created throughout the lifetime of an application, utilising this design pattern can ensure that only one instance of the FirebaseWaddleDatabaseServicesClient.java class is created, preventing any potential concurrency issues and improving the overall performance of the application. Additionally, it simplifies the codebase by providing a global point of access to the FirebaseWaddleDatabaseServicesClient.java instance, making it easier to maintain and debug, overall, leading to more efficient and streamlined code and an improved user experience.

2. **Factory Method Design Pattern**
    * Classes utilised:
        * UserDto.java, whole file
        * StudentUserDto.java, whole file
        * TeacherUserDto.java, whole file
        * Creating Messages in UserDto.java (Factory for Strings)
    * The factory method design pattern allows for the creation of objects without having to specify the exact class of the object that will be created. In the context of a UserDto class extended by TeacherUserDto and StudentUserDto, utilising the factory method design pattern can offer several benefits. It allows for the creation of objects of the UserDto class and its subclasses without needing to know the exact class of the object at runtime. This can simplify the codebase, reduce code duplication, and make the system more modular. Additionally, it used used to generate notification messages given what is changed in the database. It also provides a clear separation of concerns between the creation of objects and their implementation, making it easier to maintain and test the code.

3. **Facade Design Pattern**
   * Classes utilised:
     * All classes in database folder
     * All classes in viewmodel folder
   * The facade design pattern played a crucial role in our project, serving as the main architectural framework for our application. We organized all the Firebase-related logic into the database folder and accessed it through the viewmodel course, incorporating additional logic when necessary. This approach allowed us to present a simplified interface for a sophisticated subsystem that encompassed numerous interconnected elements. Given the complexity of our application, which involved multiple features and intricate logic for seamless integration, the facade design pattern greatly facilitated the overall development process.

4. **Observer Design Pattern**
   * Classes utilised:
     * FirebaseWaddleDatabaseServiceClient.java (Subject)
     * UserDto.java (And its child classes) (Observer)
   * The observer desing pattern plays a side role in the project, and is used to notify the UserDTO of any changes in the database. The firebase client is the subject, and it
contains a listener which detects changes in necessary parts of the database. The corresponding UserDTO is added to the list of observers and the subject notifies all observers when there is a change. The userDTO then updates, generating a notification message for the user which is then displayed in the messages/ notifications tab. This observer pattern allows a clean and seemless notification method throughout the program and allows observers to respond to necessary changes in the database. 

**Grammar(s)**

Production Rules:

    1. <user name> ::= <user name, user email>|<user email>|<empty>
       <user email> ::= <user email, user email>|<user name>|<empty>
    2. <course name> ::= <course name, course description>|<course description>|<empty>
       <course description> ::=  <course description, course description>|<course name>|<empty>

* The grammar designed is as shown above. This grammar allows us to check if the token is a username or a 
combination of username and user email or just user email. It can also check if entered token is a combination
  of multiple emails and queries accordingly.
* The grammar for courses also works in the same manner. This grammar allows us to check if the token is a course name, or a
  combination of course name and course description or just course description. It can also check if entered token is a combination
  of multiple course descriptions and queries accordingly.
* This grammar allows the user to stack such tokens indefinitely and use all the tokens to query different results.
  This contributes to a robust result for incorrect queries. Hence, this grammar is used in a tokenizer and parser
  pair which are used to query search results by the logged-in user. When searching for Users, the grammar(1) used as tokenizer
  tokenizes the input string into username or user email. Otherwise, grammar(2) is used where the tokenizer tokenizes input to course name or course description.
* The database queries the parsed input and checks what results to return.

**Android MVVM Design Pattern**
* The MVVM Design pattern was utilised for all files in the model, view, and viewmodel files.
* Implementing all the functionality and firebase logic in a single class or activity would lead to problems in testing and refactoring the code. This is due to difficulty and unreliability in testing two components of an application simultaneously. Hence, the separation of code and clean architecture can allow us to develop a Mock database to test easily against our program and its individual components.

![mvvmdiagram](./images/mvvmdiagram.png) <br>

## Summary of Known Errors and Bugs

1. Joining course via Enter key bug 
   * In the dashboard, if a user tries to join classes by pressing the "Enter" key instead of clicking the button, they need to press "Enter" again for the action to take effect. 
   * However, when the user clicks the button directly, it works smoothly without any issues.

2. User email display bug in UserPage.java
   * When viewing other users, if a user's email address exceeds the length of the designated white box, the text extends beyond the box boundaries and overlaps with the background.

3. Dashboard name and profile information display bug
   * The first name of the user in the Dashboard fragment appears a second or so after the user first enters the app. 
   * Similarly in profile, the user's name, student/teacher status and course information take a second to load when first viewing it.
   * This is because we used @Bindable for a user's information, which takes a lot longer to load than using Serializable.

4. Rapid switching between fragments 
   * When switching too fast between the fragments, the information of users and their courses take longer time to load
   * Depending on the device, this act may result in the app to crash altogether. This bug may be due to the number of changes in activity and the number of queries sent to the database.

## Testing Summary

*[What features have you tested? What is your testing coverage?]*

**CourseAVLTest.java**
- Number of test cases: 17
- Code coverage: All methods within CourseAVL
- Types of tests created:
    * null
    * insert
    * inOrderTraversal
    * rightRotate
    * leftRotate

**LoginPageTests.java**
- Number of test cases: 5
- Code coverage: 100% line by line of the LoginViewModel.java class
- Tests created:
    * Tests getters and setters are functional in the login page view model
    * Tests that empty fields in the page causes an error pop up
    * Tests that incorrect passwords cause an error
    * Tests that an invalid user causes an error
    * Tests that a correct login flags the model to be authorised to continue

**RegexTests.java**
- Number of test cases: 1
- Code coverage: 100% of CommonRegexUtil 
- Tests created:
    * Checks the email regex against a sample of valid and invalid emails

**DashboardTests.java**
- Number of test cases: 4
- Code coverage: N/A (UI test)
- Tests created:
    * These four tests assert that the four tabs show their respective displays

**LoginViewTests.java**
- Number of test cases: 4
- Code coverage: N/A (UI test)
- Tests created:
    * Mirrors the LoginPageTests logic tests, but in the UI using Espresso


## Implemented Features

### Basic App
1. [Login]. Users must be able to log in and sign up **(easy)**
    * Classes utilised:
        * model/LoginModel.java, whole file
        * viewmodel/LoginEvent.java, whole file
        * viewmodel/LoginViewModel.java, whole file
        * viewmodel/SignUpEvent.java, whole file
        * viewmodel/SignUpEventModel.java, whole file
        * views/LoginView.java, whole file
        * views/SignupView.java, whole file
        * database/FirebaseWaddleDatabaseServiceClient.java, createNewUser, signIn, lines of code: 88-152*
    * Users can login to the app using an existing Firebase account. When a user enters their email and password on the login page, the app checks if the credentials match an existing user in the Firebase database. If there is a match, the user is directed to the dashboard fragment. The login page also implements null safe exceptions such as the user not entering an email or password, or entering a string not in the form of an email. All of these exceptions result in a concise error message being returned at the bottom of the screen for the user

<img src="./images/login.png" alt="Login" height="600"><br>

2. [2500 Data Instances]. There must be data file(s) with at least 2,500 valid data instances **(easy)**
    * Classes/files utilised:
      * DataReader.java
      * LoginView.java
      * dataInstances.csv
    * 2500 user instances were added to Firebase Authentication and Firestore. These instances were auto-generated with first name, last name, and email information, stored in a CSV file named "dataInstances.csv" with comma-separated values. The DataReader class was used to read and parse the CSV file using BufferedReader. The parsed tokens were then processed in the createUserAndLogin method to create users and sync them with the Firestore database. The DataReader class was instantiated in the LoginView to generate all 2500 user instances and removed afterward. Additionally, each user was enrolled in a randomly selected COMP course from a pool of three courses, visible under the participants tab on the course page.

3. [Load data/information]. Users must be able to load data/information from Firebase and visualise it **(medium)**
    - Classes utilised: All ViewModel, View and Firebase related classes.
    - The app retrieves data from Firebase to display information across most of its activities and fragments. This data includes a user's course list, course participants, and their roles as either a student or teacher. The information is utilised across the application and is mainly displayed within the four primary fragments. This is performed through a ClassBinding variable within a View class, which can be set to utilise a ViewModel class. Hence, we can use our defined methods, getters/setters in our ViewModel class to return a user's information and display it on our application UI.

<img src="./images/loadinfo.png" alt="Load Data/Information" height="600"><br>

5. [Search by Parsers/Tokenisers]. Users must be able to search for information on your app. **(medium)**
    - To be added

<img src="./images/search.png" alt="Search by Parsers/Tokenisers" height="600"><br>


### General Features
Our application consists of 8 implemented features, consisting of 2 **hard**, 3 **medium**, and 3 **easy** features.

Feature Category: Firebase Integration <br>
1. [FB-Auth]. Use Firebase to implement User Authentication/Authorisation. **(easy)**
   * Class utilised:
      * database/FirebaseWaddleDatabaseServiceClient.java
      * database/WaddleDatabaseServiceClient.java
   * In the app, Firebase Authentication is utilised through the use of the FirebaseAuth object. When the app is launched, users are taken to the login page where they can either sign in with an existing account or register for a new account using Firebase. To create a new account, the user must fill in various textboxes with information, and then their email and password are passed into the "createUserWithEmailAndPassword" method. This creates a new user object in the firestore database. Once a user has an account, the "signInWithEmailAndPassword" method from Firebase Authentication is used to authenticate the user against the firestore database. If the user is verified, they are directed to MainActivity.java.

2. [FB-Persist]. Use Firebase to persist all data used in your app. **(medium)**
    * Classes utilised:
        * database/FirebaseWaddleDatabaseServiceClient.java
        * database/WaddleDatabaseServiceClient.java
    * Our data storage system relies on Firebase for various purposes, including the storage of user information, courses, and discussion posts. Each user and course stored in Firebase possesses numerous attributes, all of which can be accessed in the Firestore database. The primary keys associated with users and courses allow us to extract specific details of an individual user and display them on our app pages. Permissions are granted to users, enabling them to join existing courses or create new ones. Any updates related to courses, such as joining by a student or creation by a teacher, are promptly reflected in Firestore. For instance, when a student joins a course, it is added to their list of courses in their attributes. Similarly, if a teacher adds a course, it is included in their courses attributes, and a new course entry is created in Firestore.

3. [FB-Syn]. Using Firebase or another remote database to store user information and having the app updated as the remote database is updated **(hard)**
    * Classes utilised:
        * database/FirebaseWaddleDatabaseServiceClient.java
        * database/WaddleDatabaseServiceClient.java
        * view/Dashboard/DashboardFragment.java
        * view/CoursePage.java
        * viewmodel/UserViewModel.java
        * UserDto.java
        * viewmodel/MessagesViewModel.java
    * The UserViewModel.java class has a CustomOnCompleteListener attached to the fetchUserCourseDetails method. This method, in turn, calls the synchCourses method in FirebaseWaddleDatabaseServiceClient.java. The synchCourses method updates the user's course list whenever a change in their courses is detected. The listener adds the queried course to the courseList, which is then automatically displayed in the user's Dashboard with an updated list of courses. A similar process was developed for discussion board messages, where the syncDiscussions method updates the displayed discussions and posts and syncs the user's discussion board tab in a course page without requiring a page reload. The database also has a real time listener on data change which then sends a notification to the userDto to generate a nofication message which is then used by MessagesViewModel to display that message. 

Feature Category: Creating Processes <br>
1. [Process-Permission]. Only users with permission can have certain permissions in the app. **(easy)**
    * Classes utilised:
        * UserViewModel.java
        * DashboardFragment.java
    * Within the UserViewModel.java class, a boolean variable isStudent is used to indicate whether the user is a student or not by using the instanceof keyword with StudentDto and TeacherDto. The permission of certain users is implemented by an OnClickListener within a lambda function setExpandButton which is executed on the click of the expandable button. For a teacher, both create class and join class options will be displayed, but when the student attempts to expand the button, only the join class button will display.

<img src="./images/processpermission.png" alt="Process-Permission" height="600"><br>

Feature Category: Search-related features <br>
1. [Search-Invalid]. Search functionality can handle partially valid and invalid search queries. **(medium)**
    * Classes utilised:
      * FirebaseWaddleDatabaseServiceClient.java
      * ListViewAdapter.java
      * UsersRecyclerViewAdapter.java
      * CourseRecyclerViewAdapter.java
      * SearchFragment.java
    * The Firestore Database allows users to search for other users and courses. The search functionality accommodates partially valid queries and returns the most accurate results based on the given query. For example, searching for "COMP" under the classes section will retrieve all courses that contain the substring "COMP". This search is accomplished using the fetchAllUsersForSearch method, which breaks down the user's input string into tokens and analyses it against the attributes of courses and users. It compares each attribute and returns the matching queries.

<img src="./images/invalidsearch.png" alt="Search-Invalid" height="600"><br>

Feature Category: User Interactivity <br>
1. [Interact-Follow]. The ability to ‘follow’ a course or any specific items. **(medium)**
    * Classes utilised:
        * view/Dashboard/DashboardFragment.java
        * viewmodel/UserViewModel.java
        * database/FirebaseWaddleDatabaseServiceClient
    * Both teachers and students have the option to join classes by either entering a class code using the expandable button or manually searching for a course through the app's search function. When a user tries to join a course, the joinCourse method in UserViewModel.java is executed, adding the queried course to the user's list of courses. The updated list of courses is then displayed in DashboardFragment.java, where the user can click on each course to access its content, view participants, and access discussion forums.

<img src="./images/interactfollow.png" alt="Interact-Follow" height="600"><br>

Feature Category: Greater Data Usage, Handling and Sophistication <br>
1. [Data-Profile] User profile or Course material activity containing a media file (image, animation (e.g. gif), video). **(easy)**
   * Classes utilised:
     * activity_user_page.xml
     * profile_fragment.xml
   * When accessing your own or another user's profile, an aesthetically pleasing background is associated with the activity, enhancing the visual presentation of the information displayed. This captivating background serves as a backdrop while showcasing the comprehensive details of a particular user, encompassing their full name, email address, and their status as a student or teacher. Additionally, the profile furnishes a comprehensive list of the courses in which the user is currently enrolled. This thoughtful design element not only enhances the user experience but also adds a touch of personalisation and visual appeal to the profile-viewing process.

<img src="./images/dataprofile.png" alt="Data Profile" height="600"><br>

Feature Category: UI Design and Testing <br>
1. [UI-Test]. UI tests using espresso or similar. **(hard)**
   * Classes utilised:
     * DashboardTests.java
     * LoginViewTests.java
   * These tests open the Android emulator in the IDE and press various buttons across the UI to make assertions against the true functionality of the app, as it would appear to the user, rather than just asserting against the logic that may or may not have been bridged correctly to the real-life observer.


## Team Meetings

- *[Team Meeting 1](./meeting1.md)*
- *[Team Meeting 2](./meeting2.md)*
- *[Team Meeting 3](./meeting3.md)*
- *[Team Meeting 4](./meeting4.md)*
- *[Team Meeting 5](./meeting5.md)*
- *[Team Meeting 6](./meeting6.md)*
