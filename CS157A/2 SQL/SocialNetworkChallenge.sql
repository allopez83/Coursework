#1. Find all students who do not appear in the Likes table (as a student who likes or is liked) and return their names and grades. Sort by grade, then by name within each grade.

SELECT name, grade
FROM Highschooler
WHERE NOT EXISTS (
    SELECT *
    FROM Likes
    WHERE (ID = ID1 OR ID = ID2)
)
ORDER BY grade ASC

#2. For each student A who likes a student B where the two are not friends, find if they have a friend C in common (who can introduce them!). For all such trios, return the name and grade of A, B, and C.

SELECT nameA, gradeA, nameB, gradeB, nameC, gradeC
FROM (
    SELECT ID1 as idA, nameA, gradea, idB, nameB, gradeB, ID2 as idc, name as nameC, grade as gradeC
    FROM (
        (
            (
                SELECT idA as ID1, nameA, gradeA, ID as idB, name as nameB, grade as gradeB
                FROM
                    (
                        SELECT
                            ID AS idA, name AS nameA, grade AS gradeA, idB AS ID
                        FROM
                            (SELECT ID1 AS ID, ID2 AS idB FROM Likes) NATURAL JOIN Highschooler
                    )
                    NATURAL JOIN
                    Highschooler
                WHERE NOT EXISTS (
                    SELECT *
                    FROM Friend
                    WHERE (idA = Friend.ID1 AND idB = Friend.ID2)
                )
            )
        ) NATURAL JOIN Friend NATURAL JOIN (SELECT ID as ID2, name, grade FROM Highschooler)
    )
    WHERE EXISTS (
        SELECT *
        FROM Friend
        WHERE idC = Friend.ID1 AND idB = Friend.ID2
    )
)
