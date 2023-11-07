-- Write a PL/SQL code block to calculate the area of a circle for a value of radius varying from 5 to 9. 
-- Store the radius and the corresponding values of calculated area in an empty table named areas, 
-- consisting of two columns, radius and area.

CREATE TABLE areas (
    radius INT,
    result FLOAT
);

DELIMITER //
CREATE PROCEDURE calculateArea()
BEGIN
    DECLARE radius INT;
    DECLARE pi FLOAT;
    DECLARE res int;
    SET pi = 3.14;
    SET radius = 5;
    WHILE radius <= 9 DO
        SET res = pi * (radius * radius);
        insert into areas values(radius,res);
        SET radius = radius + 1;
    END WHILE;
END;
//
DELIMITER ;