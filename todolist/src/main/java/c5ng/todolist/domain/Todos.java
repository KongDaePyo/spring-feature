package c5ng.todolist.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Todos {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    
    private Integer todoOrder;

    private Boolean completed;
}
