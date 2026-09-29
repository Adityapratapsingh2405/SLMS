package com.java.slms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.slms.model.Event;
import com.java.slms.model.SelectedSubject;

public interface SelectedSubjectRepo extends JpaRepository<SelectedSubject, Long>
{
	void deleteAllBySchoolId(Long schoolId);
	 List<SelectedSubject> findAllBySchoolId(Long schoolId);
}
