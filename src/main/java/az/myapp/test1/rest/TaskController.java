package az.myapp.test1.rest;

import az.myapp.test1.dao.enums.Status;
import az.myapp.test1.dto.TaskStatusCountDto;
import az.myapp.test1.dto.request.TaskRequestDto;
import az.myapp.test1.dto.response.TaskResponseDto;
import az.myapp.test1.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService service;

    @GetMapping
    public List<TaskResponseDto> getTasks() {
        return service.getTasks();
    }

    @PostMapping
    public TaskResponseDto addTask(@RequestBody TaskRequestDto requestDto) {
        return service.addTask(requestDto);
    }

    @GetMapping("/search")
    public List<TaskResponseDto> getTaskBySearch(@RequestParam(required = false) Status status) {
        return service.getTaskBySearch(status);
    }

    //500
    @GetMapping("/count")
    public List<TaskStatusCountDto> countByStatus() {
        return service.countByStatus();
    }

    @PatchMapping("/{id}")
    public void updateTaskStatus(@PathVariable Long id,
                                 @RequestBody TaskRequestDto status) {
        service.updateTaskStatus(id, status);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id){
        service.deleteTask(id);
    }


}
