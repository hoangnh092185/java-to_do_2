import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Task {
  private String description;
  private boolean completed;
  private LocalDateTime createdAt;
  private int id;

  public Task(String description) {
    this.description = description;
    completed = false;
    createdAt = LocalDateTime.now();
  }

  public String getDescription() {
    return description;
  }

  public boolean isCompleted() {
    return completed;
  }

  public LocalDateTime getCreatedAt() {
   return createdAt;
  }

 public static List<Task> all() {
   String sql = "SELECT id, description FROM tasks";
   try(Connection con = DB.sql2o.open()){
     return con.createQuery(sql).executeAndFetch(Task.class);
   }
 }

  public int getId() {
    return id;
  }
  public static Task find(int id) {
  // return instances.get(id - 1);
  }

}
