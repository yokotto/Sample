package model;

import java.util.List;
import dao.StudyDAO;

public class GetStudyLogic {
  public List<Study> execute() {
    StudyDAO dao = new StudyDAO();
    List<Study> studyList = dao.findAll();
    return studyList;
  }
}