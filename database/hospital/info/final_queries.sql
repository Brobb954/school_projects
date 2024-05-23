--Final Queries

-- Room Utilization Queries
-- 1.1
SELECT r.RoomNumber, p.Name AS PatientName, a.AdmissionDate 
FROM Room r 
JOIN Admission a ON r.RoomNumber = a.RoomNumber 
JOIN Patient p ON a.PatientId = p.PatientId 
WHERE a.DischargeDate IS NULL

-- 1.2
SELECT RoomNumber 
FROM Room 
WHERE RoomNumber NOT IN ( 
    SELECT RoomNumber 
    FROM Admission 
    WHERE DischargeDate IS NULL)

-- 1.3
SELECT r.RoomNumber, p.Name AS PatientName, a.AdmissionDate 
FROM Room r 
LEFT JOIN Admission a ON r.RoomNumber = a.RoomNumber AND a.DischargeDate IS NULL 
LEFT JOIN Patient p ON a.PatientId = p.PatientId

-- Patient Information

-- 2.1
SELECT * FROM Patient

-- 2.2
SELECT p.PatientId, p.Name 
FROM Patient p 
JOIN Admission a ON p.PatientId = a.PatientId 
WHERE a.DischargeDate IS NULL

-- 2.3
SELECT p.PatientId, p.Name 
FROM Patient p 
LEFT JOIN Admission a ON p.PatientId = a.PatientId 
WHERE a.DischargeDate BETWEEN '2023-01-01' AND '2023-12-31'

-- 2.4
SELECT p.PatientId, p.Name 
FROM Patient p 
JOIN Admission a ON p.PatientId = a.PatientId 
WHERE a.AdmissionDate BETWEEN '2023-01-01' AND '2023-12-31'

-- 2.5
SELECT a.AdmissionId, a.AdmissionDate, d.Name as Diagnosis 
FROM Admission a 
JOIN Diagnosis d ON a.DiagnosisId = d.DiagnosisId 
WHERE a.PatientId = 1

-- 2.6
SELECT a.AdmissionId, ta.TimeStamp, t.Name as Treatment 
FROM Admission a 
JOIN Treatment_Administration ta ON a.AdmissionId = ta.AdmissionId 
JOIN Treatment_Order torder ON ta.OrderId = torder.OrderId 
JOIN Treatment t ON torder.TreatmentId = t.TreatmentId 
WHERE a.PatientId = 1 
ORDER BY a.AdmissionDate DESC

-- 2.7
SELECT DISTINCT p.PatientId, p.Name 
FROM Patient p 
JOIN Admission a ON p.PatientId = a.PatientId 
WHERE a.DischargeDate IS NOT NULL 
AND EXISTS (
    SELECT 1 
    FROM Admission aNext 
    WHERE aNext.PatientId = a.PatientId 
    AND aNext.AdmissionDate <= DATE_ADD(a.DischargeDate, INTERVAL 30 DAY))

-- 2.8
SELECT p.PatientId,p.Name, 
COUNT(*) AS TotalAdmissions, 
AVG(DATEDIFF(a.DischargeDate, a.AdmissionDate)) AS AvgStayDuration 
FROM Patient p 
JOIN Admission a ON p.PatientId = a.PatientId 
GROUP BY p.PatientId, p.Name


-- Diagnosis and Treatment Information

-- 3.1
SELECT d.DiagnosisId,d.Name, 
COUNT(*) AS TotalPatientOccurrences 
FROM Diagnosis d 
JOIN Admission a ON d.DiagnosisId = a.DiagnosisId 
GROUP BY d.DiagnosisId, d.Name 
ORDER BY TotalPatientOccurrences DESC

-- 3.2
SELECT d.DiagnosisId,d.Name, 
COUNT(*) AS TotalHospitalOccurrences 
FROM Diagnosis d 
JOIN Admission a ON d.DiagnosisId = a.DiagnosisId 
GROUP BY d.DiagnosisId, d.Name 
ORDER BY TotalHospitalOccurrences DESC

-- 3.3
SELECT t.TreatmentId, t.Name, 
COUNT(*) AS TotalOccurrences 
FROM Treatment t 
JOIN Treatment_Order torder 
ON t.TreatmentId = torder.TreatmentId 
JOIN Treatment_Administration ta 
ON torder.OrderId= ta.OrderId 
GROUP BY t.TreatmentId, t.Name 
ORDER BY TotalOccurrences DESC

-- 3.4
WITH PatientAdmissionCount 
    AS (
    SELECT PatientId, COUNT(*) AS AdmissionCount 
    FROM Admission 
    GROUP BY PatientId 
    ),

MaxAdmissionCount AS (
    SELECT MAX(AdmissionCount) AS MaxAdmissions 
    FROM PatientAdmissionCount 
    ),

TopPatients AS (
    SELECT pac.PatientId 
    FROM PatientAdmissionCount pac 
    JOIN MaxAdmissionCount mac ON pac.AdmissionCount = mac.MaxAdmissions 
    )

SELECT d.DiagnosisId, d.Name, COUNT(*) AS DiagnosisOccurrences 
FROM Admission a 
JOIN Diagnosis d ON a.DiagnosisId = d.DiagnosisId 
WHERE a.PatientId IN (SELECT PatientId FROM TopPatients) 
GROUP BY d.DiagnosisId, d.Name 
ORDER BY DiagnosisOccurrences ASC

-- 3.5
SELECT p.Name AS PatientName, CONCAT(e.FirstName, ' ', e.LastName) AS DoctorName 
FROM Patient p 
JOIN Treatment_Order torder ON p.PatientId = torder.PatientId 
JOIN Employee e ON torder.DoctorId = e.EmployeeId 
WHERE torder.TreatmentId = 1 

-- Employee Information

-- 4.1
SELECT FirstName, LastName, r.Name AS JobCategory 
FROM Employee e 
JOIN Role r ON e.RoleId = r.RoleId 
ORDER BY LastName ASC, FirstName ASC

-- 4.2
WITH HighAdmissionPatients 
    AS (
    SELECT PatientId 
    FROM Admission 
    GROUP BY PatientId 
    HAVING COUNT(AdmissionId) >= 1
    )

SELECT DISTINCT e.FirstName, e.LastName 
FROM Employee e 
JOIN Doctor_Patient dp ON e.EmployeeId = dp.DoctorId 
WHERE dp.PatientId IN (SELECT PatientId FROM HighAdmissionPatients) 
AND dp.isPrimaryDoctor = TRUE

-- 4.3
SELECT d.DiagnosisId, d.Name, COUNT(*) AS OccurrenceCount 
FROM Diagnosis d 
JOIN Admission a ON d.DiagnosisID = a.DiagnosisId 
JOIN Doctor_Patient dp ON a.PatientId = dp.PatientID 
WHERE dp.DoctorId = 1 
AND dp.isPrimaryDoctor = TRUE 
GROUP BY d.DiagnosisId, d.Name 
ORDER BY OccurrenceCount DESC

-- 4.4
   SELECT t.TreatmentId, t.Name, COUNT(*) AS OccurrenceCount 
FROM Treatment t 
JOIN Treatment_Order torder ON t.TreatmentId = torder.TreatmentId 
WHERE torder.DoctorId = 1 
GROUP BY t.TreatmentId, t.Name 
ORDER BY OccurrenceCount DESC

-- 4.5
SELECT e.EmployeeId, e.FirstName, e.LastName 
FROM Employee e
WHERE NOT EXISTS (
     SELECT 1 
     FROM Admission a
     WHERE NOT EXISTS (
         SELECT 1 
         FROM Treatment_Administration ta
         WHERE ta.EmployeeId = e.EmployeeId
         AND ta.AdmissionId = a.AdmissionId
     )
)
