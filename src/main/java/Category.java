import java.util.List;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Category {
  private String name;
  private int id;

  public Category(String name) {
    this.name = name;
  }
  public String getName(){
    return name;
  }
  public static List<Category>all(){
    String sql = "SELECT id, name FROM categories";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql).executeAndFetch(Category.class);//Category is the class of this file.
    }
  }

  public int getId(){
    return id;
  }

  @Override
  public boolean equals(Object otherCategory) {
    if (!(otherCategory instanceof Category)) {
      return false;
    } else {
      Category newCategory = (Category) otherCategory;
      return this.getName().equals(newCategory.getName());
    }
  }
  public void save() {
   try(Connection con = DB.sql2o.open()) {
     String sql = "INSERT INTO categories (name) VALUES (:name)";
     con.createQuery(sql)
       .addParameter("name", this.name)//"name" is the key, Method Category constructor name.
       .executeUpdate();//to run Query
   }
 }
 //  public static Category find(int id) {
 // }
 // public List<Task> getTasks() {
 //  }

}
