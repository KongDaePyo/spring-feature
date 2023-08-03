package c5ng.todolist.service;

import c5ng.todolist.domain.ToDo;
import c5ng.todolist.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    @Transactional
    public Long saveToDo(ToDo todo) {
        toDoRepository.save(todo);

        return todo.getId();
    }

    public List<ToDo> findToDos() {
        return toDoRepository.findAll();
    }

    public ToDo findToDo(Long id) {
        return toDoRepository.findOne(id);
    }

    public void changeTitle(Long id, String title) {
        ToDo findToDo = toDoRepository.findOne(id);
        findToDo.setTitle(title);
    }

    public void changeCompleted(Long id) {
        ToDo findToDo = toDoRepository.findOne(id);

        if (findToDo.getCompleted() == true) {
            findToDo.setCompleted(false);
        } else if (findToDo.getCompleted() == false){
            findToDo.setCompleted(true);
        }
    }

    public void deleteToDos() {
        List<ToDo> toDos = toDoRepository.findAll();

        for (ToDo toDo : toDos) {
            toDo.setDeleted(true);
        }
    }

    public void deleteToDo(Long id) {
        ToDo findToDo = toDoRepository.findOne(id);

        findToDo.setDeleted(true);
    }
}
