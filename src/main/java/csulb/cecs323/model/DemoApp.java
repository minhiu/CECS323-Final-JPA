package csulb.cecs323.model;

import javax.persistence.*;
import java.util.GregorianCalendar;

public class DemoApp {
    private static final Studio[] INITIAL_STUDIOS = new Studio[]{
            new Studio("Marvel Studios", Country.US),
            new Studio("Warner Bros Studios", Country.US),
            new Studio("Pixar Studios", Country.US)
    };

    //iron man 1, black panther, thor, dr strange, captain marvel
    private static final MovieShowing[] INITIAL_MOVIESHOWING = new MovieShowing[]{
            new MovieShowing(new GregorianCalendar(2008, 5, 2), new GregorianCalendar(2008, 8, 2)), //iron man
            new MovieShowing(new GregorianCalendar(2018, 2, 16), new GregorianCalendar(2018, 5, 16)), //black panther
            new MovieShowing(new GregorianCalendar(2011, 5, 1), new GregorianCalendar(2011, 8, 1)), //thor
            new MovieShowing(new GregorianCalendar(2016, 10, 20), new GregorianCalendar(2017, 1, 20)), //dr. strange
            new MovieShowing(new GregorianCalendar(2019, 3, 8), new GregorianCalendar(2019, 6, 8)), //captain marvel
    };

}
