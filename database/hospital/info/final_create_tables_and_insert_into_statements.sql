-- Final Create Tables Statements

CREATE TABLE Role (
    RoleId INT PRIMARY KEY,
    Name VARCHAR(255)
)

-- Modifying the Employee Table to include FirstName and LastName
CREATE TABLE Employee (
    EmployeeId INT PRIMARY KEY,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    RoleId INT,
    FOREIGN KEY (RoleId) REFERENCES Role(RoleId)
    
)

-- Adding a Diagnosis Table
CREATE TABLE Diagnosis (
    DiagnosisId INT PRIMARY KEY,
    Name VARCHAR(255)

)

CREATE TABLE Patient (
    PatientId INT PRIMARY KEY,
    Name VARCHAR(255),
    InsurancePolicy VARCHAR(255),
    EmergencyContact VARCHAR(255)
)

CREATE TABLE Treatment (
    TreatmentId INT PRIMARY KEY,
    Name VARCHAR(255),
    Type VARCHAR(255)
)

CREATE TABLE Treatment_Order (
    OrderId INT PRIMARY KEY,
    DoctorId INT,
    PatientId INT,
    TreatmentId INT,
    TimeStamp DATETIME,
    FOREIGN KEY (DoctorId) REFERENCES Employee(EmployeeId),
    FOREIGN KEY (PatientId) REFERENCES Patient(PatientId),
    FOREIGN KEY (TreatmentId) REFERENCES Treatment(TreatmentId)
)

CREATE TABLE Doctor_Patient (
    DoctorId INT,
    PatientId INT,
    isPrimaryDoctor BOOLEAN,
    PRIMARY KEY (DoctorId, PatientId),
    FOREIGN KEY (DoctorId) REFERENCES Employee(EmployeeId),
    FOREIGN KEY (PatientId) REFERENCES Patient(PatientId)
)

-- Modifying the Admission Table to include DiagnosisId
CREATE TABLE Admission (
    AdmissionId INT PRIMARY KEY,
    PatientId INT,
    AdmissionDate DATETIME,
    DischargeDate DATETIME,
    RoomNumber INT,
    DiagnosisId INT,
    FOREIGN KEY (PatientId) REFERENCES Patient(PatientId),
    FOREIGN KEY (RoomNumber) REFERENCES Room(RoomNumber),
    FOREIGN KEY (DiagnosisId) REFERENCES Diagnosis(DiagnosisId)
)

CREATE TABLE Room (
    RoomNumber INT PRIMARY KEY,
    Capacity INT
)

-- Modifying the Treatment_Administration Table to include AdmissionId
CREATE TABLE Treatment_Administration (
    AdministrationId INT PRIMARY KEY,
    EmployeeId INT,
    OrderId INT,
    AdmissionId INT,
    TimeStamp DATETIME,
    FOREIGN KEY (EmployeeId) REFERENCES Employee(EmployeeId),
    FOREIGN KEY (OrderId) REFERENCES Treatment_Order(OrderId),
    FOREIGN KEY (AdmissionId) REFERENCES Admission(AdmissionId)
)


-- Final Insert Into Statements

-- Insert roles
INSERT INTO Role (RoleId, Name) VALUES
(1, 'Doctor'),
(2, 'Nurse'),
(3, 'Administrator');

-- Insert employees
INSERT INTO Employee (EmployeeId, FirstName, LastName, RoleId) VALUES
(1, 'John', 'Doe', 1),
(2, 'Jane', 'Smith', 2),
(3, 'Alice', 'Johnson', 1),
(4, 'Bob', 'Lee', 3);

-- Insert diagnoses
INSERT INTO Diagnosis (DiagnosisId, Name) VALUES
(1, 'Flu'),
(2, 'COVID-19'),
(3, 'Broken Leg');

-- Insert patients
INSERT INTO Patient (PatientId, Name, InsurancePolicy, EmergencyContact) VALUES
(1, 'Tom Thumb', '123456', 'Jane Thumb'),
(2, 'Sally Ride', '654321', 'Wally Ride'),
(3, 'Lisa Ray', '789012', 'Billy Ray');

-- Insert treatments
INSERT INTO Treatment (TreatmentId, Name, Type) VALUES
(1, 'Physical Therapy', 'Rehabilitation'),
(2, 'Surgery', 'Operative'),
(3, 'Medication', 'Pharmacological');

-- Insert treatment orders
INSERT INTO Treatment_Order (OrderId, DoctorId, PatientId, TreatmentId, TimeStamp) VALUES
(1, 1, 1, 1, '2023-04-25 09:00:00'),
(2, 1, 2, 2, '2023-04-26 14:00:00'),
(3, 3, 3, 3, '2023-04-27 16:00:00');

-- Insert doctor-patient relationships
INSERT INTO Doctor_Patient (DoctorId, PatientId, isPrimaryDoctor) VALUES
(1, 1, TRUE),
(1, 2, TRUE),
(3, 3, TRUE);

-- Insert rooms
INSERT INTO Room (RoomNumber, Capacity) VALUES
(101, 2),
(102, 2),
(103, 1);

-- Insert admissions
INSERT INTO Admission (AdmissionId, PatientId, AdmissionDate, DischargeDate, RoomNumber, DiagnosisId) VALUES
(1, 1, '2023-01-20', '2023-01-30', 105, 1),
(2, 2, '2023-04-26', NULL, 101, 2),
(3, 3, '2023-04-27', NULL, 103, 3),
(4, 1, '2023-02-25', '2023-02-28', 101, 1);

-- Insert treatment administrations
INSERT INTO Treatment_Administration (AdministrationId, EmployeeId, OrderId, AdmissionId, TimeStamp) VALUES
(1, 1, 1, 1, '2023-04-25 09:15:00'),
(2, 1, 2, 2, '2023-04-26 14:15:00'),
(3, 3, 3, 3, '2023-04-27 16:15:00');
