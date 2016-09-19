import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;


public class TaskTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do", null, null);
  }
  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM tasks *;";
      con.createQuery(sql).executeUpdate();
    }
  }
  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Task firstTask = new Task("Mow the lawn");
    Task secondTask = new Task("Mow the lawn");
    assertTrue(firstTask.equals(secondTask));
  }

  @Test
   public void save_returnsTrueIfDescriptionsAretheSame() {
     Task myTask = new Task("Mow the lawn");
     myTask.save();
     assertTrue(Task.all().get(0).equals(myTask));
   }
 @Test
  public void all_returnsAllInstancesOfTask_true() {
    Task firstTask = new Task("Mow the lawn");
    firstTask.save();
    Task secondTask = new Task("Buy groceries");
    secondTask.save();
    assertEquals(true, Task.all().get(0).equals(firstTask));
    assertEquals(true, Task.all().get(1).equals(secondTask));
  }
  @Test
  public void save_assignsIdToObject() {
    Task myTask = new Task("Mow the lawn");
    myTask.save();
    Task savedTask = Task.all().get(0);
    assertEquals(myTask.getId(), savedTask.getId());
  }
  @Test
   public void getId_tasksInstantiateWithAnID() {
     Task myTask = new Task("Mow the lawn");
     myTask.save();
     assertTrue(myTask.getId() > 0);
   }
  // <----Old Test for gradle test without Postgresql---->
  // @Test
  // public void Task_instantiatesCorrectly_true() {
  //   Task myTask = new Task("Mow the lawn");
  //   assertEquals(true, myTask instanceof Task);
  // }
  // @Test
  // public void isCompleted_isFalseAfterInstantiation_false() {
  //   Task myTask = new Task("Mow the lawn");
  //   assertEquals(false, myTask.isCompleted());
  // }
  // @Test
  // public void Task_instantiatesWithDescription_String() {
  //   Task myTask = new Task("Mow the lawn");
  //   assertEquals("Mow the lawn", myTask.getDescription());
  // }
  // @Test
  // public void getCreatedAt_instantiatesWithCurrentTime_today() {
  //   Task myTask = new Task("Mow the lawn");
  //   assertEquals(LocalDateTime.now().getDayOfWeek(), myTask.getCreatedAt().getDayOfWeek());
  // }
  // @Test
  // public void all_returnsAllInstancesOfTask_true() {
  //   Task firstTask = new Task("Mow the lawn");
  //   Task secondTask = new Task("Buy groceries");
  //   assertEquals(true, Task.all().contains(firstTask));
  //   assertEquals(true, Task.all().contains(secondTask));
  // }
  // @Test
  // public void clear_emptiesAllTasksFromArrayList_0() {
  //   Task myTask = new Task("Mow the lawn");
  //   Task.clear();
  //   assertEquals(Task.all().size(), 0);
  // }
  //
  // @Test
  // public void getId_tasksInstantiateWithAnID_1() {
  //   Task.clear();  // Remember, the test will fail without this line! We need to empty leftover Tasks from previous tests!
  //   Task myTask = new Task("Mow the lawn");
  //   assertEquals(1, myTask.getId());
  // }
  // @Test
  // public void find_returnsTaskWithSameId_secondTask() {
  //   Task.clear();
  //   Task firstTask = new Task("Mow the lawn");
  //   Task secondTask = new Task("Buy groceries");
  //   Task thirdTask = new Task("Laundry");
  //   assertEquals(Task.find(secondTask.getId()), secondTask);
  // }

}
