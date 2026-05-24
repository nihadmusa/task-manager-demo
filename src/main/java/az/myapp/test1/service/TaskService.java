package az.myapp.test1.service;

import az.myapp.test1.dao.entity.Task;
import az.myapp.test1.dao.enums.Status;
import az.myapp.test1.dao.repository.TaskRepository;
import az.myapp.test1.dto.TaskStatusCountDto;
import az.myapp.test1.dto.request.TaskRequestDto;
import az.myapp.test1.dto.response.TaskResponseDto;
import az.myapp.test1.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    private final TaskMapper mapper;

    public List<TaskResponseDto> getTasks() {
        return mapper.entitiesToDtos(repository.findAllByCreated_at());
    }

    public TaskResponseDto addTask(TaskRequestDto requestDto) {
        var task = new Task(
                requestDto.getName(),
                requestDto.getStatus()
        );
        return mapper.entityToDto(repository.save(task));
    }

    public List<TaskResponseDto> getTaskBySearch(Status status) {
        if (status == null) return mapper.entitiesToDtos(repository.findAll());
        return mapper.entitiesToDtos(repository.getTasksBySearch(status.name()));


    }

    public List<TaskStatusCountDto> countByStatus() {
        return repository.countByStatus();
    }

    public void updateTaskStatus(Long id,
                                 TaskRequestDto status) {
        var optional = repository.findById(id);
        if (optional.isPresent()) {
            repository.updateTask(id, status.getStatus().name());
            var uptadedTask = optional.get();
            mapper.entityToDto(uptadedTask);
        }

        new TaskResponseDto();

    }
    public void deleteTask(Long id){
        repository.deleteById(id);
    }

}
