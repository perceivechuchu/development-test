package za.co.bmw.kanban.controller;

import za.co.bmw.kanban.model.Kanban;
import za.co.bmw.kanban.model.KanbanDTO;
import za.co.bmw.kanban.model.Task;
import za.co.bmw.kanban.model.TaskDTO;
import za.co.bmw.kanban.model.TaskStatus;
import za.co.bmw.kanban.repository.KanbanRepository;
import za.co.bmw.kanban.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Optional;

@TestPropertySource( properties = {
        "spring.datasource.url=jdbc:h2:mem:test",
        "spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.H2Dialect"
})
public class CommonITCase {

    @Autowired
    private KanbanRepository kanbanRepository;

    @Autowired
    private TaskRepository taskRepository;

    protected Kanban createSingleKanban(){
        Kanban kanban = new Kanban();
        int random = (int)(Math.random() * 100 + 1);
        kanban.setTitle("Test Kanban " + random);
        kanban.setTasks(new ArrayList<>());
        return kanban;
    }

    protected Task createSingleTask(){
        Task task = new Task();
        int random = (int)(Math.random() * 100 + 1);
        task.setTitle("Test Task " + random);
        task.setDescription("Description " + random);
        task.setColor("Color " + random);
        task.setStatus(TaskStatus.TODO);
        return task;
    }

    protected KanbanDTO convertKanbanToDTO(Kanban kanban) {
        return new KanbanDTO().builder()
                .title(kanban.getTitle())
                .build();
    }

    protected TaskDTO convertTaskToDTO(Task task) {
        return new TaskDTO().builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .color(task.getColor())
                .status(task.getStatus())
                .build();
    }

    protected Kanban saveSingleRandomKanban(){
        return kanbanRepository.save(createSingleKanban());
    }

    protected Kanban saveSingleKanbanWithOneTask(){
        Kanban kanban = createSingleKanban();
        Task task = createSingleTask();
        kanban.addTask(task);
        return kanbanRepository.save(kanban);
    }

    protected Task saveSingleTask(){
        return taskRepository.save(createSingleTask());
    }

    protected Optional<Kanban> findKanbanInDbById(Long id) {
        return kanbanRepository.findById(id);
    }

    protected Optional<Task> findTaskInDbById(Long id) {
        return taskRepository.findById(id);
    }
}
