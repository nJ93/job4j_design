package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

  @XmlAttribute
  private String name;
  @XmlAttribute
  private int age;
  @XmlAttribute(name = "graduated")
  private boolean isGraduated;
  private Info info;
  @XmlElementWrapper(name = "courses")
  @XmlElement(name = "course")
  private String[] courses;

  public Student() {
  }

  public Student(String name, int age, boolean isGraduated, String[] courses, Info info) {
    this.name = name;
    this.age = age;
    this.isGraduated = isGraduated;
    this.courses = courses;
    this.info = info;
  }

  @Override
  public String toString() {
    return "Student{"
            + "name='" + name + '\''
            + ", age=" + age
            + ", isGraduated=" + isGraduated
            + ", courses=" + Arrays.toString(courses)
            + ", info=" + info
            + '}';
  }

  public static void main(String[] args) throws JAXBException, IOException {
    Student student = new Student("Ivan", 21, false, new String[] {"Math", "English", "Physics"}, new Info(80, 180));

    JAXBContext context = JAXBContext.newInstance(Student.class);
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

    String xml;
    try (StringWriter writer = new StringWriter()) {
      marshaller.marshal(student, writer);
      xml = writer.getBuffer().toString();
      System.out.println(xml);
    }

    Unmarshaller unmarshaller = context.createUnmarshaller();
    try (StringReader reader = new StringReader(xml)) {
      Student result = (Student) unmarshaller.unmarshal(reader);
      System.out.println(result);
    }
  }
}
