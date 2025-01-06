# Project Management System

## Overview

This Project Management System is a Java-based application designed to manage projects, tasks, and users within an organization. It provides functionalities for different roles such as Admin, Project Manager, Team Leader, and Employee.

## Features

- **User Management**: Admin can add, update, and delete users.
- **Project Management**: Project Managers can create, update, and delete projects.
- **Task Management**: Team Leaders can assign tasks to employees and manage their statuses.
- **Vacation Requests**: Employees can request vacations, and Team Leaders can approve or reject them.
- **Penalty Management**: Team Leaders can add penalties to employees.
- **Work Hours Tracking**: Employees can log their work hours.

## Technologies Used

- **Java**: Core programming language.
- **Swing**: For building the graphical user interface.
- **Gson**: For JSON serialization and deserialization.
- **Maven**: For project management and dependency management.

## Project Structure

```
ProjectManagementSystem/
├── data/
│   ├── EmployeeReport.json
│   ├── Project.json
│   ├── Task.json
│   ├── User.json
├── documentation/
│   ├── FileManager.html
│   ├── FileManager.txt
├── src/
│   ├── Classes/
│   ├── Enum/
│   ├── GUI/
│   ├── Test/
│   ├── Utils/
│   ├── Main.java
├── pom.xml
├── .gitignore
├── readme.md
```

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Maven 3.6.0 or higher

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/ProjectManagementSystem.git
    ```
2. Navigate to the project directory:
    ```sh
    cd ProjectManagementSystem
    ```
3. Build the project using Maven:
    ```sh
    mvn clean install
    ```

### Running the Application

1. Navigate to the `src` directory:
    ```sh
    cd src
    ```
2. Run the `Main` class:
    ```sh
    java Main
    ```

## Usage

- **Login**: Users can log in using their email and password.
- **Admin Panel**: Admins can manage users and view all projects.
- **Project Manager Dashboard**: Project Managers can manage projects and generate team reports.
- **Team Leader Page**: Team Leaders can assign tasks, manage employees, and handle vacation requests.
- **Employee Page**: Employees can log work hours, view penalties, request vacations, and check tasks.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License

This project is licensed under the MIT License.

## Contact

For any inquiries, please contact osama.abdullah.2c@gmail.com.
