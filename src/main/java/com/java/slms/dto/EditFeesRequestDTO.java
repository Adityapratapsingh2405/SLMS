package com.java.slms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditFeesRequestDTO {
	private Double trans;
	private Double comp;
	private Double tuit;
	private Double other;
	private Double exam;
}
