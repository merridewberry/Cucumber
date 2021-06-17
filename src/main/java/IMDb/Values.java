package IMDb;

import java.util.HashMap;
import java.util.Map;

public class Values {

    public static final String URL = "https://www.imdb.com";
    public static final String MOVIE_URL_TAIL = "?ref_=nv_sr_srsg_0";
    public static final String MOVIE_URL_TAIL_II = "/?ref_=fn_al_tt_1";

    public static final Map<String, String> MOVIE = new HashMap<>();

    public static void populateMap() {
        MOVIE.put("The Boat That Rocked", "/title/tt1131729");
    }

}


