package ithillel.lesson23;

import org.postgresql.ds.PGSimpleDataSource;

public class DBConnection {
    public static PGSimpleDataSource connectTo(String dataBaseName, String userName, String password, String[] serverNames, int[] portNumbers){
        var ds = new PGSimpleDataSource();
        ds.setDatabaseName(dataBaseName);
        ds.setUser(userName);
        ds.setPassword(password);
        ds.setServerNames(serverNames);
        ds.setPortNumbers(portNumbers);
        return ds;
    }
}
