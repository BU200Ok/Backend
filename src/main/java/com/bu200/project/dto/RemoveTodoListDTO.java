package com.bu200.project.dto;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RemoveTodoListDTO {
    private String todoListName;
    private String todoListDetail;
    private LocalDate todoListStart;
    private boolean todoListDone = true;
}
