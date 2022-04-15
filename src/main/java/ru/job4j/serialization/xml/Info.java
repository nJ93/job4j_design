package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "info")
public class Info {

  @XmlAttribute
  private int weight;
  @XmlAttribute
  private int height;

  public Info() {
  }

  public Info(int weight, int height) {
    this.weight = weight;
    this.height = height;
  }

  @Override
  public String toString() {
    return "Info{"
            + "weight=" + weight
            + ", height=" + height
            + '}';
  }
}
