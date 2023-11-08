--- Cursors: (All types: Implicit, Explicit, Cursor FOR Loop, Parameterized Cursor)

--- Write a PL/SQL block of code using parameterized Cursor that will merge the data available in the newly created table N_RollCall with the data available in the table O_RollCall. 
--- If the data in the first table already exist in the second table then that data should be skipped.


CREATE TABLE o_rollcall (
rollno INT NOT NULL,
name VARCHAR(255) NOT NULL,
marks INT NOT NULL,
PRIMARY KEY (rollno)
);

CREATE TABLE n_rollcall (
rollno INT NOT NULL,
name VARCHAR(255) NOT NULL,
marks INT NOT NULL,
PRIMARY KEY (rollno)
);

INSERT INTO o_rollcall (rollno, name, marks)
VALUES
  (101, 'Alice', 95),
  (102, 'Bob', 85),
  (103, 'Charlie', 75),
  (104, 'David', 65),
  (105, 'Emily', 90),
  (106, 'Frank', 80),
  (107, 'Grace', 70),
  (108, 'Helen', 60);

INSERT INTO n_rollcall (rollno, name, marks)
VALUES
  (101, 'Alice', 95),
  (102, 'Bob', 85);


DELIMITER //

CREATE PROCEDURE p(IN id INT)
BEGIN
  DECLARE oldid INT;
  DECLARE done BOOLEAN DEFAULT FALSE;
  DECLARE c CURSOR FOR SELECT rollno FROM o_rollcall WHERE rollno = id;

  OPEN c;

  cloop: LOOP
    FETCH c INTO oldid;

    IF NOT done THEN
      IF EXISTS (SELECT * FROM n_rollcall WHERE rollno = id) THEN
        SET done = TRUE;
      ELSE
        INSERT INTO n_rollcall (SELECT * FROM o_rollcall WHERE rollno = id);
      END IF;
    END IF;

    LEAVE cloop;
  END LOOP;

  CLOSE c;
END;
//

DELIMITER ;