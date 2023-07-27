package c5ng.todolist.service;

import c5ng.todolist.domain.ToDo;
import c5ng.todolist.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    @Transactional
    public Long addTodoList(ToDo todo) {
        toDoRepository.save(todo);

        return todo.getId();
    }
}
