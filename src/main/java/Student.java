import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Student {
  private int id;
  private String name;
  private String enrollment;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEnrollment() {
    return enrollment;
  }

  public Student(String name, String enrollment) {
    this.name = name;
    this.enrollment = enrollment;
  }

  @Override
  public boolean equals(Object otherStudent) {
    if (!(otherStudent instanceof Student)) {
      return false;
    } else {
      Student newStudent = (Student) otherStudent;
      return this.getName().equals(newStudent.getName()) &&
             this.getId() == newStudent.getId() &&
             this.getEnrollment().equals(newStudent.getEnrollment());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO students(name, enrollment) VALUES (:name, :enrollment)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("enrollment", enrollment)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Student> all() {
    String sql = "SELECT * FROM students";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Student.class);
    }
  }

  public static Student find(int findId) {
    String sql = "SELECT * FROM students WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", findId)
        .executeAndFetchFirst(Student.class);
    }
  }

  public void update(String newName, String newEnrollment) {
    String sql = "UPDATE students SET name = :newName, enrollment = :newEnrollment WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("newName", newName)
        .addParameter("newEnrollment", newEnrollment)
        .addParameter("id", id)
        .executeUpdate();
    }

  }

}
