package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        if (user.getCar() != null) {
            session.save(user.getCar());
        } else {
            Car nullCar = new Car("no car", 0);
            user.setCar(nullCar);
            session.save(nullCar);
        }
        session.save(user);
        session.getTransaction().commit();
        System.out.println("User с именем " + user.getFirstName() + " добавлен в базу данных");
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        TypedQuery<User> query = session.createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByCar(Car car) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where car=:car")
                .setParameter("car", car)
                .setMaxResults(1);
        return (User) query.getSingleResult();
    }
}
