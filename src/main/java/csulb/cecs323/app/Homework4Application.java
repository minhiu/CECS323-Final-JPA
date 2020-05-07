/*
 * Licensed under the Academic Free License (AFL 3.0).
 *     http://opensource.org/licenses/AFL-3.0
 *
 *  This code is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, other than educational.
 *
 *  2018 Alvaro Monge <alvaro.monge@csulb.edu>
 *
 */

package csulb.cecs323.app;

import csulb.cecs323.model.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 * A simple application to demonstrate how to persist an object in JPA.
 *
 * This is for demonstration and educational purposes only.
 */
public class Homework4Application {
   private EntityManager entityManager;

   private static final Logger LOGGER = Logger.getLogger(Homework4Application.class.getName());

   public Homework4Application(EntityManager manager) {
      this.entityManager = manager;
   }

   public static void main(String[] args) {
      LOGGER.fine("Creating EntityManagerFactory and EntityManager");
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("homework4_PU");
      EntityManager manager = factory.createEntityManager();
      Homework4Application hw4application = new Homework4Application(manager);


      // Any changes to the database need to be done within a transaction.
      // See: https://en.wikibooks.org/wiki/Java_Persistence/Transactions

      LOGGER.fine("Begin of Transaction");
      EntityTransaction tx = manager.getTransaction();

      tx.begin();

      try {
         hw4application.createMovieEntity();
         hw4application.createStudioEntity();
         hw4application.createTheaterEntity();
         hw4application.createMovieShowingEntity();
         hw4application.persistData();

         hw4application.loadInitialData(manager);

      } catch (Exception e) {
         e.printStackTrace();
      }

      tx.commit();
      LOGGER.fine("End of Transaction");
   }

   /**
    * Create Movie objects to the database.
    */
   public void createMovieEntity() {
      /**
       * MAPPING MOVIES TO STUDIOS
       */
      IntStream.range(0, INITIAL_MOVIES.length).forEach(i -> {
         if (i >= 0 && i <= 2 || i == 11) {
            INITIAL_MOVIES[i].addStudio(INITIAL_STUDIOS[0]);
         } else if (i >= 3 && i <= 4) {
            INITIAL_MOVIES[i].addStudio(INITIAL_STUDIOS[1]);
         } else if (i >= 5 && i <= 9) {
            INITIAL_MOVIES[i].addStudio(INITIAL_STUDIOS[2]);
         } else if (i == 10) {
            INITIAL_MOVIES[i].addStudio(INITIAL_STUDIOS[3]);
         } else if (i == 12) {
            INITIAL_MOVIES[i].addStudio(INITIAL_STUDIOS[4]);
         }
      });

      /**
       * MAPPING MOVIES TO MOVIESHOWINGS
       */
      IntStream.range(0, INITIAL_MOVIESHOWING.length).forEach(i -> {
         if (i >= 0 && i <= 4) {
            INITIAL_MOVIES[i].addMovieShowing(INITIAL_MOVIESHOWING[i]);
         } else if (i >= 5 && i <= 9) {
            INITIAL_MOVIES[i - 5].addMovieShowing(INITIAL_MOVIESHOWING[i]);
         } else if (i == 10) {
            INITIAL_MOVIES[11].addMovieShowing(INITIAL_MOVIESHOWING[i]);
         } else if (i == 11) {
            INITIAL_MOVIES[12].addMovieShowing(INITIAL_MOVIESHOWING[i]);
         }
      });

      /**
       * MAPPING SEQUEL (IRON MAN -> IRON MAN 2)
       */
      INITIAL_MOVIES[5].setSequel(INITIAL_MOVIES[11]);
   }

   /**
    * Create Studio objects to the database.
    */
   public void createStudioEntity() {
      /**
       * MAPPING STUDIOS TO MOVIES
       */
      IntStream.range(0, INITIAL_MOVIES.length).forEach(i -> {
         if (i >= 0 && i <= 2 || i == 12) {
            INITIAL_STUDIOS[0].addMovie(INITIAL_MOVIES[i]);
         } else if (i >= 3 && i <= 4) {
            INITIAL_STUDIOS[1].addMovie(INITIAL_MOVIES[i]);
         } else if (i >= 5 && i <= 9) {
            INITIAL_STUDIOS[2].addMovie(INITIAL_MOVIES[i]);
         } else if (i == 10) {
            INITIAL_STUDIOS[3].addMovie(INITIAL_MOVIES[i]);
         } else if (i == 11 || i == 12) {
            INITIAL_STUDIOS[4].addMovie(INITIAL_MOVIES[i]);
         }
      });
   }

   /**
    * Create Theater objects to the database.
    */
   public void createTheaterEntity() {
      /**
       * MAPPING THEATERS TO MOVIESHOWINGS
       */
      IntStream.range(0, INITIAL_MOVIESHOWING.length).forEach(i -> {
         if (i == 0 || i == 4 || i == 10) {
            INITIAL_THEATERS[0].addMovieShowing(INITIAL_MOVIESHOWING[i]);
         } else if (i == 6 || i == 8) {
            INITIAL_THEATERS[1].addMovieShowing(INITIAL_MOVIESHOWING[i]);
         } else if (i == 1 || i == 3) {
            INITIAL_THEATERS[2].addMovieShowing(INITIAL_MOVIESHOWING[i]);
         } else if (i == 5 || i == 7 || i == 11) {
            INITIAL_THEATERS[3].addMovieShowing(INITIAL_MOVIESHOWING[i]);
         } else if (i == 2 || i == 9) {
            INITIAL_THEATERS[4].addMovieShowing(INITIAL_MOVIESHOWING[i]);
         }
      });
   }

   /**
    * Create Movie Showing objects to the database.
    */
   public void createMovieShowingEntity() {
      /**
       * MAPPING MOVIESHOWINGS TO THEATERS
       */
      IntStream.range(0, INITIAL_MOVIESHOWING.length).forEach(i -> {
         if (i == 0 || i == 4 || i == 10) {
            INITIAL_MOVIESHOWING[i].setTheater(INITIAL_THEATERS[0]);
         } else if (i == 6 || i == 8) {
            INITIAL_MOVIESHOWING[i].setTheater(INITIAL_THEATERS[1]);
         } else if (i == 1 || i == 3) {
            INITIAL_MOVIESHOWING[i].setTheater(INITIAL_THEATERS[2]);
         } else if (i == 5 || i == 7 || i == 11) {
            INITIAL_MOVIESHOWING[i].setTheater(INITIAL_THEATERS[3]);
         } else if (i == 2 || i == 9) {
            INITIAL_MOVIESHOWING[i].setTheater(INITIAL_THEATERS[4]);
         }
      });
      /**
       * MAPPING MOVIESHOWINGS TO MOVIES
       */
      IntStream.range(0, INITIAL_MOVIESHOWING.length).forEach(i -> {
         if (i >= 0 && i <= 4) {
            INITIAL_MOVIESHOWING[i].setMovie(INITIAL_MOVIES[i]);
         } else if (i >= 5 && i <= 9){
            INITIAL_MOVIESHOWING[i].setMovie(INITIAL_MOVIES[i - 5]);
         } else if (i == 10) {
            INITIAL_MOVIESHOWING[i].setMovie(INITIAL_MOVIES[11]);
         } else if (i == 11) {
            INITIAL_MOVIESHOWING[i].setMovie(INITIAL_MOVIES[12]);
         }
      });
   }

   /**
    * Persist all data
    */
   private void persistData() {
      /**
       * Persist all Movies
       */
      for (Movie movie : INITIAL_MOVIES) {
         entityManager.persist(movie);
         System.out.println(movie.getTitle());
      }
      /**
       * Persist all Studios
       */
      for (Studio studio : INITIAL_STUDIOS) {
         entityManager.persist(studio);
      }
      /**
       * Persist all Theaters
       */
      for (Theater theater : INITIAL_THEATERS) {
         entityManager.persist(theater);
      }
      /**
       * Persist all MovieShowings
       */
      for (MovieShowing movieShowing : INITIAL_MOVIESHOWING) {
         entityManager.persist(movieShowing);
      }
   }


   private void runQueryOne(EntityManager manager) {
      Query query = manager.createNativeQuery("SELECT s.name, MAX (m.budget) AS Maximum_Spent\n" +
              "FROM Studios s LEFT OUTER JOIN \n" +
              "(moviestudios ms INNER JOIN movies m ON m.id = ms.movie_Id)\n" +
              "ON s.id = ms.studio_Id\n" +
              "   \tGROUP BY s.name");
      List<Object[]> queryResults = query.getResultList();

      for (Object[] obj : queryResults) {
         for (int i = 0; i < obj.length; i += 2) {
            System.out.printf("%2s %19s %2s %2s $%2d", "Studio name: ", obj[i] ,
                    "|" , " Highest budget: ", obj[i+1]);
            System.out.println();
         }
      }
      System.out.println();
   }

   private void runQueryTwo(EntityManager manager) {
      Query query = manager.createNativeQuery("SELECT m.dateReleased, m.title, COUNT (DISTINCT COUNTRY) AS \n" +
              " \t\tNumberOfCountries\n" +
              " \tFROM movies m LEFT OUTER JOIN \n" +
              "(movieShowings ms INNER JOIN theaters t ON ms.theater_Id = t.id)\n" +
              "ON m.id = ms.movie_Id \n" +
              "\tGROUP by m.dateReleased, m.title\n");

      List<Object[]> queryResults = query.getResultList();

      for (Object[] obj : queryResults) {
         for (int i = 0; i < obj.length; i+=3) {
            System.out.printf("%2s %2s %2s %2s %28s %2s %2s %2s", "Date released: ", obj[i] ,
                    "|" , " Title: ", obj[i+1], "|", " Country played: ", obj[i+2]);
            System.out.println();
         }
      }
      System.out.println();
   }

   private void runQueryThree(EntityManager manager) {
      Query query = manager.createNativeQuery("SELECT s.name, m.title, m.tomatoMeter\n" +
              "\tFROM Studios s INNER JOIN MovieStudios ms \n" +
              "ON s.id = ms.studio_Id\n" +
              "\t\t      INNER JOIN movies m \n" +
              "ON m.id = ms.movie_Id \n" +
              "\tWHERE tomatoMeter = (SELECT MIN(tomatoMeter)\n" +
              "FROM movies)");
      List<Object[]> queryResults = query.getResultList();

      for (Object[] obj : queryResults) {
         for (int i = 0; i < obj.length; i += 3) {
            System.out.printf("%2s %2s %2s %2s %28s %2s %2s %2s%%", "Studio: ", obj[i], "|", " Title: ",
                    obj[i + 1], "|", " Tomato Score: ", obj[i + 2]);
            System.out.println();
         }
      }

      System.out.println();
   }

   private void insertMovie(EntityManager manager) {
      Query query;
      Scanner in = new Scanner(System.in);
      String movieName;
      int year;
      int month;
      int dayOfMoth;
      int ratingSelection;
      MPAARating rating;
      int runTime;
      int budget;
      int grossEarnings;
      int tomatoMeter;
      int numOfStudios;
      int studioSelect;
      System.out.print("Please enter a Movie Name: ");
      movieName = in.nextLine();
      System.out.print("Please enter the year it released: ");
      year = in.nextInt();
      System.out.print("Please enter the month it released: ");
      month = in.nextInt();
      System.out.print("Please enter the day it released:");
      dayOfMoth = in.nextInt();
      System.out.print("Select an MPAARating \n"
              +"1. G\n" +
              "2. PG\n" +
              "3. PG13\n" +
              "4. R\n" +
              "5. NC17\n");
      ratingSelection = in.nextInt();
      if (ratingSelection == 1) rating = MPAARating.G;
      if (ratingSelection == 2) rating = MPAARating.PG;
      if (ratingSelection == 3) rating = MPAARating.PG13;
      if (ratingSelection == 4) rating = MPAARating.R;
      else rating = MPAARating.NC17;
      System.out.print("Please enter the runtime for the movie:");
      runTime = in.nextInt();
      System.out.print("Please enter the budget for the movie:");
      budget = in.nextInt();
      System.out.print("Please enter the gross earnings for the movie:");
      grossEarnings = in.nextInt();
      System.out.print("Please enter the tomato meter rating for the movie:");
      tomatoMeter = in.nextInt();
      Movie newMovie = new Movie (movieName, new GregorianCalendar(year, month, dayOfMoth), rating, runTime, budget, grossEarnings, tomatoMeter);
      newMovie.addStudio (INITIAL_STUDIOS[0]);

      try {

         entityManager.persist(newMovie);
         query = manager.createNativeQuery("SELECT * FROM movies");
         List<Object[]> list = query.getResultList();
         //entityManager.flush();

         System.out.print("How many studios produced this movie? ");
         numOfStudios = in.nextInt();

         List<Studio> newMovieStudios = new ArrayList<>();

         while (numOfStudios != 0) {
            for (int i = 0 ; i < INITIAL_STUDIOS.length ; i ++) {
               System.out.println((i+1) + ". " + INITIAL_STUDIOS[i].getName());
            }
            System.out.println((INITIAL_STUDIOS.length + 1) + ". New Studio");
            studioSelect = in.nextInt();
            if (studioSelect <= INITIAL_STUDIOS.length && studioSelect > 0)
               newMovieStudios.add(INITIAL_STUDIOS[studioSelect - 1]);
            else {
               String studioName;
               Country studioCountry;
               int countryNum;
               System.out.print("Please enter the studio name: ");
               in.nextLine();
               studioName = in.nextLine();
               System.out.println("Please select the country the studio is in: ");
               int i = 1;
               for (Country country : Country.values()) {
                  System.out.println((i++) + ". " + country.toString());
               }
               countryNum = in.nextInt();
               if (countryNum == 1)
                  studioCountry = Country.US;
               if (countryNum == 2)
                  studioCountry = Country.UK;
               if (countryNum == 3)
                  studioCountry = Country.CA;
               if (countryNum == 4)
                  studioCountry = Country.MX;
               else
                  studioCountry = Country.JP;

               Studio newStudio = new Studio(studioName,studioCountry);
               try {
                  entityManager.persist(newStudio);
                  //entityManager.flush();
                  newMovieStudios.add(newStudio);
               } catch (Exception e)
               {
                  //e.printStackTrace();
                  //System.out.println("This studio already exisit");
                  //continue;
               }
            }
            numOfStudios--;
         }
         newMovie.setStudios(newMovieStudios);
         for (Studio studio : newMovieStudios) {
            studio.addMovie(newMovie);
         }
         entityManager.persist(newMovie);
      }
      catch (Exception e) {
         //e.printStackTrace();
         System.out.println("Movie already exist");

      }
   }
   
   private void loadInitialData(EntityManager manager) { 

      Scanner input = new Scanner(System.in);
      int userInput, userInputQuery;
      Query query;
      List<Object[]> queryResults;
      System.out.println("Welcome to JPA by Group 6!");
      do {
         System.out.println("Please select an option below:\n" +
                 "1. Run queries.\n" +
                 "2. Insert Movie into tables.\n" +
                 "3. Delete data from tables.\n" +
                 "4. Exit the application.");

         userInput = input.nextInt();

         if (userInput == 1) {
            System.out.println("Select one of these 3 queries:\n" +
                    "1. Show all studios and the highest budget that they spent on a single movie.\n" +
                    "2. Show all movies and the number of countries they are playing in\n" +
                    "3. Show all studio(s) that produced a movie with the lowest TomatoMeter score");
            userInputQuery = input.nextInt();

            if (userInputQuery == 1) { runQueryOne(manager); }
            else if (userInputQuery == 2) { runQueryTwo(manager); }
            else if (userInputQuery == 3) { runQueryThree(manager); }

         } else if (userInput == 2) {
            insertMovie(manager);
         } else if (userInput == 3) {

         } else if (userInput == 4) {
            System.out.println("Goodbye!");
            System.exit(0);
         }

      }

      while (userInput != 4);
   }


   /**
    * Movie objects to be loaded to the database initially.
    */
   private static final Movie[] INITIAL_MOVIES = new Movie[]{
           new Movie("Ratatouille", new GregorianCalendar(2005, 0, 1), MPAARating.G, 111, 150000000, 620700000, 96),
           new Movie("Up", new GregorianCalendar(2009, 5, 29), MPAARating.G, 96, 175000000, 735100000, 98),
           new Movie("The Incredibles", new GregorianCalendar(2004, 11, 5), MPAARating.G, 116, 92000000, 633000000, 97),
           new Movie("The Dark Knight", new GregorianCalendar(2008, 7, 18), MPAARating.PG13, 152, 180000000, 1005000000, 94),
           new Movie("Joker", new GregorianCalendar(2019, 10, 4), MPAARating.R, 122, 62500000, 1074000000, 68),
           new Movie("Iron Man", new GregorianCalendar(2008, 5, 2), MPAARating.PG13, 126, 1400000, 5853000, 94),
           new Movie("Thor", new GregorianCalendar(2011, 5, 6), MPAARating.PG13, 114, 1500000, 4493000, 77),
           new Movie("Doctor Strange", new GregorianCalendar(2016, 10, 20), MPAARating.PG13, 115, 2366000, 6777000, 89),
           new Movie("Black Panther", new GregorianCalendar(2018, 2, 16), MPAARating.PG13, 134, 2000000, 1347000000, 97),
           new Movie("Captain Marvel", new GregorianCalendar(2019, 3, 8), MPAARating.PG13, 124, 1750000, 1128000000, 78),
           new Movie("Murder on the Orient Express", new GregorianCalendar(2017, 11, 10), MPAARating.PG13, 114, 55000000, 352800000, 61),
           new Movie("Iron Man 2", new GregorianCalendar(2010, 4, 26), MPAARating.PG13, 119, 200000000, 623900000, 73),
           new Movie("Spider-Man: Homecoming", new GregorianCalendar(2017, 7,7 ), MPAARating.PG13, 133, 175000000, 880200000,92)
   };

   /**
    * Studio objects to be loaded to the database initially.
    */
   private static final Studio[] INITIAL_STUDIOS = new Studio[]{
           new Studio("Marvel Studios", Country.US),
           new Studio("Warner Bros Studios", Country.US),
           new Studio("Pixar Studios", Country.US),
           new Studio("Longcross Studios", Country.UK),
           new Studio("Columbia Pictures", Country.US)
   };

   /**
    * Theater objects to be loaded to the database initially.
    */
   private static final Theater[] INITIAL_THEATERS = new Theater[]{
           new Theater("AMC Southbay Galleria", "Redondo Beach", "Western", Country.US, 16, "3107937477"),
           new Theater("Beckenham", "Beckenham", "Euro", Country.UK, 6, "08001383315"),
           new Theater("ArcLight Cinemas - Hollywood", "Hollywood", "Western", Country.US, 15, "3236152550"),
           new Theater("Revue Cinema", "Toronto", "Western", Country.CA, 1, "4165319950"),
           new Theater("Toho Cinemas Roppongi Hills", "Tokyo", "Eastern", Country.JP, 5, "81357756090")
   };

   /**
    * MovieShowing objects to be loaded to the database initially.
    */
   private static final MovieShowing[] INITIAL_MOVIESHOWING = new MovieShowing[]{
           new MovieShowing(new GregorianCalendar(2008, 5, 2), new GregorianCalendar(2008, 8, 2)), //iron man
           new MovieShowing(new GregorianCalendar(2018, 2, 16), new GregorianCalendar(2018, 5, 16)), //black panther
           new MovieShowing(new GregorianCalendar(2011, 5, 1), new GregorianCalendar(2011, 8, 1)), //thor
           new MovieShowing(new GregorianCalendar(2016, 10, 20), new GregorianCalendar(2017, 1, 20)), //dr. strange
           new MovieShowing(new GregorianCalendar(2019, 3, 8), new GregorianCalendar(2019, 6, 8)), //captain marvel
//           new MovieShowing(new GregorianCalendar(2007, 6, 29), new GregorianCalendar(2007, 9, 29)), //ratatouille
//           new MovieShowing(new GregorianCalendar(2009, 5, 29), new GregorianCalendar(2009, 8, 29)), //up
//           new MovieShowing(new GregorianCalendar(2004, 11, 5), new GregorianCalendar(2005, 2, 5)), //the incredibles
//           new MovieShowing(new GregorianCalendar(2008, 7, 18), new GregorianCalendar(2008, 10, 18)),//the dark knight
//           new MovieShowing(new GregorianCalendar(2019, 10, 4), new GregorianCalendar(2020, 1, 4)), //the joker
//           new MovieShowing(new GregorianCalendar(2017, 11, 10), new GregorianCalendar(2018, 2, 6)) // murder orient express
           new MovieShowing(new GregorianCalendar(2008, 5, 2), new GregorianCalendar(2008, 8, 28)), //iron man
           new MovieShowing(new GregorianCalendar(2018, 2, 16), new GregorianCalendar(2018, 5, 6)), //black panther
           new MovieShowing(new GregorianCalendar(2011, 5, 1), new GregorianCalendar(2011, 8, 10)), //thor
           new MovieShowing(new GregorianCalendar(2016, 10, 20), new GregorianCalendar(2017, 1, 22)), //dr. strange
           new MovieShowing(new GregorianCalendar(2019, 3, 8), new GregorianCalendar(2019, 6, 18)), //captain marvel
           new MovieShowing(new GregorianCalendar(2010, 4, 26), new GregorianCalendar(2010, 7, 5)), //iron man 2 
           new MovieShowing(new GregorianCalendar(2017, 7, 7), new GregorianCalendar(2017, 10, 15))//SpiderMan: Homecoming
   };


}

