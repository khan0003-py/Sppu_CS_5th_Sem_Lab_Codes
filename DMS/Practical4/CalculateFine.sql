
--- Unnamed PL/SQL code block: Use of Control structure and Exception handling is mandatory.

--- Consider Tables:

--- 1. Borrower(Roll_no, Name, DateofIssue, NameofBook, Status)
--- 2. Fine(Roll_no,Date,Amt)

--- Accept Roll_no and NameofBook from user.
--- Check the number of days (from date of issue).
--- If days are between 15 to 30 then fine amount will be Rs 5per day.
--- If no. of days>30, per day fine will be Rs 50 per day and for days less than 30, Rs. 5 per day.
--- After submitting the book, status will change from I to R.
--- If condition of fine is true, then details will be stored into fine table.
--- Also handles the exception by named exception handler or user define exception handler.


-- Create the Borrower table
CREATE TABLE Borrower (
    Roll_no INT PRIMARY KEY,
    Name VARCHAR(255),
    DateofIssue DATE,
    NameofBook VARCHAR(255),
    Status VARCHAR(50)
);

-- Create the Fine table
CREATE TABLE Fine (
    Roll_no INT,
    Date DATE,
    Amt INT,
    PRIMARY KEY (Roll_no, Date),
    FOREIGN KEY (Roll_no) REFERENCES Borrower(Roll_no)
);


-- Insert 10 records into the Borrower table
INSERT INTO Borrower (Roll_no, Name, DateofIssue, NameofBook, Status)
VALUES
    (1, 'John Doe', '2023-01-15', 'Introduction to SQL', 'Borrowed'),
    (2, 'Jane Smith', '2023-02-10', 'Programming in Python', 'Borrowed'),
    (3, 'Alice Johnson', '2023-03-05', 'Data Structures and Algorithms', 'Borrowed'),
    (4, 'Bob Williams', '2023-04-20', 'Database Design', 'Borrowed'),
    (5, 'Eva Davis', '2023-05-12', 'Java Programming', 'Borrowed'),
    (6, 'Mike Wilson', '2023-06-08', 'Web Development Basics', 'Borrowed'),
    (7, 'Sophia Martin', '2023-07-30', 'Artificial Intelligence', 'Borrowed'),
    (8, 'Liam Anderson', '2023-08-17', 'Machine Learning Fundamentals', 'Borrowed'),
    (9, 'Olivia Brown', '2023-09-22', 'Network Security', 'Borrowed'),
    (10, 'Noah Lee', '2023-10-19', 'Computer Graphics', 'Borrowed');


CREATE PROCEDURE calculate(IN roll INT)
BEGIN
    DECLARE fine INT;
    DECLARE noOfDays INT;
    DECLARE issueDate DATE;
    
    SELECT DateofIssue INTO issueDate FROM Borrower WHERE Roll_no = roll;
    SELECT DATEDIFF(CURDATE(), issueDate) INTO noOfDays;
    
    IF noOfDays > 15 AND noOfDays <= 30 THEN
        SET fine = noOfDays * 5;
        INSERT INTO Fine (Roll_no, Date, Amt) VALUES (roll, CURDATE(), fine);
    ELSEIF noOfDays > 30 THEN
        SET fine = noOfDays * 50;
        INSERT INTO Fine (Roll_no, Date, Amt) VALUES (roll, CURDATE(), fine);
    ELSE
        INSERT INTO Fine (Roll_no, Date, Amt) VALUES (roll, CURDATE(), 0);
    END IF;
    
    UPDATE Borrower SET Status = 'Return' WHERE Roll_no = roll;
END;
//

