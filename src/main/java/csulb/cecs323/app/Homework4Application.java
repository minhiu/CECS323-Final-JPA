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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
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
         hw4application.loadInitialData();
      } catch (Exception e) {
         e.printStackTrace();
      }

      hw4application.createStudentEntity();

      tx.commit();
      LOGGER.fine("End of Transaction");

   }

   /**
    * Create and persist a Student object to the database.
    */
   public void createStudentEntity() {

      //Linking movie showings to their movies. Setting a movie to each movie showing
      for(int i = 0 ; i < INITIAL_MOVIESHOWING.length ; i ++)
      {
         INITIAL_MOVIESHOWING[i].setMovie(INITIAL_MOVIES[i]);
      }

      //Linking movies to their movie showings. Setting list of movieShowings for each movie
      List<List<MovieShowing>> listOfMovies = new ArrayList<>();
      for(int i = 0 ; i < INITIAL_MOVIES.length ; i++)
      {
         List<MovieShowing> mov = new ArrayList<>();
         mov.add(INITIAL_MOVIESHOWING[i]);
         listOfMovies.add(mov);
      }

      for(int i = 0 ; i < INITIAL_MOVIES.length ; i ++)
      {
         INITIAL_MOVIES[i].setMovieshowings(listOfMovies.get(i));
      }

   }

   private void loadInitialData()
   {

      // *************** MAP STUDIOS -> MOVIES ***************
      List<Movie> mainMovieList0 = new ArrayList<Movie>();
      List<Movie> mainMovieList1 = new ArrayList<Movie>();
      List<Movie> mainMovieList2 = new ArrayList<Movie>();
      List<Movie> mainMovieList3 = new ArrayList<Movie>();
      IntStream.range(0, INITIAL_MOVIES.length).forEach(i -> {
         if (i >= 0 && i <= 2) {
            mainMovieList0.add(INITIAL_MOVIES[i]);
         } else if (i >= 3 && i <= 4) {
            mainMovieList1.add(INITIAL_MOVIES[i]);
         } else if (i >= 5 && i <= 9) {
            mainMovieList2.add(INITIAL_MOVIES[i]);
         } else {
            mainMovieList3.add(INITIAL_MOVIES[i]);
         }
      });

      INITIAL_STUDIOS[0].setMovies(mainMovieList0);
      INITIAL_STUDIOS[1].setMovies(mainMovieList1);
      INITIAL_STUDIOS[2].setMovies(mainMovieList2);
      INITIAL_STUDIOS[3].setMovies(mainMovieList3);
      // *************** END OF MAP STUDIOS -> MOVIES ***************


      // *************** MAP MOVIES -> STUDIOS ***************
      List<Studio> mainStudioList0 = new ArrayList<Studio>();
      List<Studio> mainStudioList1 = new ArrayList<Studio>();
      List<Studio> mainStudioList2 = new ArrayList<Studio>();
      List<Studio> mainStudioList3 = new ArrayList<Studio>();

      IntStream.range(0, INITIAL_STUDIOS.length).forEach(i -> {
         if (i == 0) {
            mainStudioList0.add(INITIAL_STUDIOS[0]);
         } else if (i == 1) {
            mainStudioList1.add(INITIAL_STUDIOS[1]);
         } else if (i == 2) {
            mainStudioList2.add(INITIAL_STUDIOS[2]);
         } else if (i == 3) {
            mainStudioList3.add(INITIAL_STUDIOS[3]);
         }
      });

      IntStream.range(0, INITIAL_MOVIES.length).forEach(i -> {
         if (i >= 0 && i <= 2) {
            INITIAL_MOVIES[i].setStudios(mainStudioList0);
         } else if (i >= 3 && i <= 4) {
            INITIAL_MOVIES[i].setStudios(mainStudioList1);
         } else if (i >= 5 && i <= 9) {
            INITIAL_MOVIES[i].setStudios(mainStudioList2);
         } else {
            INITIAL_MOVIES[i].setStudios(mainStudioList3);
         }
      });
      // *************** END OF MAP MOVIES -> STUDIOS ***************

      // ****************MAP MovieShowing->Movie****************
      //Linking movie showings to their movies. Setting a movie to each movie showing
      for(int i = 0 ; i < 5 ; i ++)
      {
         INITIAL_MOVIESHOWING[i].setMovie(INITIAL_MOVIES[i]);
      }
      for(int i = 5 ; i < INITIAL_MOVIESHOWING.length ; i ++)
      {
         INITIAL_MOVIESHOWING[i].setMovie(INITIAL_MOVIES[i-5]);
      }
      // ****************END OF MovieShowing->Movie****************

      // ****************MAP Movie->MovieShowing****************
      //Linking movies to their movie showings. Setting list of movieShowings for each movie
      List<List<MovieShowing>> listOfMovies = new ArrayList<>();
      for(int i = 0 ; i < 5 ; i ++)
      {
         List<MovieShowing> mov = new ArrayList<>();
         mov.add(INITIAL_MOVIESHOWING[i]);
         listOfMovies.add(mov);
      }
      for(int i = 5 ; i < INITIAL_MOVIESHOWING.length ; i ++)
      {
         listOfMovies.get(i-5).add(INITIAL_MOVIESHOWING[i]);
      }

      for(int i = 0 ; i < 5 ; i ++)
      {
         INITIAL_MOVIES[i].setMovieshowings(listOfMovies.get(i));
      }
      // ****************END OF Movie-->MovieShowing****************

      // ****************MAP Theatre->MovieShowing****************
      List<MovieShowing> mainMovieShowingList0 = new ArrayList<MovieShowing>();
      List<MovieShowing> mainMovieShowingList1 = new ArrayList<MovieShowing>();
      List<MovieShowing> mainMovieShowingList2 = new ArrayList<MovieShowing>();
      List<MovieShowing> mainMovieShowingList3 = new ArrayList<MovieShowing>();
      List<MovieShowing> mainMovieShowingList4 = new ArrayList<MovieShowing>();

      IntStream.range(0, INITIAL_MOVIESHOWING.length).forEach(i-> {
         if (i == 0) {
            mainMovieShowingList0.add(INITIAL_MOVIESHOWING[i]);
         } else if (i == 1){
            mainMovieShowingList2.add(INITIAL_MOVIESHOWING[i]);
         } else if (i == 2 ){
            mainMovieShowingList4.add(INITIAL_MOVIESHOWING[i]);
         } else if (i == 3){
            mainMovieShowingList2.add(INITIAL_MOVIESHOWING[i]);
         } else if (i == 4) {
            mainMovieShowingList0.add(INITIAL_MOVIESHOWING[i]);
         } else if (i == 5) {
            mainMovieShowingList3.add(INITIAL_MOVIESHOWING[i]);
         } else if (i == 6) {
            mainMovieShowingList1.add(INITIAL_MOVIESHOWING[i]);
         } else if (i == 7) {
            mainMovieShowingList3.add(INITIAL_MOVIESHOWING[i]);
         } else if (i == 8) {
            mainMovieShowingList1.add(INITIAL_MOVIESHOWING[i]);
         } else if (i == 9) {
            mainMovieShowingList4.add(INITIAL_MOVIESHOWING[i]);
         }
      });
      INITIAL_THEATERS[0].setMovieShowings(mainMovieShowingList0);
      INITIAL_THEATERS[1].setMovieShowings(mainMovieShowingList1);
      INITIAL_THEATERS[2].setMovieShowings(mainMovieShowingList2);
      INITIAL_THEATERS[3].setMovieShowings(mainMovieShowingList3);
      INITIAL_THEATERS[4].setMovieShowings(mainMovieShowingList4);
      // ****************END OF Theater-->MovieShowing****************

      // ****************MAP MovieShowing-->Theater****************
      IntStream.range(0, INITIAL_THEATERS.length).forEach(i -> {
         if (i == 0) {
            INITIAL_MOVIESHOWING[0].setTheater(INITIAL_THEATERS[i]);
            INITIAL_MOVIESHOWING[4].setTheater(INITIAL_THEATERS[i]);
         } else if (i == 1) {
            INITIAL_MOVIESHOWING[6].setTheater(INITIAL_THEATERS[i]);
            INITIAL_MOVIESHOWING[8].setTheater(INITIAL_THEATERS[i]);
         } else if (i == 2) {
            INITIAL_MOVIESHOWING[1].setTheater(INITIAL_THEATERS[i]);
            INITIAL_MOVIESHOWING[3].setTheater(INITIAL_THEATERS[i]);
         } else if (i == 3) {
            INITIAL_MOVIESHOWING[5].setTheater(INITIAL_THEATERS[i]);
            INITIAL_MOVIESHOWING[7].setTheater(INITIAL_THEATERS[i]);
         } else {
            INITIAL_MOVIESHOWING[2].setTheater(INITIAL_THEATERS[i]);
            INITIAL_MOVIESHOWING[9].setTheater(INITIAL_THEATERS[i]);
         }
      });
      // ****************END OF MovieShowing-->Theater****************









//      for (int i = 0; i < INITIAL_STUDIOS.length; i++) {
//         for (Movie movie : INITIAL_STUDIOS[i].getMovies()) {
//            System.out.println(movie.getTitle());
//         }
//      }


      for (Movie movie : INITIAL_MOVIES) {
         entityManager.persist(movie);
      }
      for (Theater theater : INITIAL_THEATERS) {
         entityManager.persist(theater);
      }
      for (Studio studio : INITIAL_STUDIOS) {
         entityManager.persist(studio);
      }


      // Connect Movie and Studio


   }

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
           new Movie("Murder on the Orient Express", new GregorianCalendar(2017, 11, 10), MPAARating.PG13, 114, 55000000, 352800000, 61)
   };

   private static final Studio[] INITIAL_STUDIOS = new Studio[]{
           new Studio("Marvel Studios", Country.US),
           new Studio("Warner Bros Studios", Country.US),
           new Studio("Pixar Studios", Country.US),
           new Studio("Longcross Studios", Country.UK)
   };

   private static final Theater[] INITIAL_THEATERS = new Theater[]{
           new Theater("AMC Southbay Galleria", "Redondo Beach", "Western", Country.US, 16, "3107937477"),
           new Theater("Beckenham", "Beckenham", "Euro", Country.UK, 6, "08001383315"),
           new Theater("ArcLight Cinemas - Hollywood", "Hollywood", "Western", Country.US, 15, "3236152550"),
           new Theater("Revue Cinema", "Toronto", "Western", Country.CA, 1, "4165319950"),
           new Theater("Toho Cinemas Roppongi Hills", "Tokyo", "Eastern", Country.JP, 5, "81357756090")
   };

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
   };

}
