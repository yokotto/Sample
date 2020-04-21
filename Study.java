package model;

import java.io.Serializable;

public class Study implements Serializable {
  private int id;
  private String name;
  private String text;
  private String type;

  public Study() {}
  public Study(String name, String text, String type) {
    this.name = name;
    this.text = text;
    this.type = type;
  }
  public Study(int id, String name, String text, String type) {
    this.id = id;
    this.name = name;
    this.text = text;
    this.type = type;
  }
  public int getId() { return id; }
  public void setId(int id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  
  public String getText() { return text; }
  public void setText(String text) { this.text = text; }

  public String getType() { return type; }
  public void setType(String type) { this.type = type; }
}