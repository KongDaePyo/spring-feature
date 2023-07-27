package c5ng.todolist.service;

import c5ng.todolist.domain.ToDo;
import c5ng.todolist.repository.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ToDoServiceTest {

    @Autowired
    private ToDoService toDoService;

    @Test
    @Rollback(false)
    public void addList() {
        //given
        ToDo todo = new ToDo();
        todo.setTitle("투두리스트 개발");
        todo.setCompleted(false);

        //when
        toDoService.addTodoList(todo);

        //then
        assertEquals(1, todo.getId());
    }
}