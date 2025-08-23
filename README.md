# Employee Management System (EMS 3.0)

A comprehensive Java Swing-based Employee Management System with MySQL database integration for managing employee records efficiently.

## 📋 Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Database Setup](#database-setup)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

- **User Authentication**: Secure login system with username and password validation
- **Employee Management**: Complete CRUD operations for employee records
  - ➕ Add new employees
  - 👀 View employee records in a table format
  - ✏️ Update existing employee information
  - 🗑️ Delete employee records
- **Search Functionality**: Search employees by ID
- **Data Export**: Print employee records
- **User-Friendly GUI**: Clean and intuitive Swing-based interface
- **Database Integration**: MySQL database for persistent data storage
- **Data Validation**: Input validation and error handling

## 🛠 Technologies Used

- **Programming Language**: Java (JDK 24)
- **GUI Framework**: Java Swing
- **Database**: MySQL
- **Build Tool**: Apache Ant (NetBeans project)
- **IDE**: NetBeans IDE

### Libraries & Dependencies
- `mysql-connector-java-8.0.28.jar` - MySQL JDBC driver
- `jcalendar-tz-1.3.3-4.jar` - Date picker component
- `rs2xml.jar` - ResultSet to XML conversion utility

## 📋 Prerequisites

Before running this application, ensure you have:

- Java Development Kit (JDK) 8 or higher installed
- MySQL Server installed and running
- NetBeans IDE (recommended) or any Java IDE
- MySQL JDBC Driver (included in project)

## 🚀 Installation & Setup

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd EMS_3.0
   ```

2. **Open in NetBeans**
   - Open NetBeans IDE
   - File → Open Project
   - Navigate to the `EmployeemanagementSystem` folder
   - Click "Open Project"

3. **Build the Project**
   - Right-click on the project in NetBeans
   - Select "Clean and Build"

## 🗄️ Database Setup

1. **Create MySQL Database**
   ```sql
   CREATE DATABASE ems2.0;
   USE ems2.0;
   ```

2. **Create Employee Table**
   ```sql
   CREATE TABLE employee (
       empId VARCHAR(10) PRIMARY KEY,
       name VARCHAR(50) NOT NULL,
       fname VARCHAR(50) NOT NULL,
       dob DATE,
       salary DECIMAL(10,2),
       address TEXT,
       phone VARCHAR(15),
       designation VARCHAR(50),
       education VARCHAR(50),
       nid VARCHAR(20)
   );
   ```

3. **Create Admin/User Table (for login)**
   ```sql
   CREATE TABLE login (
       username VARCHAR(50) PRIMARY KEY,
       password VARCHAR(255) NOT NULL
   );
   
   -- Insert default admin user
   INSERT INTO login (username, password) VALUES ('admin', 'admin123');
   ```

4. **Configure Database Connection**
   - Open `src/employeemanagementsystem/Conn.java`
   - Update the database URL, username, and password if needed:
   ```java
   c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems2.0", "root", "");
   ```

## 📖 Usage

1. **Run the Application**
   - In NetBeans: Right-click project → Run
   - Or run the JAR file: `java -jar dist/EmployeemanagementSystem.jar`

2. **Login**
   - Use default credentials: `admin` / `admin123`
   - Or create new login credentials in the database

3. **Navigate the System**
   - **Dashboard**: Main menu with options to add, view, update, or delete employees
   - **Add Employee**: Fill in the form to add new employee records
   - **View Employees**: Browse all employees in a table format
   - **Update Employee**: Search by ID and modify employee information
   - **Delete Employee**: Remove employee records from the system

## 📁 Project Structure

```
EMS_3.0/
├── EmployeemanagementSystem/
│   ├── src/
│   │   └── employeemanagementsystem/
│   │       ├── Addemployee.java      # Add employee functionality
│   │       ├── Conn.java             # Database connection
│   │       ├── Home.java             # Dashboard/Main menu
│   │       ├── Login.java            # Login functionality
│   │       ├── RemoveEmployee.java   # Delete employee functionality
│   │       ├── Start.java            # Welcome screen
│   │       ├── UpdateEmployee.java   # Update employee functionality
│   │       └── ViewEmployee.java     # View employees functionality
│   ├── build/                        # Compiled classes
│   ├── dist/                         # Distribution files
│   ├── nbproject/                    # NetBeans project files
│   ├── jcalendar-tz-1.3.3-4.jar    # Date picker library
│   ├── mysql-connector-java-8.0.28.jar # MySQL JDBC driver
│   ├── rs2xml.jar                   # ResultSet to XML utility
│   └── build.xml                    # Ant build file
├── LICENSE                          # Apache License 2.0
└── README.md                        # This file
```

## 🖼️ Screenshots

*Note: Add screenshots of your application here*

- Login Screen
- Dashboard/Home Screen
- Add Employee Form
- View Employees Table
- Update Employee Form

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Raiyan** - *Developer*

## 🐛 Known Issues

- Main class configuration needs to be updated in project properties
- Password hashing should be implemented for better security
- Input validation can be enhanced

## 🔮 Future Enhancements

- [ ] Add role-based access control
- [ ] Implement password hashing
- [ ] Add employee photo upload
- [ ] Generate employee reports
- [ ] Add backup and restore functionality
- [ ] Implement email notifications
- [ ] Add dark mode theme
- [ ] Create REST API for mobile integration

## 💡 Support

If you encounter any issues or have questions:
1. Check the [Issues](../../issues) section
2. Create a new issue with detailed description
3. Contact the developer

---
**Happy Coding!** 🎉
