package hiber.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component("carBean")
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "series")
    private int series;

    @OneToOne(mappedBy = "car")
    private User user;

    public Car() {
    }

    public Car(String model, int series) {
        name = model;
        this.series = series;
    }

    public long getId() {
        return id;
    }

    public String getModel() {
        return name;
    }

    public void setModel(String model) {
        name = model;
    }

    public int getSeries() {
        return series;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Car " + name + " " + series;
    }
}
