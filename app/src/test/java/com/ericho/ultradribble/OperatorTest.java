package com.ericho.ultradribble;

import com.google.gson.Gson;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve_000 on 18/9/2017.
 * for project UltraDribble
 * package name com.ericho.ultradribble
 */

public class OperatorTest {

    Gson g = new Gson();
    @Test
    public void sss(){
        List<Integer> r = new ArrayList<>(100);
        r.add(5 & 1) ;
        r.add(5 & 2) ;
        r.add(5 & 3) ;
        r.add(5 & 4) ;
        r.add(5 & 5) ;
        r.add(5 & 6) ;
        r.add(5 & 7) ;
        r.add(5 & 8) ;
        r.add(5 & 9) ;
        r.add(5 & 10) ;

        System.out.print(g.toJson(r));
    }
}
