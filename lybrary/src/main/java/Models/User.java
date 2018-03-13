package Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")



public class User implements Serializable {
    public User(String email, String password, String name, String surname, String cookieId, String status, int fine, String address, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.cookieId = cookieId;
        this.status = status;
        this.fine = fine;
        this.address = address;
        this.phone = phone;
    }

    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "cookieId")
    private String cookieId;
    @Column(name = "status")
    private String status;
    @Column(name = "fine")
    private int fine;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;


    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCookieId() {
        return cookieId;
    }

    public void setCookieId(String cookieId) {
        this.cookieId = cookieId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

