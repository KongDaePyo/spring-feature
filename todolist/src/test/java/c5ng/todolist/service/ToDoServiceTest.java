package c5ng.todolist.service;

import c5ng.todolist.domain.ToDo;
import c5ng.todolist.repository.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ToDoServiceTest {

    @Autowired
    private ToDoService toDoService;

    @Test
    @Rollback(false)
    public void 추가() {
        //given
        ToDo todo = new ToDo();
        todo.setTitle("투두리스트 개발");
        todo.setCompleted(false);


        ToDo todo2 = new ToDo();
        todo2.setTitle("투두리스트 개발2");
        todo2.setCompleted(false);

        //when
        toDoService.saveToDo(todo);
        toDoService.saveToDo(todo2);

        //then
        assertEquals(1, todo.getId());
    }

    @Test
    public void 전체_조회() throws Exception {
        //given
        ToDo todo = new ToDo();
        todo.setTitle("투두리스트 개발");
        todo.setCompleted(false);

        ToDo todo2 = new ToDo();
        todo2.setTitle("투두리스트 개발2");
        todo2.setCompleted(false);

        toDoService.saveToDo(todo);
        toDoService.saveToDo(todo2);

        //when
        List<ToDo> toDos = toDoService.findToDos();

        //then
        assertEquals(2, toDos.size());
    }

    @Test
    public void 조회() throws Exception {
        //given
        ToDo todo = new ToDo();
        todo.setTitle("투두리스트 개발");
        todo.setCompleted(false);

        toDoService.saveToDo(todo);

        //when
        ToDo findToDo = toDoService.findToDo(todo.getId());

        //then
        assertEquals(todo.getId(), findToDo.getId());
        assertEquals(todo.getTitle(), findToDo.getTitle());
        assertEquals(todo.getCompleted(), findToDo.getCompleted());
    }

    @Test
    @Rollback(value = false)
    public void 타이틀_변경() throws Exception {
        //given
        ToDo todo = new ToDo();
        todo.setTitle("투두리스트 개발");
        todo.setCompleted(false);

        toDoService.saveToDo(todo);

        ToDo findToDo = toDoService.findToDo(todo.getId());
        String orgTitle = findToDo.getTitle();

        //when
        toDoService.changeTitle(findToDo.getId(), "타이틀 변경이다~!");

        ToDo findToDo2 = toDoService.findToDo(findToDo.getId());

        //then
        assertNotEquals(orgTitle, findToDo2.getTitle());
    }

    @Test
    public void 완료_변경() throws Exception {
        //given
        ToDo todo = new ToDo();
        todo.setTitle("투두리스트 개발");
        todo.setCompleted(false);

        toDoService.saveToDo(todo);

        ToDo findToDo = toDoService.findToDo(todo.getId());
        boolean orgComple = findToDo.getCompleted();

        //when
        toDoService.changeCompleted(findToDo.getId());

        ToDo findToDo2 = toDoService.findToDo(findToDo.getId());

        //then
        assertEquals(true, findToDo2.getCompleted());
    }

    @Test
    @Rollback(false)
    public void 전체_삭제() throws Exception {
        //given
        ToDo todo = new ToDo();
        todo.setTitle("투두리스트 개발");
        todo.setCompleted(false);

        ToDo todo2 = new ToDo();
        todo2.setTitle("투두리스트 개발2");
        todo2.setCompleted(false);

        toDoService.saveToDo(todo);
        toDoService.saveToDo(todo2);


        //when
        toDoService.deleteToDos();

        //then

    }

    @Test
    @Rollback(false)
    public void 삭제() throws Exception {
        //given
        ToDo todo = new ToDo();
        todo.setTitle("투두리스트 개발");
        todo.setCompleted(false);

        ToDo todo2 = new ToDo();
        todo2.setTitle("투두리스트 개발2");
        todo2.setCompleted(false);

        toDoService.saveToDo(todo);
        toDoService.saveToDo(todo2);

        //when
        toDoService.deleteToDo(todo.getId());

        //then
        assertEquals(true, toDoService.findToDo(todo.getId()).getDeleted());
    }
}