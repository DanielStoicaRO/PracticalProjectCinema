package cinema_project.Service;

import cinema_project.EntityClass.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SessionManager {

    public static UserEntity login(String user_name, String password) throws Exception {

        List<UserEntity> users = DataBaseService.getUser(user_name, password);

        if (users.size() == 0) {
            throw new Exception("Invalid login details!");
        } else {
            return users.get(0);
        }
    }

    public static void registerNewUser(String user_name, String password, String phoneNumber) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = DataBaseService.openSession();
            transaction = session.beginTransaction();
            UserEntity userEntity = new UserEntity();
            userEntity.setUser_name(user_name);
            userEntity.setUser_password(password);
            userEntity.setUser_phone(phoneNumber);
            session.save(userEntity);
            transaction.commit();
            session.close();

        } catch (Exception e) {
            System.out.println(e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
