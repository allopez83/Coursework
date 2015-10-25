#1. Write a trigger that makes new students named 'Friendly' automatically like everyone else in their grade. That is, after the trigger runs, we should have ('Friendly', A) in the Likes table for every other Highschooler A in the same grade as 'Friendly'.

CREATE TRIGGER friendlyTrigger
AFTER INSERT ON Highschooler
FOR EACH ROW
BEGIN
    INSERT INTO Likes
        SELECT NEW.ID, ID
        FROM Highschooler
        WHERE NEW.name = "Friendly"
            AND NEW.grade = grade
            AND name <> "Friendly";

END;

#2. Write one or more triggers to manage the grade attribute of new Highschoolers. If the inserted tuple has a value less than 9 or greater than 12, change the value to NULL. On the other hand, if the inserted tuple has a null value for grade, change it to 9.


