package az.myapp.test1.dao.repository;

import az.myapp.test1.dao.entity.Task;
import az.myapp.test1.dto.TaskStatusCountDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(nativeQuery = true, value =
            "select * from task as t " +
                    "order by created_at desc")
    List<Task> findAllByCreated_at();

    @Query(nativeQuery = true, value =
            "SELECT * " +
                    "FROM task as t " +
                    "where status = :status")
    List<Task> getTasksBySearch(@Param("status") String status);

    @Query(nativeQuery = true, value =
            "SELECT count(*) , status from task group by status")
    List<TaskStatusCountDto> countByStatus();


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value =
            "update task as t set status = :status where id = :id")
    void updateTask(@Param("id") Long id, String status);

}
