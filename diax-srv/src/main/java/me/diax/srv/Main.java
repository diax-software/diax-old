package me.diax.srv;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

/**
 * Created by Comportment on 08/04/2017.
 * If you don't understand this, we are screwed.
 */
public class Main {

    public static final RethinkDB r = new RethinkDB();

    public static void main(String[] args) {
        new Main().main();
    }

    public void main() {
        Connection conn = r.connection().hostname("localhost").port(28015).connect();
        r.table("authors").insert(r.hashMap()).delete().run(conn);
    }
}
