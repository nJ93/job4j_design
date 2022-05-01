package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

  private Properties cfg;
  private String dump;

  public ImportDB(Properties cfg, String dump) {
    this.cfg = cfg;
    this.dump = dump;
  }

  public List<User> load() {
    List<User> users = new ArrayList<>();
    try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
      rd.lines()
              .peek(line -> {
                String[] lineSplit = line.split(";");
                if (lineSplit.length < 2 || "".equals(lineSplit[0]) || "".equals(lineSplit[1])) {
                  throw new IllegalArgumentException("Wrong file content at line - " + line);
                }
              })
              .forEach(line -> users.add(new User(line.substring(0, line.indexOf(";")), line.substring(line.indexOf(";") + 1, line.length() - 1))));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return users;
  }

  public void save(List<User> users) throws ClassNotFoundException, SQLException {
    Class.forName(cfg.getProperty("database.driver"));
    try (Connection cnt = DriverManager.getConnection(
            cfg.getProperty("database.spammer.url"),
            cfg.getProperty("database.login"),
            cfg.getProperty("database.password")
    )) {
      for (User user : users) {
        try (PreparedStatement ps = cnt.prepareStatement("insert into users (name, email) values (?, ?);")) {
          ps.setString(1, user.name);
          ps.setString(2, user.email);
          ps.execute();
        }
      }
    }
  }

  private static class User {
    String name;
    String email;

    public User(String name, String email) {
      this.name = name;
      this.email = email;
    }
  }


  public static void main(String[] args) throws Exception {
    Properties cfg = new Properties();
    try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
      cfg.load(in);
    }
    ImportDB db = new ImportDB(cfg, "./dump.txt");
    db.save(db.load());
  }
}