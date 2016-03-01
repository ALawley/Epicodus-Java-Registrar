import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class StudentTest {


  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Student.all().size(), 0);
  }

  @Test
  public void equals_trueIfNameAndEnrollmentMatch() {
    Student testStudent = new Student("Taylor", "March 5");
    Student secondStudent = new Student("Taylor", "March 5");
    assertTrue(testStudent.equals(secondStudent));
  }

  @Test
  public void save_savesObjectInDatabase() {
    Student myStudent = new Student("Taylor", "March 5");
    myStudent.save();
    Student savedStudent = Student.all().get(0);
    assertTrue(savedStudent.equals(myStudent));
  }

}
