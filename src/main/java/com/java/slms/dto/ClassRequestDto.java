package com.java.slms.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClassRequestDto
{
    private String className;
    private Double feesAmount;
    private Long schoolId;
    private Long classTeacherId;
    private Long sessionId;
    
    private Double transportFees;
    private Double computerFees;
    private Double tuitionFees;
    private Double otherFees;
    private Double examAmount;
}
