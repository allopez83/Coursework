#1. For each movie, return the title and the 'rating spread', that is, the difference between highest and lowest ratings given to that movie. Sort by rating spread from highest to lowest, then by movie title. 

SELECT title, spread
FROM (
    (
        SELECT mID, MAX(stars) - MIN(stars) as spread
        FROM Rating
        GROUP BY mID
    )
    NATURAL JOIN Movie
)
ORDER BY spread DESC, title ASC;

#2. Find the difference between the average rating of movies released before 1980 and the average rating of movies released after 1980. (Make sure to calculate the average rating for each movie, then the average of those averages for movies before 1980 and movies after. Don't just calculate the overall average rating before and after 1980.) 

SELECT
(
    (
        SELECT avg(avgRating)
        FROM (
            SELECT title, avg(stars) as avgRating
            FROM (Rating NATURAL JOIN Movie)
            WHERE year < 1980
            GROUP BY mID
        )
    )
    -
    (
        SELECT avg(avgRating)
        FROM (
            SELECT title, avg(stars) as avgRating
            FROM (Rating NATURAL JOIN Movie)
            WHERE year > 1980
            GROUP BY mID
        )
    )
)

#3. Some directors directed more than one movie. For all such directors, return the titles of all movies directed by them, along with the director name. Sort by director name, then movie title. (As an extra challenge, try writing the query both with and without COUNT.) 


SELECT title, director
FROM (
    Movie NATURAL JOIN
    (
        SELECT director
        FROM Movie
        GROUP BY director
        HAVING COUNT(director) > 1
    )
)
ORDER BY director ASC, title ASC

#4. Find the movie(s) with the highest average rating. Return the movie title(s) and average rating. (Hint: This query is more difficult to write in SQLite than other systems; you might think of it as finding the highest average rating and then choosing the movie(s) with that average rating.) 

SELECT title, avgRating
FROM (
    SELECT  title, avg(stars) as avgRating
    FROM Movie NATURAL JOIN Rating
    GROUP BY mID
)
ORDER BY avgRating DESC
LIMIT 1

#5. Find the movie(s) with the lowest average rating. Return the movie title(s) and average rating. (Hint: This query may be more difficult to write in SQLite than other systems; you might think of it as finding the lowest average rating and then choosing the movie(s) with that average rating.)

SELECT *
FROM (
    (
        SELECT  title, avg(stars) as stars
        FROM Movie NATURAL JOIN Rating
        GROUP BY mID
    )
    NATURAL JOIN
    (
        SELECT stars
        FROM (
            SELECT  title, avg(stars) as stars
            FROM Movie NATURAL JOIN Rating
            GROUP BY mID
        )
        ORDER BY stars ASC
        LIMIT 1
    )
)

#6. For each director, return the director's name together with the title(s) of the movie(s) they directed that received the highest rating among all of their movies, and the value of that rating. Ignore movies whose director is NULL.


SELECT director, title, MAX(stars) as stars
FROM (
    SELECT director, title, MAX(stars) as stars
    FROM Movie NATURAL JOIN Rating
    WHERE director IS NOT NULL
    GROUP BY mID
)
GROUP BY director
