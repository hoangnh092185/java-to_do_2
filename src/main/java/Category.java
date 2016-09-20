
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
      return con.createQuery(sql).executeAndFetch(Category.class);//Category is the class of this file.;
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
      return this.getName().equals(newCategory.getName()) &&
            this.getId() == newCategory.getId(); //equals() methods to acount new property;
    }
  }
  public void save() {
   try(Connection con = DB.sql2o.open()) {
     String sql = "INSERT INTO categories (name) VALUES (:name)";
     this.id = (int) con.createQuery(sql, true)
       .addParameter("name", this.name)//"name" is the key, Method Category constructor name.
       .executeUpdate()//to run Query
       .getKey();//Semicolon includes .addParameter, .executeUpdate and .getKey
   }
 }
  public static Category find(int id) {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM categories where id=:id";//using a select query using where id=:id. We use .addParameter("id", id) to pass in the id argument to the sql query
      Category category = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Category.class);
      return category;
    }
 }


}
