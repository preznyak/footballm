package hu.preznyak.footballm.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TEAM")
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "CITY", nullable = false)
    private String city;
    @Column(name = "STADIUM", nullable = false)
    private String stadium;
    @Column(name = "COACH")
    private String coach;
    @Column(name = "BUDGET", nullable = false)
    private Double budget;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", stadium='" + stadium + '\'' +
                ", coach='" + coach + '\'' +
                ", budget='" + budget + '\'' +
//                ", squad=" + squad +
                '}';
    }
}
