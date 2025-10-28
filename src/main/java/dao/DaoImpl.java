// dao/DaoImpl.java (100.0, profil prod)
package dao;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("dao")
@Profile({"prod", "default"})
public class DaoImpl implements IDao {
  @Override public double getValue(){ return 100.0; }
}