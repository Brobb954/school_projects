import java.sql.*;
import java.util.ArrayList;

/**
 * The DatabaseUtils class provides utility methods for setting up and filling a
 * hospital database.
 */
public class DatabaseUtils {

        /**
         * Sets up the database by creating the necessary tables if they do not exist.
         *
         * @param connection the database connection
         * @throws SQLException if an error occurs while setting up the database
         */
        public static void databaseSetUp(Connection connection) throws SQLException {
                ArrayList<String> createTables = new ArrayList<>();
                // Create the role table
                String createRoleTable = "CREATE TABLE IF NOT EXISTS role ("
                                + "RoleId INT AUTO_INCREMENT PRIMARY KEY,"
                                + "Name VARCHAR(255) NOT NULL"
                                + ");";
                createTables.add(createRoleTable);

                // Create the Room table
                String createRoomTable = "CREATE TABLE IF NOT EXISTS Room ("
                                + "RoomNumber INT PRIMARY KEY,"
                                + "Capacity INT)";
                createTables.add(createRoomTable);

                // Create the Employee table
                String createEmployeeTable = "CREATE TABLE IF NOT EXISTS Employee ( "
                                + "EmployeeId INT PRIMARY KEY, "
                                + " FirstName VARCHAR(255),"
                                + "LastName VARCHAR(255),"
                                + "RoleId INT,"
                                + "FOREIGN KEY (RoleId) REFERENCES Role(RoleId))";
                createTables.add(createEmployeeTable);

                // Create the Diagnosis table
                String createDiagnosisTable = "CREATE TABLE IF NOT EXISTS Diagnosis ("
                                + "DiagnosisId INT PRIMARY KEY,"
                                + "Name VARCHAR(255))";
                createTables.add(createDiagnosisTable);

                // Create the Patient table
                String createPatientTable = "CREATE TABLE IF NOT EXISTS Patient ("
                                + "PatientId INT PRIMARY KEY,"
                                + "Name VARCHAR(255),"
                                + "InsurancePolicy VARCHAR(255),"
                                + "EmergencyContact VARCHAR(255))";
                createTables.add(createPatientTable);

                // Create the Treatment table
                String createTreatmentTable = "CREATE TABLE IF NOT EXISTS Treatment ("
                                + "TreatmentId INT PRIMARY KEY,"
                                + "Name VARCHAR(255),"
                                + "Type VARCHAR(255))";
                createTables.add(createTreatmentTable);

                // Create the Treatment_Order table
                String createTreatmentOrderTable = "CREATE TABLE IF NOT EXISTS Treatment_Order ("
                                + "OrderId INT PRIMARY KEY,"
                                + "DoctorId INT,"
                                + "PatientId INT,"
                                + "TreatmentId INT,"
                                + "TimeStamp DATETIME,"
                                + "FOREIGN KEY (DoctorId) REFERENCES Employee(EmployeeId),"
                                + "FOREIGN KEY (PatientId) REFERENCES Patient(PatientId),"
                                + "FOREIGN KEY (TreatmentId) REFERENCES Treatment(TreatmentId))";
                createTables.add(createTreatmentOrderTable);

                // Create the Doctor_Patient table
                String createDoctorPatientTable = "CREATE TABLE IF NOT EXISTS Doctor_Patient ("
                                + "DoctorId INT,"
                                + "PatientId INT,"
                                + "isPrimaryDoctor BOOLEAN,"
                                + "PRIMARY KEY (DoctorId, PatientId),"
                                + "FOREIGN KEY (DoctorId) REFERENCES Employee(EmployeeId),"
                                + "FOREIGN KEY (PatientId) REFERENCES Patient(PatientId))";
                createTables.add(createDoctorPatientTable);

                // Create the Admission table
                String createAdmissionsTable = "CREATE TABLE IF NOT EXISTS Admission ("
                                + "AdmissionId INT PRIMARY KEY,"
                                + "PatientId INT,"
                                + "AdmissionDate DATETIME,"
                                + "DischargeDate DATETIME,"
                                + "RoomNumber INT,"
                                + "DiagnosisId INT,"
                                + "FOREIGN KEY (PatientId) REFERENCES Patient(PatientId),"
                                + "FOREIGN KEY (RoomNumber) REFERENCES Room(RoomNumber),"
                                + "FOREIGN KEY (DiagnosisId) REFERENCES Diagnosis(DiagnosisId))";
                createTables.add(createAdmissionsTable);

                // Create the Treatment_Administration table
                String createTreatmentAdministrationTable = "CREATE TABLE IF NOT EXISTS Treatment_Administration ("
                                + "AdministrationId INT PRIMARY KEY,"
                                + "EmployeeId INT,"
                                + "OrderId INT,"
                                + "AdmissionId INT,"
                                + "TimeStamp DATETIME,"
                                + "FOREIGN KEY (EmployeeId) REFERENCES Employee(EmployeeId),"
                                + "FOREIGN KEY (OrderId) REFERENCES Treatment_Order(OrderId),"
                                + "FOREIGN KEY (AdmissionId) REFERENCES Admission(AdmissionId))";
                createTables.add(createTreatmentAdministrationTable);

                // Execute the create table statements
                for (String sql : createTables) {
                        try {
                                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                                preparedStatement.executeUpdate();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
        }

        /**
         * Fills the database with sample data.
         *
         * @param connection the database connection
         */
        public static void fillInDatabase(Connection connection) {
                ArrayList<String> fillTables = new ArrayList<>();

                // Insert data into Role table
                String roleTable = "INSERT INTO Role (RoleId, Name) VALUES (1, 'Doctor'), (2, 'Nurse'), (3, 'Administrator')";
                fillTables.add(roleTable);

                // Insert data into Room table
                String roomTable = "INSERT INTO Room (RoomNumber, Capacity) VALUES (101, 2), (102, 2), (103, 1), (105, 1)";
                fillTables.add(roomTable);

                // Insert data into Employee table
                String employeeTable = "INSERT INTO Employee (EmployeeId, FirstName, LastName, RoleId) VALUES (1, 'John', 'Doe', 1), (2, 'Jane', 'Smith', 2), (3, 'Alice', 'Johnson', 1), (4, 'Bob', 'Lee', 3)";
                fillTables.add(employeeTable);

                // Insert data into Diagnosis table
                String diagnosisTable = "INSERT INTO Diagnosis (DiagnosisId, Name) VALUES (1, 'Flu'), (2, 'COVID-19'), (3, 'Broken Leg')";
                fillTables.add(diagnosisTable);

                // Insert data into Patient table
                String patientTable = "INSERT INTO Patient (PatientId, Name, InsurancePolicy, EmergencyContact) VALUES (1, 'Tom Thumb', '123456', 'Jan Thumb'), (2, 'Sally Ride', '654321', 'Wally Ride'), (3, 'Lisa Ray', '789012', 'Billy Ray')";
                fillTables.add(patientTable);

                // Insert data into Treatment table
                String treatmentTable = "INSERT INTO Treatment (TreatmentId, Name, Type) VALUES (1, 'Physical Therapy', 'Rehabilitation'), (2, 'Surgery', 'Operative'), (3, 'Medication', 'Pharmacological')";
                fillTables.add(treatmentTable);

                // Insert data into Treatment_Order table
                String treatmentOrderTable = "INSERT INTO Treatment_Order (OrderId, DoctorId, PatientId, TreatmentId, TimeStamp) VALUES (1, 1, 1, 1, '2023-04-25 09:00:00'), (2, 1, 2, 2, '2023-04-26 14:00:00'), (3, 3, 3, 3, '2023-04-27 16:00:00'), (4, 1, 1, 3, '2023-02-26 10:00:00')";
                fillTables.add(treatmentOrderTable);

                // Insert data into Doctor_Patient table
                String doctorPatientTable = "INSERT INTO Doctor_Patient (DoctorId, PatientId, isPrimaryDoctor) VALUES (1, 1, TRUE), (1, 2, TRUE), (3, 3, TRUE), (1, 3, TRUE)";
                fillTables.add(doctorPatientTable);

                // Insert data into Admission table
                String admissionTable = "INSERT INTO Admission (AdmissionId, PatientId, AdmissionDate, DischargeDate, RoomNumber, DiagnosisId) VALUES (1, 1, '2023-01-20', '2023-01-30', 105, 1), (2, 2, '2023-04-26', NULL, 101, 2), (3, 3, '2023-04-27', NULL, 103, 3), (4, 1, '2023-02-25', '2023-02-28', 101, 1)";
                fillTables.add(admissionTable);

                // Insert data into Treatment_Administration table
                String treatmentAdministration = "INSERT INTO Treatment_Administration (AdministrationId, EmployeeId, OrderId, AdmissionId, TimeStamp) VALUES (1, 1, 1, 1, '2023-04-25 09:15:00'), (2, 1, 2, 2, '2023-04-26 14:15:00'), (3, 1, 3, 3, '2023-04-27 16:15:00'), (4, 1, 4, 4, '2023-02-26 11:00:00')";
                fillTables.add(treatmentAdministration);

                // Execute the insert statements
                for (String sql : fillTables) {
                        try {
                                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                                preparedStatement.executeUpdate();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
        }
}
