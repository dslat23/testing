package com.npmstudent.testing;

/**
 * Created by npmstudent on 5/25/16.
 */
public class crimeDbSchema {
    public static final class CrimeTable{

        public static final String name = "crimes";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static  final  String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
        }
    }
}
