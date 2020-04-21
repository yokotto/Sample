package model;

import dao.StudyDAO;

public class StudyLogic {
  public void execute(Study study) {
    StudyDAO dao = new StudyDAO();
    dao.create(study);
  }
}