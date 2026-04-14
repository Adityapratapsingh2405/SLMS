package com.java.slms.dto;

import com.java.slms.util.FeeCatalogStatus;
import com.java.slms.util.FeeStatus;
import com.java.slms.util.Gender;
import com.java.slms.util.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentBulkRequestDto
{

    private String panNumber;
    private String name;
    private String gender;
    private String className;
    private String sessionName;
    private String fatherName;
    private String mobile;
    private String dob;
    private String address;
    private Long userId;
   
}
