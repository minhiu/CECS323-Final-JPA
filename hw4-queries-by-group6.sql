#Hoang Nguyen
#Matt Ortiz
#Hieu Pham

#HOMEWORK 4

#1. Show all studios and the max budget they spent on a single movie. 
#(Satisfy these requirement: Outer Join(1/2), Aggregate function(1/3))

SELECT s.name, MAX(m.budget) AS maximum_spent
	FROM studios s LEFT OUTER JOIN 
		(moviestudios ms INNER JOIN movies m ON m.movieid = ms.movieid)
		ON s.studioid = ms.studioid
	GROUP BY s.name;

#2. Show all movies and the number of countries they are playing in.
#(Satisfy these requirements: Outer Join(2/2), Aggregate Function with NULL values(2/3))

SELECT m.datereleased, m.title, COUNT(DISTINCT country) AS 
 	number_of_countries
	FROM movies m LEFT OUTER JOIN 
		(movieshowings ms INNER JOIN theaters t ON ms.tid = t.tid)
		ON m.movieid = ms.movieid 
	GROUP by m.datereleased, m.title;

#3. Show the studios that have a total earnings of over $3,000,000 
#for all the movies they produced. Display the studio name and total earnings.
#(Satisfy these requirements : Aggregate Function with Having Clause
#(3/3))

SELECT s.name, SUM(m.grossearnings) AS total_earnings
	FROM studios s INNER JOIN moviestudios ms 
			ON s.studioid = ms.studioid
				   INNER JOIN movies m 
			ON m.movieid = ms.movieid
	GROUP BY s.name
		HAVING SUM(m.grossearnings) >= 3000000;

#4. Show movies that are only playing in Los Angeles. Display the movie and theater it is playing in.
#(Satisfy these requirements: Universal Quantifier (1/1) and Subquery(1/3))

SELECT m.title, t.name
	FROM movies m INNER JOIN movieshowings ms 
			ON m.movieid = ms.movieid	
	WHERE NOT EXIST (SELECT ms2.movieid
					 	FROM movieshowings ms2 INNER JOIN theaters t
													ON ms2.tid = t.tid
				 		WHERE ms.tid = t2.mid AND NOT (t.city = ‘Los Angeles’)
					 );

#5. Show all studio(s) that produced a movie with the lowest TomatoMeter score.
#(Satisfy these requirements: MIN and Subquery(2/3))

SELECT s.name, m.title, m.tomatoMeter
	FROM studios s INNER JOIN moviestudios ms 
						ON s.studioid = ms.studioid
				   INNER JOIN movies m 
						ON m.movieid = ms.movieid 
	WHERE tomatometer = (SELECT MIN(tomatometer)
		FROM movies);

#6. Show movies that are shown in America and were not produced by a studio in America and have a maximum tomatometer.
#(Satisfy these requirements: Set Difference (1/1) and Subquery(MAX)(3/3))

SELECT m.title
	FROM studios s INNER JOIN moviestudios ms 
						ON s.studioid = ms.studioid
				   INNER JOIN movies m 
						ON m.movieid = ms.movieid
	WHERE s.country = “America” AND tomatometer = 
			(SELECT MAX(tomatoMeter) FROM movies)
EXCEPT 
SELECT m.title
	FROM movies m INNER JOIN movieshowings ms 
						ON m.movieid = ms.movieid	
				  INNER JOIN theaters t 
						ON ms.tid = t.tid 
	WHERE t.country = “America”;


