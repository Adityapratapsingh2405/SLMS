package com.java.slms.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventMessage {
	private String title;
	private String date;
	private String time;
	private String venue;
	private List<String> students;
	
	
}
