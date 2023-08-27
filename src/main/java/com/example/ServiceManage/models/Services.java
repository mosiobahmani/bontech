package com.example.ServiceManage.models;


import com.example.ServiceManage.enums.YesNo;
import com.example.ServiceManage.enums.YesNoConverter;
import javax.persistence.*;

@Entity
@Table(name = "TBL_SERVICE")
public class Services {

    @Id
   /* @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_service")
    @SequenceGenerator(name = "seq_service", sequenceName = "SA.SEQ_EMDADGAR_ID", allocationSize = 1,initialValue = 1)*/
    @Column(name = "ID")
    private Long id;

    @Column(name = "COST")
    private Long cost;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IS_ACTIVE")
    @Convert(converter = YesNoConverter.class)
    private YesNo isActive;

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public YesNo getIsActive() {
        return isActive;
    }

    public void setIsActive(YesNo isActive) {
        this.isActive = isActive;
    }
}
