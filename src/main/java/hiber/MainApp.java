package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        Car nissan = new Car("Nissan", 370);
        Car mercedes = new Car("Mercedes", 500);
        Car bmw = new Car("BMW", 750);
        Car porsche = new Car("Porsche", 911);

        User user5 = new User("Anton", "Titiov", "anton@outlook.com");
        User user6 = context.getBean(User.class);
        user6.setFirstName("Peter");
        user6.setLastName("Pen");
        user6.setEmail("piterpen123@mail.us");
        User user7 = new User("Donald", "Duck", "duck@gmail.com", bmw);
        User user8 = new User("Alexander", "The Great", "makedonsky@gmail.gre");

        user5.setCar(nissan);
        user6.setCar(mercedes);
     //   user7.setCar(bmw);
        user8.setCar(porsche);

        userService.add(user5);
        userService.add(user6);
        userService.add(user7);
        userService.add(user8);

        System.out.println("\n------------ Result --------------- ");
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
//            System.out.println("Car id = " + user.getCar().getId());
            System.out.println();
        }

        User luckyUser = userService.getUserByCar(porsche);
        System.out.println("Owner " + porsche + " is " + luckyUser);

        context.close();
    }
}
