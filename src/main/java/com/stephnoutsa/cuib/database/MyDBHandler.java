/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stephnoutsa.cuib.database;

import com.stephnoutsa.cuib.model.Course;
import com.stephnoutsa.cuib.model.Lecturer;
import com.stephnoutsa.cuib.model.Message;
import com.stephnoutsa.cuib.model.Payment;
import com.stephnoutsa.cuib.model.Results;
import com.stephnoutsa.cuib.model.Student;
import com.stephnoutsa.cuib.model.Timetable;
import com.stephnoutsa.cuib.model.Token;
import com.stephnoutsa.cuib.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author stephnoutsa
 */
public class MyDBHandler {
    
    public static final String DATABASE_NAME = "cuib";
    
    public static final String TABLE_STUDENTS = "STUDENTS";
    public static final String STUDENT_COLUMN_ID = "ID";
    public static final String STUDENT_COLUMN_NAME = "NAME";
    public static final String STUDENT_COLUMN_MAT = "MATRICULE";
    public static final String STUDENT_COLUMN_YEAR = "ENROLLED";
    public static final String STUDENT_COLUMN_LEVEL = "LEVEL";
    public static final String STUDENT_COLUMN_SCHOOL = "SCHOOL";
    public static final String STUDENT_COLUMN_DEPT = "DEPARTMENT";
    public static final String STUDENT_COLUMN_EMAIL = "EMAIL";
    public static final String STUDENT_COLUMN_PHONE = "PHONE";
    public static final String STUDENT_COLUMN_PASSWORD = "PASSWORD";
    
    public static final String TABLE_USERS = "USERS";
    public static final String USER_COLUMN_ID = "ID";
    public static final String USER_COLUMN_EMAIL = "EMAIL";
    public static final String USER_COLUMN_PHONE = "PHONE";
    public static final String USER_COLUMN_DOB = "DOB";
    public static final String USER_COLUMN_GENDER = "GENDER";
    public static final String USER_COLUMN_PASSWORD = "PASSWORD";
    public static final String USER_COLUMN_ROLE = "ROLE";
    
    public static final String TABLE_TIMETABLE = "TIMETABLES";
    public static final String TT_COLUMN_ID = "ID";
    public static final String TT_COLUMN_SCHOOL = "SCHOOL";
    public static final String TT_COLUMN_DEPT = "DEPARTMENT";
    public static final String TT_COLUMN_LEVEL = "LEVEL";
    public static final String TT_COLUMN_URL = "URL";
    
    public static final String TABLE_COURSES = "COURSES";
    public static final String COURSE_COLUMN_ID = "ID";
    public static final String COURSE_COLUMN_CODE = "CODE";
    public static final String COURSE_COLUMN_NAME = "NAME";
    public static final String COURSE_COLUMN_DESC = "DESCRIPTION";
    public static final String COURSE_COLUMN_SCHOOLS = "SCHOOLS";
    public static final String COURSE_COLUMN_DEPTS = "DEPARTMENTS";
    public static final String COURSE_COLUMN_LEVELS = "LEVELS";
    
    public static final String TABLE_LECTURERS = "LECTURERS";
    public static final String LEC_COLUMN_ID = "ID";
    public static final String LEC_COLUMN_NAME = "NAME";
    public static final String LEC_COLUMN_AVATAR = "AVATAR";
    public static final String LEC_COLUMN_BIO = "BIO";
    public static final String LEC_COLUMN_DEPTS = "DEPARTMENTS";
    public static final String LEC_COLUMN_LEVELS = "LEVELS";
    
    public static final String TABLE_MSGS = "MESSAGES";
    public static final String MSG_COLUMN_ID = "ID";
    public static final String MSG_COLUMN_TITLE = "TITLE";
    public static final String MSG_COLUMN_BODY = "BODY";
    public static final String MSG_COLUMN_TIME = "TIME";
    
    public static final String TABLE_TOKENS = "TOKENS";
    public static final String TOK_COLUMN_ID = "ID";
    public static final String TOK_COLUMN_VALUE = "VALUE";
    public static final String TOK_COLUMN_SCHOOL = "SCHOOL";
    public static final String TOK_COLUMN_DEPT = "DEPARTMENT";
    public static final String TOK_COLUMN_LVL = "LEVEL";
    
    public static final String TABLE_RESULTS = "RESULTS";
    public static final String RES_COLUMN_ID = "ID";
    public static final String RES_COLUMN_YEAR = "ACADEMIC_YEAR";
    public static final String RES_COLUMN_SEMESTER = "SEMESTER";
    public static final String RES_COLUMN_MATRICULE = "MATRICULE";
    public static final String RES_COLUMN_URL = "URL";
    
    public static final String TABLE_PAYMENTS = "PAYMENTS";
    public static final String PAY_COLUMN_ID = "ID";
    public static final String PAY_COLUMN_DATE = "DATE";
    public static final String PAY_COLUMN_AMT = "AMOUNT";
    public static final String PAY_COLUMN_SCHOOL = "SCHOOL";
    
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";  
    static final String DB_URL = "jdbc:derby://localhost:1527/" + DATABASE_NAME;
    
    // Database credentials
    static final String USER = "root";
    static final String PASS = "b2kline";
    
    // Main method
    public static void main(String[] args) {
               
    }
    
    // Add a new student to the Students table
    public Student addStudent(Student student) {
        Student s = studentExists(student);
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            if (s.getName().equals("null") && s.getMatricule().equals("null")) {
                String query = "INSERT INTO " + TABLE_STUDENTS + "(" + STUDENT_COLUMN_NAME + ", "
                        + STUDENT_COLUMN_MAT + ", " + STUDENT_COLUMN_YEAR + ", "
                        + STUDENT_COLUMN_LEVEL + ", " + STUDENT_COLUMN_SCHOOL + ", "
                        + STUDENT_COLUMN_DEPT + ", " + STUDENT_COLUMN_EMAIL + ", "
                        + STUDENT_COLUMN_PHONE + ", " + STUDENT_COLUMN_PASSWORD + ") VALUES(\'"
                        + student.getName() + "\', \'" + student.getMatricule() + "\', \'"
                        + student.getEnrolled() + "\', \'" + student.getLevel() + "\', \'"
                        + student.getSchool() + "\', \'" + student.getDepartment() + "\', \'"
                        + student.getEmail() + "\', \'" + student.getPhone() + "\', \'"
                        + student.getPassword() + "\')";
                System.out.println("Student ID: " + student.getId());
                db.executeUpdate(query);
            }            
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
               if (conn != null)
                  conn.close();
            } catch (SQLException se) {
               se.printStackTrace();
            }
        }
        
        return s;
    }
    
    // Check if student exists
    public Student studentExists(Student student) {
        Student newStudent = new Student();
        
        String matricule = student.getMatricule();
        String password = student.getPassword();
        
        Connection conn = null;
        Statement db = null;
             
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_STUDENTS + " WHERE "
                    + STUDENT_COLUMN_MAT + " = \'" + matricule + "\' AND "
                    + STUDENT_COLUMN_PASSWORD + " = \'" + password + "\'";
            ResultSet result = db.executeQuery(query);
            
            if (result.next()) {
                System.out.println("Student exists");
                
                newStudent.setName(result.getString(STUDENT_COLUMN_NAME));
                newStudent.setMatricule(matricule);
                newStudent.setEnrolled(result.getString(STUDENT_COLUMN_YEAR));
                newStudent.setLevel(result.getString(STUDENT_COLUMN_LEVEL));
                newStudent.setSchool(result.getString(STUDENT_COLUMN_SCHOOL));
                newStudent.setDepartment(result.getString(STUDENT_COLUMN_DEPT));
                newStudent.setEmail(result.getString(STUDENT_COLUMN_EMAIL));
                newStudent.setPhone(result.getString(STUDENT_COLUMN_PHONE));
                newStudent.setPassword(password);
            } else {
                System.out.println("Student does not exist");
                
                newStudent.setName("null");
                newStudent.setMatricule("null");
                newStudent.setEnrolled("null");
                newStudent.setLevel("null");
                newStudent.setSchool("null");
                newStudent.setDepartment("null");
                newStudent.setEmail("null");
                newStudent.setPhone("null");
                newStudent.setPassword("null");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
               if (conn != null)
                  conn.close();
            } catch (SQLException se) {
               se.printStackTrace();
            }
        }
        
        return newStudent;
    }
    
    // Update a student in the Students table
    public Student updateStudent(int id, Student student) {
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "UPDATE " + TABLE_STUDENTS + " SET " +
                    STUDENT_COLUMN_NAME + "=\'" + student.getName() + "\', " +
                    STUDENT_COLUMN_YEAR + "=\'" + student.getEnrolled() + "\', " +
                    STUDENT_COLUMN_LEVEL + "=\'" + student.getLevel() + "\', " +
                    STUDENT_COLUMN_SCHOOL + "=\'" + student.getSchool() + "\', " +
                    STUDENT_COLUMN_DEPT + "=\'" + student.getDepartment() + "\', " +
                    STUDENT_COLUMN_EMAIL + "=\'" + student.getEmail() + "\', " +
                    STUDENT_COLUMN_PHONE + "=\'" + student.getPhone() + "\' " +
                    //STUDENT_COLUMN_PASSWORD + "=\'" + student.getPassword() + "\' " +
                    "WHERE " + STUDENT_COLUMN_ID + "=" + id;
            db.executeUpdate(query);
            System.out.println("Student updated");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return student;
    }
    
    // Delete student from Students table
    public void deleteStudent(int id) {
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "DELETE FROM " + TABLE_STUDENTS + " WHERE "
                    + STUDENT_COLUMN_ID + "=" + id;
            db.executeUpdate(query);
            System.out.println("Student deleted");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
    // Get single student from Students table
    public Student getStudent(int id) {
        Student student = new Student();
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_STUDENTS + " WHERE "
                    + STUDENT_COLUMN_ID + " =" + id;
            ResultSet result = db.executeQuery(query);
            
            if (result.next()) {
                student.setId(id);
                student.setName(result.getString(STUDENT_COLUMN_NAME));
                student.setMatricule(result.getString(STUDENT_COLUMN_MAT));
                student.setEnrolled(result.getString(STUDENT_COLUMN_YEAR));
                student.setLevel(result.getString(STUDENT_COLUMN_LEVEL));
                student.setSchool(result.getString(STUDENT_COLUMN_SCHOOL));
                student.setDepartment(result.getString(STUDENT_COLUMN_DEPT));
                student.setEmail(result.getString(STUDENT_COLUMN_EMAIL));
                student.setPhone(result.getString(STUDENT_COLUMN_PHONE));
                student.setPassword(result.getString(STUDENT_COLUMN_PASSWORD));
            }
            System.out.println("Single student delivered");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return student;
    }
    
    // Get all students from Students table
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_STUDENTS;
            ResultSet result = db.executeQuery(query);
            
            while(result.next()) {
                Student student = new Student();
                student.setId(result.getInt(STUDENT_COLUMN_ID));
                student.setName(result.getString(STUDENT_COLUMN_NAME));
                student.setMatricule(result.getString(STUDENT_COLUMN_MAT));
                student.setEnrolled(result.getString(STUDENT_COLUMN_YEAR));
                student.setLevel(result.getString(STUDENT_COLUMN_LEVEL));
                student.setSchool(result.getString(STUDENT_COLUMN_SCHOOL));
                student.setDepartment(result.getString(STUDENT_COLUMN_DEPT));
                student.setEmail(result.getString(STUDENT_COLUMN_EMAIL));
                student.setPhone(result.getString(STUDENT_COLUMN_PHONE));
                student.setPassword(result.getString(STUDENT_COLUMN_PASSWORD));
                
                studentList.add(student);
            }
            System.out.println("List of students delivered");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return studentList;
    }
    
    // Get students from Students table
    public Student[] getStudents(String dept, String level) {
        List<Student> studentList = new ArrayList<>();
        Student[] students = null;
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_STUDENTS + " WHERE "
                    + STUDENT_COLUMN_DEPT + " LIKE \'%" + dept + "%\' AND "
                    + STUDENT_COLUMN_LEVEL + " LIKE \'%" + level + "%\'";
            
            ResultSet result = db.executeQuery(query);
            
            while (result.next()) {
                Student s = new Student();
                
                s.setId(result.getInt(STUDENT_COLUMN_ID));
                s.setName(result.getString(STUDENT_COLUMN_NAME));
                s.setMatricule(result.getString(STUDENT_COLUMN_MAT));
                s.setEnrolled(result.getString(STUDENT_COLUMN_YEAR));
                s.setLevel(result.getString(STUDENT_COLUMN_LEVEL));
                s.setSchool(result.getString(STUDENT_COLUMN_SCHOOL));
                s.setDepartment(result.getString(STUDENT_COLUMN_DEPT));
                s.setEmail(result.getString(STUDENT_COLUMN_EMAIL));
                s.setPhone(result.getString(STUDENT_COLUMN_PHONE));
                s.setPassword(result.getString(STUDENT_COLUMN_PASSWORD));
                
                studentList.add(s);
            }
            
            System.out.println("List of students delivered");
            
            students = new Student[studentList.size()];
            
            for (int i = 0; i < studentList.size(); i++) {
                students[i] = studentList.get(i);
            }            
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return students;
    }
    
    // Add a new user, ensuring the same doesn't already exist
    public User onlyAddUser(User user) {
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            if (userExists(user.getEmail(), user)) {
                return new User("null", "null", "null", "null", "null", "null");
            } else {
                addUser(user);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return user;
    }
    
    // Simply add a new user to the Users table
    public User addUser(User user) {
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "INSERT INTO " + TABLE_USERS + "(" + USER_COLUMN_EMAIL + ", "
                    + USER_COLUMN_PHONE + ", " + USER_COLUMN_DOB + ", " + USER_COLUMN_GENDER
                    + ", " + USER_COLUMN_PASSWORD + ", " + USER_COLUMN_ROLE + ") VALUES(\'"
                    + user.getEmail() + "\', \'" + user.getPhone() + "\', \'" + user.getDob()
                    + "\', \'" + user.getGender() + "\', \'" + user.getPassword()
                    + "\', \'" + user.getRole() + "\')";
            
            db.executeUpdate(query);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return user;
    }
    
    // Check if user exists in Users table
    public boolean userExists(String email, User user) {
        boolean exists = false;
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_USERS + " WHERE "
                    + USER_COLUMN_EMAIL + " = \'" + email + "\'";
            ResultSet result = db.executeQuery(query);
            
            if (result.next()) {
                System.out.println("User exists");                
                exists = true;
            } else {
                System.out.println("User does not exist");               
                exists = false;
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            }catch (SQLException se) {
                se.printStackTrace();
            }
            try {
               if (conn != null)
                  conn.close();
            } catch (SQLException se) {
               se.printStackTrace();
            }            
        }
        
        return exists;
    }
    
    // Check if user exists in Users table
    public User loginCheck(String email, String password) {
        User newUser = new User();
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_USERS + " WHERE "
                    + USER_COLUMN_EMAIL + " = \'" + email + "\' AND "
                    + USER_COLUMN_PASSWORD + " = \'" + password + "\'";
            ResultSet result = db.executeQuery(query);
            
            if (result.next()) {
                System.out.println("User exists");
                
                newUser.setEmail(email);
                newUser.setPhone(result.getString(USER_COLUMN_PHONE));
                newUser.setDob(result.getString(USER_COLUMN_DOB));
                newUser.setGender(result.getString(USER_COLUMN_GENDER));
                newUser.setPassword(password);
                newUser.setRole(result.getString(USER_COLUMN_ROLE));
            } else {
                System.out.println("User does not exist");
                
                newUser.setEmail("null");
                newUser.setPhone("null");
                newUser.setDob("null");
                newUser.setGender("null");
                newUser.setPassword("null");
                newUser.setRole("null");
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            }catch (SQLException se) {
                se.printStackTrace();
            }
            try {
               if (conn != null)
                  conn.close();
            } catch (SQLException se) {
               se.printStackTrace();
            }            
        }
        
        return newUser;
    }
    
    // Update a user in the Users table
    public User updateUser(int id, User user) {
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "UPDATE " + TABLE_USERS + " SET " +
                USER_COLUMN_EMAIL + "=\'" + user.getEmail() + "\', " +
                USER_COLUMN_PHONE + "=\'" + user.getPhone() + "\', " +
                USER_COLUMN_DOB + "=\'" + user.getDob() + "\', " +
                USER_COLUMN_GENDER + "=\'" + user.getGender() + "\', " +
                USER_COLUMN_PASSWORD + "=\'" + user.getPassword() + "\', " +
                USER_COLUMN_ROLE + "=\'" + user.getRole() +
                "\' WHERE " + USER_COLUMN_ID + "=" + id;

            db.executeUpdate(query);
            System.out.println("User updated");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return user;
    }
    
    // Update an app user in the Users table
    public User updateAppUser(String email, User user) {
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            if (userExists(email, user)) {
                String query = "UPDATE " + TABLE_USERS + " SET " +
                    USER_COLUMN_EMAIL + "=\'" + user.getEmail() + "\', " +
                    USER_COLUMN_PHONE + "=\'" + user.getPhone() + "\', " +
                    USER_COLUMN_DOB + "=\'" + user.getDob() + "\', " +
                    USER_COLUMN_GENDER + "=\'" + user.getGender() + "\', " +
                    USER_COLUMN_PASSWORD + "=\'" + user.getPassword() + "\', " +
                    USER_COLUMN_ROLE + "=\'" + user.getRole() +
                    "\' WHERE " + USER_COLUMN_EMAIL + "=\'" + email + "\'";
            
                db.executeUpdate(query);
            } else {
                addUser(user);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return user;
    }
    
    // Delete user from Users table
    public void deleteUser(int id) {
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "DELETE FROM " + TABLE_USERS + " WHERE "
                    + USER_COLUMN_ID + "=" + id;
            
            db.executeUpdate(query);
            System.out.println("User deleted");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
    // Get single user from Users table
    public User getUser(int id) {
        User user = new User();
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_USERS + " WHERE "
                    + USER_COLUMN_ID + " =" + id;
            
            ResultSet result = db.executeQuery(query);
            
            if (result.next()) {
                user.setId(id);
                user.setEmail(result.getString(USER_COLUMN_EMAIL));
                user.setPhone(result.getString(USER_COLUMN_PHONE));
                user.setDob(result.getString(USER_COLUMN_DOB));
                user.setGender(result.getString(USER_COLUMN_GENDER));
                user.setPassword(result.getString(USER_COLUMN_PASSWORD));
                user.setRole(result.getString(USER_COLUMN_ROLE));
            }
            System.out.println("User delivered");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return user;
    }
    
    // Get all users from Users table
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_USERS;
            ResultSet result = db.executeQuery(query);
            
            while(result.next()) {
                User user = new User();
                user.setId(result.getInt(USER_COLUMN_ID));
                user.setEmail(result.getString(USER_COLUMN_EMAIL));
                user.setPhone(result.getString(USER_COLUMN_PHONE));
                user.setDob(result.getString(USER_COLUMN_DOB));
                user.setGender(result.getString(USER_COLUMN_GENDER));
                user.setPassword(result.getString(USER_COLUMN_PASSWORD));
                user.setRole(result.getString(USER_COLUMN_ROLE));
                
                userList.add(user);
            }
            
            System.out.println("List of users delivered");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return userList;
    }
    
    // Get single timetable from Timetables table
    public Timetable getTimetable(String dept, String level) {
        Timetable t = new Timetable();
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_TIMETABLE + " WHERE "
                    + TT_COLUMN_DEPT + " LIKE \'%" + dept + "%\' AND "
                    + TT_COLUMN_LEVEL + " LIKE \'%" + level + "%\'";
            
            ResultSet result = db.executeQuery(query);
            
            if (result.next()) {
                System.out.println("Timetable exists");
                t.setId(result.getInt(TT_COLUMN_ID));
                t.setSchool(result.getString(TT_COLUMN_SCHOOL));
                t.setDepartment(dept);
                t.setLevel(level);
                t.setUrl(result.getString(TT_COLUMN_URL));
            }
            System.out.println("Timetable delivered");
            System.out.println("Timetable URL: " + t.getUrl());
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return t;
    }
    
    // Check if timetable exists in Timetables table
    public boolean ttExists(String dept, String level) {
        boolean ok = false;
        
        Timetable t = new Timetable();
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_TIMETABLE + " WHERE "
                    + TT_COLUMN_DEPT + " =\'" + dept + "\' AND "
                    + TT_COLUMN_LEVEL + " =\'" + level + "\'";
            
            ResultSet result = db.executeQuery(query);
            
            if (result.next()) {
                ok = true;
                System.out.println("Timetable exists");
            } else {
                ok = false;
                System.out.println("Timetable does not exist");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return ok;
    }
    
    // Add timetable to Timetables table
    public Timetable addTimetable(Timetable timetable) {
        Timetable t = new Timetable();
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            if (ttExists(timetable.getDepartment(), timetable.getLevel())) {
                t = timetable;
            } else {
                String query = "INSERT INTO " + TABLE_TIMETABLE + "(" + TT_COLUMN_SCHOOL + ", "
                        + TT_COLUMN_DEPT + ", " + TT_COLUMN_LEVEL + ", "
                        + TT_COLUMN_URL + ") VALUES(\'"
                        + timetable.getSchool() + "\', \'" + timetable.getDepartment() + "\', \'"
                        + timetable.getLevel() + "\', \'" + timetable.getUrl() + "\')";
                System.out.println("Timetable ID: " + timetable.getId());
                
                db.executeUpdate(query);
                
                t = new Timetable("null", "null", "null", "null");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
               if (conn != null)
                  conn.close();
            } catch (SQLException se) {
               se.printStackTrace();
            }
        }
        
        return t;
    }
    
    // Add course to Courses table
    public Course addCourse(Course course) {
        Course c = new Course();
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            if (courseExists(course.getCode())) {
                c = course;
            } else {
                String query = "INSERT INTO " + TABLE_COURSES + "(" + COURSE_COLUMN_CODE + ", "
                        + COURSE_COLUMN_NAME + ", " + COURSE_COLUMN_DESC + ", "
                        + COURSE_COLUMN_SCHOOLS + ", " + COURSE_COLUMN_DEPTS + ", "
                        + COURSE_COLUMN_LEVELS + ") VALUES(\'"
                        + course.getCode() + "\', \'" + course.getName() + "\', \'"
                        + course.getDescription() + "\', \'" + arrayToString(course.getSchools()) + "\', \'"
                        + arrayToString(course.getDepartments()) + "\', \'"
                        + arrayToString(course.getLevels()) + "\')";
                System.out.println("Course ID: " + course.getId());
                
                db.executeUpdate(query);
                
                c = new Course("null", "null", "null", null, null, null);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
               if (conn != null)
                  conn.close();
            } catch (SQLException se) {
               se.printStackTrace();
            }
        }
        
        return c;
    }
    
    // Check if course exists in Course table
    public boolean courseExists(String code) {
        boolean exists = false;
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_COURSES + " WHERE "
                    + COURSE_COLUMN_CODE + " = \'" + code + "\'";
            ResultSet result = db.executeQuery(query);
            
            if (result.next()) {
                System.out.println("Course exists");                
                exists = true;
            } else {
                System.out.println("Course does not exist");               
                exists = false;
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            }catch (SQLException se) {
                se.printStackTrace();
            }
            try {
               if (conn != null)
                  conn.close();
            } catch (SQLException se) {
               se.printStackTrace();
            }            
        }
        
        return exists;
    }
    
    // Get courses from Courses table
    public Course[] getCourses(String dept, String level) {
        List<Course> courseList = new ArrayList<>();
        Course[] courses = null;
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_COURSES + " WHERE "
                    + COURSE_COLUMN_DEPTS + " LIKE \'%" + dept + "%\' AND "
                    + COURSE_COLUMN_LEVELS + " LIKE \'%" + level + "%\'";
            
            ResultSet result = db.executeQuery(query);
            
            while (result.next()) {
                Course c = new Course();
                
                c.setId(result.getInt(COURSE_COLUMN_ID));
                c.setCode(result.getString(COURSE_COLUMN_CODE));
                c.setName(result.getString(COURSE_COLUMN_NAME));
                c.setDescription(result.getString(COURSE_COLUMN_DESC));
                c.setSchools(stringToArray(result.getString(COURSE_COLUMN_SCHOOLS)));
                c.setDepartments(stringToArray(result.getString(COURSE_COLUMN_DEPTS)));
                c.setLevels(stringToArray(result.getString(COURSE_COLUMN_LEVELS)));
                
                courseList.add(c);
            }
            
            System.out.println("List of courses delivered");
            
            courses = new Course[courseList.size()];
            
            for (int i = 0; i < courseList.size(); i++) {
                courses[i] = courseList.get(i);
            }            
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return courses;
    }
    
    // Get single course from Courses table
    public Course getCourse(int id) {
        Course c = new Course();
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_COURSES + " WHERE "
                    + COURSE_COLUMN_ID + " =" + id;
            
            ResultSet result = db.executeQuery(query);
            
            if (result.next()) {
                c.setId(id);
                c.setCode(result.getString(COURSE_COLUMN_CODE));
                c.setName(result.getString(COURSE_COLUMN_NAME));
                c.setDescription(result.getString(COURSE_COLUMN_DESC));
                c.setSchools(stringToArray(result.getString(COURSE_COLUMN_SCHOOLS)));
                c.setDepartments(stringToArray(result.getString(COURSE_COLUMN_DEPTS)));
                c.setLevels(stringToArray(result.getString(COURSE_COLUMN_LEVELS)));
            }
            System.out.println("Single course delivered");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return c;
    }
    
    // Update course in Courses table
    public Course updateCourse(int id, Course course) {
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "UPDATE " + TABLE_COURSES + " SET " +
                COURSE_COLUMN_CODE + "=\'" + course.getCode() + "\', " +
                COURSE_COLUMN_NAME + "=\'" + course.getName() + "\', " +
                COURSE_COLUMN_DESC + "=\'" + course.getDescription() + "\', " +
                COURSE_COLUMN_SCHOOLS + "=\'" + arrayToString(course.getSchools()) + "\', " +
                COURSE_COLUMN_DEPTS + "=\'" + arrayToString(course.getDepartments()) + "\', " +
                COURSE_COLUMN_LEVELS + "=\'" + arrayToString(course.getLevels()) +
                "\' WHERE " + COURSE_COLUMN_ID + "=" + id;

            db.executeUpdate(query);
            System.out.println("Course updated");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return course;
    }
    
    // Delete course from Courses table
    public void deleteCourse(int id) {
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "DELETE FROM " + TABLE_COURSES + " WHERE "
                    + COURSE_COLUMN_ID + "=" + id;
            
            db.executeUpdate(query);
            System.out.println("Course deleted");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
    // Add lecturer to Lecturers table
    public Lecturer addLecturer(Lecturer lecturer) {        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "INSERT INTO " + TABLE_LECTURERS + "(" + LEC_COLUMN_NAME + ", "
                    + LEC_COLUMN_AVATAR + ", " + LEC_COLUMN_BIO + ", "
                    + LEC_COLUMN_DEPTS + ", " + LEC_COLUMN_LEVELS + ") VALUES(\'"
                    + lecturer.getName() + "\', \'" + lecturer.getAvatar() + "\', \'"
                    + lecturer.getBio() + "\', \'"
                    + arrayToString(lecturer.getDepartments()) + "\', \'"
                    + arrayToString(lecturer.getLevels()) + "\')";
            System.out.println("Lecturer ID: " + lecturer.getId());

            db.executeUpdate(query);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
               if (conn != null)
                  conn.close();
            } catch (SQLException se) {
               se.printStackTrace();
            }
        }
        
        return lecturer;
    }
    
    // Get all lecturers from Lecturers table
    public List<Lecturer> getAllLecturers() {
        List<Lecturer> lecturerList = new ArrayList<>();
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_LECTURERS;
            ResultSet result = db.executeQuery(query);
            
            while(result.next()) {
                Lecturer l = new Lecturer();
                
                l.setId(result.getInt(LEC_COLUMN_ID));
                l.setName(result.getString(LEC_COLUMN_NAME));
                l.setAvatar(result.getString(LEC_COLUMN_AVATAR));
                l.setBio(result.getString(LEC_COLUMN_BIO));
                l.setDepartments(stringToArray(result.getString(LEC_COLUMN_DEPTS)));
                l.setLevels(stringToArray(result.getString(LEC_COLUMN_LEVELS)));
                
                lecturerList.add(l);
            }
            System.out.println("All lecturers delivered");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return lecturerList;
    }
    
    // Get lecturers from Lecturers table
    public Lecturer[] getLecturers(String dept, String level) {
        List<Lecturer> lecturerList = new ArrayList<>();
        Lecturer[] lecturers = null;
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_LECTURERS + " WHERE "
                    + LEC_COLUMN_DEPTS + " LIKE \'%" + dept + "%\' AND "
                    + LEC_COLUMN_LEVELS + " LIKE \'%" + level + "%\'";
            
            ResultSet result = db.executeQuery(query);
            
            while (result.next()) {
                Lecturer l = new Lecturer();
                
                l.setId(result.getInt(LEC_COLUMN_ID));
                l.setName(result.getString(LEC_COLUMN_NAME));
                l.setAvatar(result.getString(LEC_COLUMN_AVATAR));
                l.setBio(result.getString(LEC_COLUMN_BIO));
                l.setDepartments(stringToArray(result.getString(LEC_COLUMN_DEPTS)));
                l.setLevels(stringToArray(result.getString(LEC_COLUMN_LEVELS)));
                
                lecturerList.add(l);
            }
            
            lecturers = new Lecturer[lecturerList.size()];
            
            for (int i = 0; i < lecturerList.size(); i++) {
                lecturers[i] = lecturerList.get(i);
            }
            System.out.println("Lecturers delivered");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return lecturers;
    }
    
    // Get single lecturer from Lecturers table
    public Lecturer getLecturer(int id) {
        Lecturer l = new Lecturer();
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_LECTURERS + " WHERE "
                    + LEC_COLUMN_ID + " =" + id;
            
            ResultSet result = db.executeQuery(query);
            
            if (result.next()) {
                l.setId(id);
                l.setName(result.getString(LEC_COLUMN_NAME));
                l.setAvatar(result.getString(LEC_COLUMN_AVATAR));
                l.setBio(result.getString(LEC_COLUMN_BIO));
                l.setDepartments(stringToArray(result.getString(LEC_COLUMN_DEPTS)));
                l.setLevels(stringToArray(result.getString(LEC_COLUMN_LEVELS)));
            }
                
            System.out.println("Lecturer delivered");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return l;
    }
    
    // Update lecturer in Lecturers table
    public Lecturer updateLecturer(int id, Lecturer lecturer) {
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "UPDATE " + TABLE_LECTURERS + " SET " +
                LEC_COLUMN_NAME + "=\'" + lecturer.getName() + "\', " +
                LEC_COLUMN_AVATAR + "=\'" + lecturer.getAvatar() + "\', " +
                LEC_COLUMN_BIO + "=\'" + lecturer.getBio() + "\', " +
                LEC_COLUMN_DEPTS + "=\'" + arrayToString(lecturer.getDepartments()) + "\', " +
                LEC_COLUMN_LEVELS + "=\'" + arrayToString(lecturer.getLevels()) +
                "\' WHERE " + LEC_COLUMN_ID + "=" + id;

            db.executeUpdate(query);
            System.out.println("Lecturer updated");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return lecturer;
    }
    
    // Delete lecturer from Lecturers table
    public void deleteLecturer(int id) {
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "DELETE FROM " + TABLE_LECTURERS + " WHERE "
                    + LEC_COLUMN_ID + "=" + id;
            
            db.executeUpdate(query);
            System.out.println("Lecturer deleted");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
    // Add message to Messages table
    public Message addMessage(Message message) {
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "INSERT INTO " + TABLE_MSGS + "(" + MSG_COLUMN_TITLE + ", "
                    + MSG_COLUMN_BODY + ", " + MSG_COLUMN_TIME +") VALUES(\'"
                    + message.getTitle() + "\', \'" + message.getBody() + "\', \'"
                    + message.getTime() + "\')";
            
            db.executeUpdate(query);
            
            System.out.println("Message added");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return message;
    }
    
    // Get all messages from Messages table
    public List<Message> getAllMessages() {
        List<Message> messageList = new ArrayList<>();
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_MSGS;
            ResultSet result = db.executeQuery(query);
            
            while(result.next()) {
                Message msg = new Message();
                
                msg.setId(result.getInt(MSG_COLUMN_ID));
                msg.setTitle(result.getString(MSG_COLUMN_TITLE));
                msg.setBody(result.getString(MSG_COLUMN_BODY));
                msg.setTime(result.getString(MSG_COLUMN_TIME));
                
                messageList.add(msg);
            }
            
            System.out.println("List of messages delivered");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        // Reverse the order of the messages
        Collections.reverse(messageList);
        
        return messageList;
    }
    
    // Get single message from Messages table
    public Message getMessage(int id) {
        Message m = new Message();
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_MSGS + " WHERE "
                    + MSG_COLUMN_ID + " =" + id;
            
            ResultSet result = db.executeQuery(query);
            
            if (result.next()) {
                m.setId(id);
                m.setTitle(result.getString(MSG_COLUMN_TITLE));
                m.setBody(result.getString(MSG_COLUMN_BODY));
                m.setTime(result.getString(MSG_COLUMN_TIME));
            }
                
            System.out.println("Message delivered");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return m;
    }
    
    // Delete message from Messages table
    public void deleteMessage(int id) {
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "DELETE FROM " + TABLE_MSGS + " WHERE "
                    + MSG_COLUMN_ID + "=" + id;
            
            db.executeUpdate(query);
            System.out.println("Message deleted");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
    // Add token to, or update token in Tokens table
    public Token addToken(String value, String school, String dept, String level) {
        Connection conn = null;
        Statement db = null;
        
        Token token = new Token();
        token.setValue(value);
        token.setSchool(school);
        token.setDepartment(dept);
        token.setLevel(level);
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            if (!tokenExists(value)) {
                String query = "INSERT INTO " + TABLE_TOKENS + "(" + TOK_COLUMN_VALUE +
                        ", " + TOK_COLUMN_SCHOOL + ", " + TOK_COLUMN_DEPT + ", " +
                        TOK_COLUMN_LVL + ") VALUES(\'" + value + "\', \'" + school + "\', \'" +
                        dept + "\', \'" + level + "\')";

                db.executeUpdate(query);
            } else {
                updateToken(value, school, dept, level);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return token;
    }
    
    // Check if token exists
    public boolean tokenExists(String value) {
        boolean exists = false;
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_TOKENS + " WHERE "
                    + TOK_COLUMN_VALUE + " = \'" + value + "\'";
            ResultSet result = db.executeQuery(query);
            
            if (result.next()) {
                System.out.println("Token exists");                
                exists = true;
            } else {
                System.out.println("Token does not exist");               
                exists = false;
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            }catch (SQLException se) {
                se.printStackTrace();
            }
            try {
               if (conn != null)
                  conn.close();
            } catch (SQLException se) {
               se.printStackTrace();
            }            
        }
        
        return exists;
    }
    
    // Get all tokens from Tokens table
    public List<Token> getAllTokens() {
        List<Token> tokenList = new ArrayList<>();
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_TOKENS;
            ResultSet result = db.executeQuery(query);
            
            while(result.next()) {
                Token token = new Token();
                
                token.setId(result.getInt(TOK_COLUMN_ID));
                token.setValue(result.getString(TOK_COLUMN_VALUE));
                token.setSchool(result.getString(TOK_COLUMN_SCHOOL));
                token.setDepartment(result.getString(TOK_COLUMN_DEPT));
                token.setLevel(result.getString(TOK_COLUMN_LVL));
                
                tokenList.add(token);
            }
            
            System.out.println("List of tokens delivered");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return tokenList;
    }
    
    // Get tokens from Tokens table by school
    public List<Token> getTokensBySchool(String school) {
        List<Token> tokenList = new ArrayList<>();
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_TOKENS + " WHERE " + TOK_COLUMN_SCHOOL +
                    " = \'" + school + "\'";
            ResultSet result = db.executeQuery(query);
            
            while(result.next()) {
                Token token = new Token();
                
                token.setId(result.getInt(TOK_COLUMN_ID));
                token.setValue(result.getString(TOK_COLUMN_VALUE));
                token.setSchool(result.getString(TOK_COLUMN_SCHOOL));
                token.setDepartment(result.getString(TOK_COLUMN_DEPT));
                token.setLevel(result.getString(TOK_COLUMN_LVL));
                
                tokenList.add(token);
            }
            
            System.out.println("List of tokens (by school) delivered");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return tokenList;
    }
    
    // Update token in Tokens table
    public Token updateToken(String value, String school, String dept, String level) {
        Connection conn = null;
        Statement db = null;
        
        Token token = new Token(value, school, dept, level);
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "UPDATE " + TABLE_TOKENS + " SET " +
                TOK_COLUMN_SCHOOL + "=\'" + school + "\', " +
                TOK_COLUMN_DEPT + "=\'" + dept + "\', " +
                TOK_COLUMN_LVL + "=\'" + level +
                "\' WHERE " + TOK_COLUMN_VALUE + "=\'" + value + "\'";

            db.executeUpdate(query);
            System.out.println("Token updated");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return token;
    }
    
    // Add results to Results table
    public Results addResults(Results results) {        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "INSERT INTO " + TABLE_RESULTS + "(" + RES_COLUMN_YEAR + ", "
                    + RES_COLUMN_SEMESTER + ", " + RES_COLUMN_MATRICULE + ", "
                    + RES_COLUMN_URL + ") VALUES(\'"
                    + results.getYear() + "\', \'" + results.getSemester() + "\', \'"
                    + results.getMatricule() + "\', \'"
                    + results.getUrl() + "\')";
            System.out.println("Results ID: " + results.getId());

            db.executeUpdate(query);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
               if (conn != null)
                  conn.close();
            } catch (SQLException se) {
               se.printStackTrace();
            }
        }
        
        return results;
    }
    
    // Get results from Results table
    public Results getResults(String year, String semester, String matricule) {
        Results r = new Results();
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_RESULTS + " WHERE "
                    + RES_COLUMN_YEAR + " LIKE \'%" + year + "%\' AND "
                    + RES_COLUMN_SEMESTER + " LIKE \'%" + semester + "%\' AND "
                    + RES_COLUMN_MATRICULE + " = \'" + matricule + "\'";
            
            ResultSet result = db.executeQuery(query);
            
            while (result.next()) {
                r.setId(result.getInt(RES_COLUMN_ID));
                r.setYear(year);
                r.setSemester(semester);
                r.setMatricule(matricule);
                r.setUrl(result.getString(RES_COLUMN_URL));
            }
            System.out.println("Results delivered");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return r;
    }
    
    // Add payment to Payments table
    public Payment addPayment(Payment payment) {        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "INSERT INTO " + TABLE_PAYMENTS + "("
                    + PAY_COLUMN_DATE + ", " + PAY_COLUMN_AMT + ", "
                    + PAY_COLUMN_SCHOOL + ") VALUES(\'"
                    + payment.getDate() + "\', \'"
                    + payment.getAmount() + "\', \'"
                    + payment.getSchool() + "\')";
            System.out.println("Payment ID: " + payment.getId());

            db.executeUpdate(query);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
               if (conn != null)
                  conn.close();
            } catch (SQLException se) {
               se.printStackTrace();
            }
        }
        
        return payment;
    }
    
    // Get all payments from Payments table
    public List<Payment> getAllPayments() {
        List<Payment> paymentList = new ArrayList<>();
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_PAYMENTS;
            ResultSet result = db.executeQuery(query);
            
            while(result.next()) {
                Payment p = new Payment();
                
                p.setId(result.getInt(PAY_COLUMN_ID));
                p.setDate(result.getString(PAY_COLUMN_DATE));
                p.setAmount(result.getString(PAY_COLUMN_AMT));
                p.setSchool(result.getString(PAY_COLUMN_SCHOOL));
                
                paymentList.add(p);
            }
            
            System.out.println("List of payments delivered");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        // Reverse the order of the payments
        Collections.reverse(paymentList);
        
        return paymentList;
    }
    
    // Get single payment from Payments table
    public Payment getPayment(int id) {
        Payment p = new Payment();
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_PAYMENTS + " WHERE "
                    + PAY_COLUMN_ID + " =" + id;
            
            ResultSet result = db.executeQuery(query);
            
            if (result.next()) {
                p.setId(id);
                p.setDate(result.getString(PAY_COLUMN_DATE));
                p.setAmount(result.getString(PAY_COLUMN_AMT));
                p.setSchool(result.getString(PAY_COLUMN_SCHOOL));
            }
                
            System.out.println("Payment delivered");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return p;
    }
    
    // Convert array to string
    public String arrayToString(String[] array) {
        int size = array.length;
        String string = "";

        for (int i = 0; i < size; i++) {
            if (i == 0) {
                string = array[0];
            } else {
                string += ", " + array[i];
            }
        }

        return string;
    }

    // Convert string to array
    public String[] stringToArray(String string) {
        return string.split(", ");
    }
}
