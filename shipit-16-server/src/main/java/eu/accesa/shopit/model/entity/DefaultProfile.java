package eu.accesa.shopit.model.entity;

import eu.accesa.shopit.base.BaseEntity;

import javax.persistence.*;


@Entity
public class DefaultProfile implements BaseEntity {

    @Id
    private String name;

    private Integer interval;

    public DefaultProfile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }
}
