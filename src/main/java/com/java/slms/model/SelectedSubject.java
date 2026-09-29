package com.java.slms.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "selected_subjects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SelectedSubject 
{
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	  
	 private Integer classId;
	 private Integer sessionId;
	 private String subjectName;
	 private Boolean mainResult;
	 private Boolean gradeSheet;
	 private Long schoolId;
}
