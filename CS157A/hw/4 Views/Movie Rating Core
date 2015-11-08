# Core

#1. Write an instead-of trigger that enables updates to the title attribute of view LateRating. Policy: Updates to attribute title in LateRating should update Movie.title for the corresponding movie. (You may assume attribute mID is a key for table Movie.) Make sure the mID attribute of view LateRating has not also been updated -- if it has been updated, don't make any changes. Don't worry about updates to stars or ratingDate.

CREATE TRIGGER TitleUpdate
INSTEAD OF UPDATE OF title ON LateRating
BEGIN
    UPDATE Movie
    SET title = NEW.title
    WHERE Movie.mID = OLD.mID AND NEW.mID = OLD.mID AND;
END;

#2. Write an instead-of trigger that enables updates to the stars attribute of view LateRating. Policy: Updates to attribute stars in LateRating should update Rating.stars for the corresponding movie rating. (You may assume attributes [mID,ratingDate] together are a key for table Rating.) Make sure the mID and ratingDate attributes of view LateRating have not also been updated -- if either one has been updated, don't make any changes. Don't worry about updates to title.

CREATE TRIGGER StarChange
INSTEAD OF UPDATE OF stars ON LateRating
BEGIN
    UPDATE Rating
    SET stars = NEW.stars
    WHERE Rating.mID = OLD.mID AND NEW.mID = OLD.mID AND ratingDate > '2011-01-20' AND NEW.ratingDate = OLD.ratingDate;
END;

#3. Write an instead-of trigger that enables updates to the mID attribute of view LateRating. Policy: Updates to attribute mID in LateRating should update Movie.mID and Rating.mID for the corresponding movie. Update all Rating tuples with the old mID, not just the ones contributing to the view. Don't worry about updates to title, stars, or ratingDate.

CREATE TRIGGER IDChange
INSTEAD OF UPDATE OF mID ON LateRating
FOR EACH ROW
BEGIN
    UPDATE Movie
    SET mID = NEW.mID
    WHERE Movie.mID = OLD.mID;
    UPDATE Rating
    SET mID = NEW.mID
    WHERE Rating.mID = OLD.mID;
END;

#4. Write an instead-of trigger that enables deletions from view HighlyRated. Policy: Deletions from view HighlyRated should delete all ratings for the corresponding movie that have stars > 3.

CREATE TRIGGER RatingDeletion
INSTEAD OF DELETE ON HighlyRated
FOR EACH ROW
BEGIN
    DELETE FROM Rating
    WHERE stars > 3 AND mID = OLD.mID;
END;

#5. Write an instead-of trigger that enables deletions from view HighlyRated. Policy: Deletions from view HighlyRated should update all ratings for the corresponding movie that have stars > 3 so they have stars = 3.

CREATE TRIGGER RatingDeletion
INSTEAD OF DELETE ON HighlyRated
FOR EACH ROW
BEGIN
    UPDATE Rating
    SET stars = 3
    WHERE stars >3 AND mID = OLD.mID;
END;

# Challenge

#1. Write a single instead-of trigger that enables simultaneous updates to attributes mID, title, and/or stars in view LateRating. Combine the view-update policies of the questions 1-3 in the core set, with the exception that mID may now be updated. Make sure the ratingDate attribute of view LateRating has not also been updated -- if it has been updated, don't make any changes.

CREATE TRIGGER LateRatingUpdate
INSTEAD OF UPDATE ON LateRating
BEGIN
    UPDATE Movie
    SET title = NEW.title, mID = NEW.mID
    WHERE Movie.mID = OLD.mID AND NEW.ratingDate = OLD.ratingDate;

    UPDATE Rating
    SET stars = NEW.stars
    WHERE Rating.ratingDate = OLD.ratingDate AND ratingDate > '2011-01-20' AND NEW.ratingDate = OLD.ratingDate;
    
    UPDATE Rating
    SET mID = NEW.mID
    WHERE Rating.mID = OLD.mID AND NEW.ratingDate = OLD.ratingDate;
END;

#2. Write an instead-of trigger that enables insertions into view HighlyRated. Policy: An insertion should be accepted only when the (mID,title) pair already exists in the Movie table. (Otherwise, do nothing.) Insertions into view HighlyRated should add a new rating for the inserted movie with rID = 201, stars = 5, and NULL ratingDate.

CREATE TRIGGER InsertHighlyRated
INSTEAD OF INSERT ON HighlyRated
FOR EACH ROW
WHEN (NEW.mID IN (SELECT mID FROM Movie) AND NEW.title IN (SELECT title FROM Movie))
BEGIN
    INSERT INTO Rating VALUES (201, NEW.mID, 5, NULL);
END;

#3. Write an instead-of trigger that enables insertions into view NoRating. Policy: An insertion should be accepted only when the (mID,title) pair already exists in the Movie table. (Otherwise, do nothing.) Insertions into view NoRating should delete all ratings for the corresponding movie.

CREATE TRIGGER InsertNoRating
INSTEAD OF INSERT ON NoRating
FOR EACH ROW
WHEN (NEW.mID IN (SELECT mID FROM Movie) AND NEW.title IN (SELECT title FROM Movie))
BEGIN
    INSERT INTO NoRating VALUES (201, NEW.title);
    DELETE FROM Rating WHERE mID = NEW.mID;
END;
