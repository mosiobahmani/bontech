package com.example.ServiceManage.models;

import com.example.ServiceManage.enums.YesNo;
import com.example.ServiceManage.enums.YesNoConverter;
import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "TBL_USER")
public class User {

    @Id
  /*  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    @SequenceGenerator(name = "seq_user", sequenceName = "SA.SEQ_EMDADGAR_ID", allocationSize = 1,initialValue = 1)*/
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME")
    private String usernamge;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "IS_ADMIN")
    @Convert(converter = YesNoConverter.class)
    private YesNo isAdmin;

    @Column(name = "CREDIT")
    private Long credit;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsernamge() {
        return usernamge;
    }

    public void setUsernamge(String usernamge) {
        this.usernamge = usernamge;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public YesNo getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(YesNo isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && usernamge.equals(user.usernamge) && isAdmin == user.isAdmin && credit.equals(user.credit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usernamge, isAdmin, credit);
    }
}
