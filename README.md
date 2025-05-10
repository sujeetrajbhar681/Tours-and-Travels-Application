# Travel Management System

## Project Description
The Travel Management System is a desktop application developed in Java using the Swing framework. It provides a user-friendly interface for managing travel bookings, including hotel and package bookings, user authentication, and administrative functionalities. The system connects to a MySQL database to store and retrieve user and booking information.

## Features
- User login and signup with role-based access (Admin and User)
- Password recovery functionality
- Admin dashboard for managing customers, bookings, and packages
- User dashboard for viewing and booking hotels and travel packages
- Hotel and package booking management
- Payment integration (including Paytm)
- View and update customer details
- View booked hotels and packages
- Responsive and intuitive GUI with icons and images

## Technologies Used
- Java SE (Swing for GUI)
- MySQL Database
- JDBC for database connectivity
- NetBeans IDE (project structure and build files)

## Project Structure
```
src/
 └── travel/management/system/       # Java source files for different modules
     ├── Conn.java                   # Database connection class
     ├── Login.java                  # Login GUI and logic
     ├── Signup.java                 # Signup GUI and logic
     ├── AdminPage.java              # Admin dashboard
     ├── Dashboard.java              # User dashboard
     ├── BookHotel.java              # Hotel booking module
     ├── BookPackage.java            # Package booking module
     ├── Payment.java                # Payment processing
     ├── Paytm.java                  # Paytm payment integration
     ├── ForgetPassword.java         # Password recovery
     ├── UpdateCustomer.java         # Update customer details
     ├── ViewBookedHotel.java        # View booked hotels
     ├── ViewPackage.java            # View travel packages
     └── ...                        # Other related classes
build/                              # Compiled classes and resources
icons/                              # Icons and images used in the GUI
nbproject/                         # NetBeans project configuration files
```

## Setup and Installation
1. Install Java Development Kit (JDK) 8 or higher.
2. Install MySQL Server and create a database named `travelmanagementsystem`.
3. Update the database username and password in `src/travel/management/system/Conn.java` if necessary.
4. Import the project into NetBeans IDE.
5. Build the project to compile the source code.
6. Run the `Login` class to start the application.

## Database
The application uses a MySQL database named `travelmanagementsystem`. Ensure the database is running and accessible. The connection details are configured in the `Conn` class.

## Running the Application
- Launch the application by running the `Login` class.
- Use the login screen to sign in or sign up as a new user.
- Admin users have access to administrative features, while regular users can book hotels and packages.

## Author
This project was developed as a Travel Management System desktop application using Java Swing and MySQL.
