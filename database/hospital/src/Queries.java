import java.util.ArrayList;

public class Queries {
        public static ArrayList<String> queries = new ArrayList<String>();

        // Room Utilization Queries
        public static ArrayList<String> getQueries() {
                queries.add(" ");
                // 1.1
                String queryOne = """
                                SELECT r.RoomNumber, p.Name AS PatientName, a.AdmissionDate \
                                FROM Room r \
                                JOIN Admission a ON r.RoomNumber = a.RoomNumber \
                                JOIN Patient p ON a.PatientId = p.PatientId \
                                WHERE a.DischargeDate IS NULL
                                """;
                queries.add(queryOne);

                // 1.2
                String queryTwo = """
                                SELECT RoomNumber \
                                FROM Room \
                                WHERE RoomNumber NOT IN ( \
                                 SELECT RoomNumber \
                                FROM Admission \
                                WHERE DischargeDate IS NULL)
                                """;
                queries.add(queryTwo);

                // 1.3
                String queryThree = """
                                SELECT r.RoomNumber, p.Name AS PatientName, a.AdmissionDate \
                                FROM Room r \
                                LEFT JOIN Admission a ON r.RoomNumber = a.RoomNumber AND a.DischargeDate IS NULL \
                                LEFT JOIN Patient p ON a.PatientId = p.PatientId
                                """;
                queries.add(queryThree);

                // Patient Information

                // 2.1
                String queryFour = "SELECT * FROM Patient";
                queries.add(queryFour);

                // 2.2
                String queryFive = """
                                SELECT p.PatientId, p.Name \
                                FROM Patient p \
                                JOIN Admission a ON p.PatientId = a.PatientId \
                                WHERE a.DischargeDate IS NULL
                                """;
                queries.add(queryFive);

                // 2.3
                String querySix = """
                                SELECT p.PatientId, p.Name \
                                FROM Patient p \
                                LEFT JOIN Admission a ON p.PatientId = a.PatientId \
                                WHERE a.DischargeDate BETWEEN '2023-01-01' AND '2023-12-31'
                                """;
                queries.add(querySix);

                // 2.4
                String querySeven = """
                                SELECT p.PatientId, p.Name \
                                FROM Patient p \
                                JOIN Admission a ON p.PatientId = a.PatientId \
                                WHERE a.AdmissionDate BETWEEN '2023-01-01' AND '2023-12-31'
                                """;
                queries.add(querySeven);

                // 2.5
                String queryEight = """
                                SELECT a.AdmissionId, a.AdmissionDate, d.Name as Diagnosis \
                                FROM Admission a \
                                JOIN Diagnosis d ON a.DiagnosisId = d.DiagnosisId \
                                WHERE a.PatientId = 1
                                """;
                queries.add(queryEight);

                // 2.6
                String queryNine = """
                                SELECT a.AdmissionId, ta.TimeStamp, t.Name as Treatment \
                                FROM Admission a \
                                JOIN Treatment_Administration ta ON a.AdmissionId = ta.AdmissionId \
                                JOIN Treatment_Order torder ON ta.OrderId = torder.OrderId \
                                JOIN Treatment t ON torder.TreatmentId = t.TreatmentId \
                                WHERE a.PatientId = 1 \
                                ORDER BY a.AdmissionDate DESC
                                """;
                queries.add(queryNine);

                // 2.7
                String queryTen = """
                                SELECT DISTINCT p.PatientId, p.Name \
                                FROM Patient p \
                                JOIN Admission a ON p.PatientId = a.PatientId \
                                WHERE a.DischargeDate IS NOT NULL \
                                AND EXISTS (\
                                    SELECT 1 \
                                    FROM Admission aNext \
                                    WHERE aNext.PatientId = a.PatientId \
                                    AND aNext.AdmissionDate <= DATE_ADD(a.DischargeDate, INTERVAL 30 DAY))
                                    """;
                queries.add(queryTen);

                // 2.8
                String queryEleven = """
                                SELECT p.PatientId,p.Name, \
                                COUNT(*) AS TotalAdmissions, \
                                AVG(DATEDIFF(a.DischargeDate, a.AdmissionDate)) AS AvgStayDuration \
                                FROM Patient p \
                                JOIN Admission a ON p.PatientId = a.PatientId \
                                GROUP BY p.PatientId, p.Name
                                """;
                queries.add(queryEleven);

                // Diagnosis and Treatment Information

                // 3.1
                String queryTwelve = """
                                SELECT d.DiagnosisId,d.Name, \
                                COUNT(*) AS TotalPatientOccurrences \
                                FROM Diagnosis d \
                                JOIN Admission a ON d.DiagnosisId = a.DiagnosisId \
                                GROUP BY d.DiagnosisId, d.Name \
                                ORDER BY TotalPatientOccurrences DESC
                                """;
                queries.add(queryTwelve);

                // 3.2
                String queryThirteen = """
                                SELECT d.DiagnosisId,d.Name, \
                                COUNT(*) AS TotalHospitalOccurrences \
                                FROM Diagnosis d \
                                JOIN Admission a ON d.DiagnosisId = a.DiagnosisId \
                                GROUP BY d.DiagnosisId, d.Name \
                                ORDER BY TotalHospitalOccurrences DESC
                                """;
                queries.add(queryThirteen);

                // 3.3
                String queryFourteen = """
                                SELECT t.TreatmentId, t.Name, \
                                COUNT(*) AS TotalOccurrences \
                                FROM Treatment t \
                                JOIN Treatment_Order torder \
                                ON t.TreatmentId = torder.TreatmentId \
                                JOIN Treatment_Administration ta \
                                ON torder.OrderId= ta.OrderId \
                                GROUP BY t.TreatmentId, t.Name \
                                ORDER BY TotalOccurrences DESC
                                """;
                queries.add(queryFourteen);

                // 3.4
                String queryFifteen = """
                                WITH PatientAdmissionCount \
                                    AS (\
                                    SELECT PatientId, COUNT(*) AS AdmissionCount \
                                    FROM Admission \
                                    GROUP BY PatientId \
                                    ),

                                MaxAdmissionCount AS (\
                                    SELECT MAX(AdmissionCount) AS MaxAdmissions \
                                    FROM PatientAdmissionCount \
                                    ),

                                TopPatients AS (\
                                    SELECT pac.PatientId \
                                    FROM PatientAdmissionCount pac \
                                    JOIN MaxAdmissionCount mac ON pac.AdmissionCount = mac.MaxAdmissions \
                                    )

                                SELECT d.DiagnosisId, d.Name, COUNT(*) AS DiagnosisOccurrences \
                                FROM Admission a \
                                JOIN Diagnosis d ON a.DiagnosisId = d.DiagnosisId \
                                WHERE a.PatientId IN (SELECT PatientId FROM TopPatients) \
                                GROUP BY d.DiagnosisId, d.Name \
                                ORDER BY DiagnosisOccurrences ASC
                                """;
                queries.add(queryFifteen);

                // 3.5
                String querySixteen = """
                                SELECT p.Name AS PatientName, CONCAT(e.FirstName, ' ', e.LastName) AS DoctorName \
                                FROM Patient p \
                                JOIN Treatment_Order torder ON p.PatientId = torder.PatientId \
                                JOIN Employee e ON torder.DoctorId = e.EmployeeId \
                                WHERE torder.TreatmentId = 1 \
                                """;
                queries.add(querySixteen);

                // Employee Information

                // 4.1
                String querySeventeen = """
                                SELECT FirstName, LastName, r.Name AS JobCategory \
                                FROM Employee e \
                                JOIN Role r ON e.RoleId = r.RoleId \
                                ORDER BY LastName ASC, FirstName ASC
                                """;
                queries.add(querySeventeen);

                // 4.2
                String queryEighteen = """
                                WITH HighAdmissionPatients \
                                    AS (\
                                    SELECT PatientId \
                                    FROM Admission \
                                    GROUP BY PatientId \
                                    HAVING COUNT(AdmissionId) >= 1\
                                )

                                SELECT DISTINCT e.FirstName, e.LastName \
                                FROM Employee e \
                                JOIN Doctor_Patient dp ON e.EmployeeId = dp.DoctorId \
                                WHERE dp.PatientId IN (SELECT PatientId FROM HighAdmissionPatients) \
                                AND dp.isPrimaryDoctor = TRUE
                                """;
                queries.add(queryEighteen);

                // 4.3
                String queryNineteen = """
                                SELECT d.DiagnosisId, d.Name, COUNT(*) AS OccurrenceCount \
                                FROM Diagnosis d \
                                JOIN Admission a ON d.DiagnosisID = a.DiagnosisId \
                                JOIN Doctor_Patient dp ON a.PatientId = dp.PatientID \
                                WHERE dp.DoctorId = 1 \
                                AND dp.isPrimaryDoctor = TRUE \
                                GROUP BY d.DiagnosisId, d.Name \
                                ORDER BY OccurrenceCount DESC
                                """;
                queries.add(queryNineteen);

                // 4.4
                String queryTwenty = """
                                SELECT t.TreatmentId, t.Name, COUNT(*) AS OccurrenceCount \
                                FROM Treatment t \
                                JOIN Treatment_Order torder ON t.TreatmentId = torder.TreatmentId \
                                WHERE torder.DoctorId = 1 \
                                GROUP BY t.TreatmentId, t.Name \
                                ORDER BY OccurrenceCount DESC
                                """;
                queries.add(queryTwenty);

                // 4.5
                String queryTwentyOne = """
                                SELECT e.EmployeeId, e.FirstName, e.LastName \
                                FROM Employee e \
                                WHERE NOT EXISTS (\
                                    SELECT 1 \
                                    FROM Admission a \
                                    WHERE NOT EXISTS (\
                                        SELECT 1 \
                                        FROM Treatment_Administration ta \
                                        WHERE ta.EmployeeId = e.EmployeeId \
                                        AND ta.AdmissionId = a.AdmissionId\
                                    )\
                                )
                                """;
                queries.add(queryTwentyOne);

                return queries;
        }
}
