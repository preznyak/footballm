package hu.preznyak.footballm.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "player")
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;
    @Column(name = "pos", nullable = false)
    private String position;
    @Column(name = "shirt_number")
    private Integer shirtNumber;
    @Column(name = "market_price")
    @JsonFormat()
    private Double marketPrice;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "nationality", nullable = false)
    private String nationality;
    /*
    Salary per week is not perfectly correct, we just need some additional field to play with.
     */
    @Column(name = "salary_per_week")
    private Double salaryPerWeek;

//    @ManyToOne(targetEntity = Team.class)
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;

    @Column(name = "good_form")
    private boolean goodForm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(Integer shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Double getSalaryPerWeek() {
        return salaryPerWeek;
    }

    public void setSalaryPerWeek(Double salaryPerWeek) {
        this.salaryPerWeek = salaryPerWeek;
    }

//    public Team getTeam() {
//        return team;
//    }
//
//    public void setTeam(Team team) {
//        this.team = team;
//    }

    public boolean isGoodForm() {
        return goodForm;
    }

    public void setGoodForm(boolean goodForm) {
        this.goodForm = goodForm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name) && age.equals(player.age) && nationality.equals(player.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, nationality);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", position=" + position +
                ", shirtNumber=" + shirtNumber +
                ", marketPrice=" + marketPrice +
                ", age=" + age +
                ", nationality='" + nationality + '\'' +
                ", salaryPerWeek=" + salaryPerWeek +
                '}';
    }
}
