# Core

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

CREATE TRIGGER setNine
AFTER INSERT ON Highschooler
BEGIN
    UPDATE Highschooler
    SET grade = 9
    WHERE NEW.grade ISNULL AND ID=NEW.ID;
END;
|
CREATE TRIGGER setNull
AFTER INSERT ON Highschooler
BEGIN
    UPDATE Highschooler
    SET grade = NULL
    WHERE grade > 12 OR grade < 9 AND ID=NEW.ID;
END;

#3. Write a trigger that automatically deletes students when they graduate, i.e., when their grade is updated to exceed 12. 

CREATE TRIGGER graduate
AFTER UPDATE ON Highschooler
BEGIN
    DELETE FROM Highschooler
    WHERE grade > 12;
END;


# Challenge

#1. Write one or more triggers to maintain symmetry in friend relationships. Specifically, if (A,B) is deleted from Friend, then (B,A) should be deleted too. If (A,B) is inserted into Friend then (B,A) should be inserted too. Don't worry about updates to the Friend table.

CREATE TRIGGER deleteFriend
AFTER DELETE ON Friend
BEGIN
    DELETE FROM Friend
    WHERE ID1 = OLD.ID2 AND ID2 = OLD.ID1;
END;
|
CREATE TRIGGER insertFriend
AFTER INSERT ON Friend
BEGIN
    INSERT INTO Friend VALUES (NEW.ID2, NEW.ID1);
END;

#2. Write a trigger that automatically deletes students when they graduate, i.e., when their grade is updated to exceed 12. In addition, write a trigger so when a student is moved ahead one grade, then so are all of his or her friends.

CREATE TRIGGER graduate
AFTER UPDATE ON Highschooler
BEGIN
    DELETE FROM Highschooler
    WHERE grade > 12;
END;
|
CREATE TRIGGER advance
AFTER UPDATE ON Highschooler
BEGIN
    UPDATE Highschooler
    SET grade = grade + 1
    WHERE ID in (SELECT distinct ID2 from Friend where ID1 = NEW.ID);
END;

#3. Write a trigger to enforce the following behavior: If A liked B but is updated to A liking C instead, and B and C were friends, make B and C no longer friends. Don't forget to delete the friendship in both directions, and make sure the trigger only runs when the "liked" (ID2) person is changed but the "liking" (ID1) person is not changed.

CREATE TRIGGER newLike
AFTER UPDATE ON Likes
BEGIN
    DELETE FROM Friend
    WHERE ID1 = NEW.ID2 AND ID2 = OLD.ID2 AND OLD.ID2 <> NEW.ID2 AND OLD.ID1 = NEW.ID1;
    DELETE FROM Friend
    WHERE ID1 = OLD.ID2 AND ID2 = NEW.ID2 AND OLD.ID2 <> NEW.ID2 AND OLD.ID1 = NEW.ID1;
END;
