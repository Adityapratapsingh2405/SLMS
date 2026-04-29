package com.java.slms.repository;

import com.java.slms.model.Attendance;
import com.java.slms.model.ClassEntity;
import com.java.slms.model.Student;
import com.java.slms.model.Transport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface TransportRepository extends JpaRepository<Transport, Long>
{
	List<Transport> findBySchool(Long school);
}
