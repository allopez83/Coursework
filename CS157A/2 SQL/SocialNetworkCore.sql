#1. Find the names of all students who are friends with someone named Gabriel.

SELECT name
FROM (
    (
        SELECT ID2 as ID
        FROM Friend
        WHERE ID1 = 1911 OR ID1 = 1689
    )

    NATURAL JOIN

    Highschooler
)

#2. For every student who likes someone 2 or more grades younger than themselves, return that student's name and grade, and the name and grade of the student they like.

SELECT n1, g1, n2, g2
FROM (
    (
        SELECT name as n1, grade as g1, ID2 as likes
        FROM (
            Highschooler
            CROSS JOIN
            Likes
            ON ID = ID1
        )
    )
    CROSS JOIN
    (
        SELECT name as n2, grade as g2, ID2 as ID
        FROM (
            Highschooler
            CROSS JOIN
            Likes
            ON ID = ID2
        )
    )
    ON (g1-g2>1 AND likes = ID)
)

#3. For every pair of students who both like each other, return the name and grade of both students. Include each pair only once, with the two names in alphabetical order. 

SELECT n1, g1, this1, likes, n2, g2, this2, likedBy
FROM (
    (
        SELECT name as n1, grade as g1, ID1 as this1, ID2 as likes
        FROM (
            Highschooler
            CROSS JOIN
            Likes
            ON ID = ID1
        )
    )
    CROSS JOIN
    (
        SELECT name as n2, grade as g2, ID2 as this2, ID1 as likedBy
        FROM (
            Highschooler
            CROSS JOIN
            Likes
            ON ID = ID2
        )
    )
    ON (this1 = likedBy AND this2 = likes)
)


SELECT *
FROM (
    SELECT name as n1, grade as g1, ID1 as this1, ID2 as likes
    FROM (
        Highschooler
        CROSS JOIN
        Likes
        ON ID = ID1
    )
)
WHERE EXISTS (
    SELECT *
    FROM (
        SELECT *
        FROM (
            Highschooler
            CROSS JOIN
            Likes
            ON ID = ID1
        )
    )
    WHERE likes = ID1
)

#4. Find names and grades of students who only have friends in the same grade. Return the result sorted by grade, then by name within each grade. 

SELECT name, grade
FROM Highschooler
WHERE NOT EXISTS (
    SELECT *
    FROM (
        SELECT ID1, name1, grade1
        FROM (
            (
                (SELECT ID as ID1, name as name1, grade as grade1 FROM Highschooler)
                NATURAL JOIN
                Friend
            )
            NATURAL JOIN
            (
                SELECT ID as ID2, name as name2, grade as grade2 FROM Highschooler
            )
        )
        WHERE (grade1<>grade2)
    )
    WHERE ID = ID1
)
ORDER BY grade ASC, name ASC

#5. Find the name and grade of all students who are liked by more than one other student.

SELECT name, grade
FROM (
    Highschooler NATURAL JOIN (SELECT ID2 as ID FROM Likes)
)
GROUP BY ID
HAVING COUNT(*) > 1