package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

  private Connection connection;

  private final Properties properties;

  public TableEditor(Properties properties) {
    this.properties = properties;
    initConnection();
  }

  private void initConnection() {
    String driver = properties.getProperty("database.driver");
    String url = properties.getProperty("database.url");
    String login = properties.getProperty("database.login");
    String password = properties.getProperty("database.password");
    try {
      Class.forName(driver);
      this.connection = DriverManager.getConnection(url, login, password);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void createTable(String tableName) {
    executeStatement(String.format(
            "CREATE TABLE IF NOT EXISTS %s (%s);",
            tableName,
            "id SERIAL PRIMARY KEY"
    ));
  }

  public void dropTable(String tableName) {
    executeStatement(String.format(
            "DROP TABLE IF EXISTS %s;",
            tableName
    ));
  }

  public void addColumn(String tableName, String columnName, String type) {
    executeStatement(String.format(
            "ALTER TABLE %s ADD COLUMN %s %s;",
            tableName,
            columnName,
            type
    ));
  }

  public void dropColumn(String tableName, String columnName) {
    executeStatement(String.format(
            "ALTER TABLE %s DROP COLUMN %s ;",
            tableName,
            columnName
    ));
  }

  public void renameColumn(String tableName, String columnName, String newColumnName) {
    executeStatement(String.format(
            "ALTER TABLE %s RENAME COLUMN %s TO %s ;",
            tableName,
            columnName,
            newColumnName
    ));
  }

  private void executeStatement(String sql) {
    try (Statement statement = connection.createStatement()) {
      statement.execute(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getTableScheme(String tableName) {
    String rowSeparator = "-".repeat(30).concat(System.lineSeparator());
    String header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
    StringJoiner buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
    buffer.add(header);
    try (Statement statement = connection.createStatement()) {
      try (ResultSet selection = statement.executeQuery(String.format(
              "select * from %s limit 1", tableName
      ))) {
        ResultSetMetaData metaData = selection.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
          buffer.add(String.format("%-15s|%-15s%n",
                  metaData.getColumnName(i), metaData.getColumnTypeName(i))
          );
        }
      } catch (SQLException e) {
        System.out.printf("Ошибка при попытке запроса к таблице %s!%nТаблицы %s не существует!%n", tableName, tableName);
        return "";
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return buffer.toString();
  }

  @Override
  public void close() throws Exception {
    if (connection != null) {
      connection.close();
    }
  }

  public static void main(String[] args) {
    try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
      Properties properties = new Properties();
      properties.load(in);
      try (TableEditor tableEditor = new TableEditor(properties)) {
        String tableName = "jdbc_test";
        tableEditor.initConnection();

        tableEditor.createTable(tableName);
        System.out.println(tableEditor.getTableScheme(tableName));

        tableEditor.addColumn(tableName, "test", "text");
        System.out.println(tableEditor.getTableScheme(tableName));

        tableEditor.dropColumn(tableName, "test");
        System.out.println(tableEditor.getTableScheme(tableName));

        tableEditor.addColumn(tableName, "test", "text");
        System.out.println(tableEditor.getTableScheme(tableName));

        tableEditor.renameColumn(tableName, "test", "test_two");
        System.out.println(tableEditor.getTableScheme(tableName));

        tableEditor.dropTable("jdbc_test");
        System.out.println(tableEditor.getTableScheme(tableName));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}