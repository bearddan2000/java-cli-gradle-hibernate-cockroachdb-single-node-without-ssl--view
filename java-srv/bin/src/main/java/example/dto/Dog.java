package example.dto;

import org.hibernate.Query;
import org.hibernate.Session;
import java.util.List;

import example.entity.DogEntity;
import example.dto.interfaces.IJoined;

public class Dog implements IJoined {
  Session session = null;
  public Dog(Session s){
    session = s;
  }

  @Override
  public void insert(int i, Integer breed, Integer color) throws Exception {
    if (color < 1)
      throw new Exception("must provide color");
    else if (breed < 1)
      throw new Exception("must provide breed");

		session.beginTransaction();

		DogEntity dog = new DogEntity(i, breed, color);

		session.save(dog);

		session.getTransaction().commit();
  }

  @Override
  public void selectAll(){

    String hql = "FROM DogEntity";
    Query query = session.createQuery(hql);
    List<DogEntity> lst = query.list();
    System.out.println(hql);
    for (DogEntity entity : lst)
      System.out.println(entity.toString());
  }
}
