package c5ng.todolist.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "todos")
@Setter @Getter
public class ToDo {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private Integer todoOrder;

    private Boolean completed;

    private Boolean deleted; /* 삭제 여부 판단, true일 경우에는 화면에 안 뿌려준다. */

//    public void setCompleted(Boolean completed) {
//        this.completed = completed;
//    }
}
