--- Named PL/SQL Block: 

--- PL/SQL Stored Procedure and Stored Function. Write a Stored Procedure namely proc_Grade for the categorization of student. 
--- If marks scoredby students in examination is <=1500 and marks>=990 then student will be placed in distinction category 
--- if marks scored are between 989 and 900 category is first class, if marks 899 and 825 category is Higher Second Class.
--- Write a PL/SQL block to use procedure created with above requirement.

CREATE TABLE stud_marks (
    roll_no INT PRIMARY KEY,
    name CHAR(10),
    total_marks INT
);

CREATE TABLE result (
    roll_no INT,
    name CHAR(30),
    class CHAR(20)
);

DELIMITER //
CREATE PROCEDURE proc_grade(IN marks INT, OUT class CHAR(10))
BEGIN
    IF marks <= 1500 AND marks >= 990 THEN
        SET class = 'DIST';
    ELSEIF marks <= 989 AND marks >= 900 THEN
        SET class = 'FC';
    ELSEIF marks <= 899 AND marks >= 825 THEN
        SET class = 'HSC';
    ELSEIF marks <= 824 AND marks >= 750 THEN
        SET class = 'SC';
    ELSEIF marks <= 749 AND marks >= 650 THEN
        SET class = 'PC';
    ELSE
        SET class = 'fail';
    END IF;
END//
DELIMITER ;

DELIMITER //
CREATE FUNCTION find_result(roll_in INT) RETURNS INT DETERMINISTIC
BEGIN
    DECLARE fmarks INT;
    DECLARE grade CHAR(10);
    DECLARE stud_name CHAR(10);
    
    SELECT total_marks, name INTO fmarks, stud_name FROM stud_marks WHERE roll_no = roll_in;
    
    CALL proc_grade(fmarks, @grade);
    
    INSERT INTO result (roll_no, name, class) VALUES (roll_in, stud_name, @grade);
    
    RETURN roll_in;
END//
DELIMITER ;