package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class SimpleJsonObject {
  private final String name;
  private final int classroom;
  private final boolean isStudent;
  private final String[] courses;
  private final SimpleJsonSecondObject simpleJsonSecondObject;

  public SimpleJsonObject(String name, int classroom, boolean isStudent, String[] courses, SimpleJsonSecondObject simpleJsonSecondObject) {
    this.name = name;
    this.classroom = classroom;
    this.isStudent = isStudent;
    this.courses = courses;
    this.simpleJsonSecondObject = simpleJsonSecondObject;
  }

  @Override
  public String toString() {
    return "SimpleJsonObject{"
            + "name='" + name + '\''
            + ", classroom=" + classroom
            + ", isStudent=" + isStudent
            + ", courses=" + Arrays.toString(courses)
            + ", simpleJsonSecondObject=" + simpleJsonSecondObject
            + '}';
  }

  public static void main(String[] args) {
    SimpleJsonObject simpleJsonObject = new SimpleJsonObject("Alex", 6, true, new String[]{"Math", "English"}, new SimpleJsonSecondObject("justInfo"));

    Gson gson = new GsonBuilder().create();
    System.out.println(gson.toJson(simpleJsonObject));

    String simpleJson =
            "{"
                + "\"name\":\"Alex\","
                + "\"classroom\":6,"
                + "\"isStudent\":true,"
                + "\"courses\":[\"Math\",\"English\"],"
                + "\"simpleJsonSecondObject\":{\"justField\":\"justInfo\"}"
                +
                "}";
    SimpleJsonObject simpleJsonObjectFromJson = gson.fromJson(simpleJson, SimpleJsonObject.class);
    System.out.println(simpleJsonObjectFromJson);
  }
}
