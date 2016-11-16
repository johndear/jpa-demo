package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Helper {
  public static void checkData() throws Exception {
    Class.forName("org.hsqldb.jdbcDriver");
    Connection conn = DriverManager.getConnection("jdbc:hsqldb:data/tutorial", "sa", "");
    Statement st = conn.createStatement();

    ResultSet mrs = conn.getMetaData().getTables(null, null, null, new String[] { "TABLE" });
    while (mrs.next()) {
      String tableName = mrs.getString(3);

      ResultSet rs = st.executeQuery("select * from " + tableName);
      ResultSetMetaData metadata = rs.getMetaData();

      int k = 0;
      while (rs.next()) {
        String filedNames = "";
        String filedValues = "";
        if(k==0) {
          System.out.println("\n\n\n\nTable Name: "+ tableName);
          for (int i = 0; i < metadata.getColumnCount(); i++) {
            filedNames = filedNames + metadata.getColumnLabel((i + 1)) + "\t";

          }
          System.out.println(filedNames);
        }

        for (int i = 0; i < metadata.getColumnCount(); i++) {
          Object value = rs.getObject(i + 1);
          filedValues = filedValues + value + "\t";
        }
        System.out.println(filedValues);

        k++;
      }
    }
  }
}
