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

  public static List<Student> all() {
    String sql = "SELECT * FROM students";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Student.class);
    }
  }
}
