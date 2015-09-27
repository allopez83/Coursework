#1. Find the titles of all movies directed by Steven Spielberg.

SELECT title
FROM Movie
WHERE director = 'Steven Spielberg';

#2. Find all years that have a movie that received a rating of 4 or 5, and sort them in increasing order.

SELECT year
FROM Movie NATURAL JOIN Rating
WHERE stars >= 4
UNION
SELECT year
FROM Movie NATURAL JOIN Rating
WHERE stars >= 4
ORDER BY year ASC;

    # "ORDER BY ... ASC/DESC" auto orders elements for you

#3. Find the titles of all movies that have no ratings. 

SELECT title
FROM Movie
WHERE (Movie.mID) not IN (SELECT mID FROM Rating)


#4. Some reviewers didn't provide a date with their rating. Find the names of all reviewers who have ratings with a NULL value for the date.

SELECT name
FROM Reviewer
WHERE rID IN (SELECT rID FROM Rating WHERE ratingDate is NULL)

    # "IN" operator finds all matches while = only finds for first element

#5. Write a query to return the ratings data in a more readable format: reviewer name, movie title, stars, and ratingDate. Also, sort the data, first by reviewer name, then by movie title, and lastly by number of stars. 

SELECT DISTINCT name, title, stars, ratingDate
FROM Reviewer NATURAL JOIN (Movie NATURAL JOIN Rating)

#6. For all cases where the same reviewer rated the same movie twice and gave it a higher rating the second time, return the reviewer's name and the title of the movie.

SELECT name, title
FROM (
        SELECT rID, mID, MAX(stars)
        FROM (
            SELECT rID, mID, MAX(stars)
            FROM (
                SELECT *
                FROM Rating
                WHERE rID IN (
                        SELECT rID FROM Rating
                        GROUP BY rID, mID
                        HAVING COUNT(*) > 1
                    )
            )
            WHERE mID IN (
                    SELECT mID
                    FROM Rating
                    GROUP BY rID, mID
                    HAVING COUNT(*) > 1
            )
        )
        WHERE ratingDate IN (
            SELECT MAX(ratingDate)
            FROM  (
                SELECT *
                FROM Rating
                GROUP BY rID, mID
                HAVING COUNT(*) > 1
            )
            GROUP BY rID
        )
    NATURAL JOIN
    Movie
    NATURAL JOIN
    Reviewer
)

#7. For each movie that has at least one rating, find the highest number of stars that movie received. Return the movie title and number of stars. Sort by movie title.

Select title, max(stars)
FROM Movie NATURAL JOIN Rating
GROUP BY mID
ORDER BY title ASC;

#8. List movie titles and average ratings, from highest-rated to lowest-rated. If two or more movies have the same average rating, list them in alphabetical order. 

SELECT title, avg(stars)
FROM Movie NATURAL JOIN Rating
GROUP BY mID
ORDER BY avg(stars) DESC, title ASC;

#9. Find the names of all reviewers who have contributed three or more ratings. (As an extra challenge, try writing the query without HAVING or without COUNT.) 

SELECT name
FROM Reviewer NATURAL JOIN Rating
GROUP BY rID
HAVING COUNT(mID)>=3
