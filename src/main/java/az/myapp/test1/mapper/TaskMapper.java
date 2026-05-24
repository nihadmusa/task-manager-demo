package az.myapp.test1.mapper;

import az.myapp.test1.dao.entity.Task;
import az.myapp.test1.dto.response.TaskResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class TaskMapper {
    public TaskResponseDto entityToDto(Task task){
        return new TaskResponseDto(
            task.getId(),
            task.getName(),
            task.getStatus()
        );
    }

    public List<TaskResponseDto> entitiesToDtos(List<Task> tasks){
        List<TaskResponseDto> list = new ArrayList<>();
        for (Task task : tasks) {
            list.add(entityToDto(task));
        }
        return list;
    }
}
