// dao/DaoApi.java (220.0, profil api)
package dao;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("daoApi")
@Profile({"api", "default"})
public class DaoApi implements IDao {
  @Override public double getValue(){ return 220.0; }
}