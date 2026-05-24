package az.myapp.test1.dto;

import az.myapp.test1.dao.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskStatusCountDto {

    private Long count;
    private String status;
 }
