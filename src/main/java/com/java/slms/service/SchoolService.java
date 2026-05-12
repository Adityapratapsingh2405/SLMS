package com.java.slms.service;

import com.java.slms.dto.SchoolRequestDto;
import com.java.slms.dto.SchoolResponseDto;
import com.java.slms.model.School;

import java.util.List;

public interface SchoolService
{
    SchoolResponseDto createSchool(SchoolRequestDto requestDto);

    SchoolResponseDto getSchool(Long id);

    List<SchoolResponseDto> getAllSchools(boolean status);

    SchoolResponseDto updateSchool(Long id, SchoolRequestDto requestDto);

    void deleteSchool(Long id);
    
    boolean isActiveSchool(Long id);

	void deactiveSchool(Long id);

	void activeSchool(Long id);

	School getSchoolById(Long schoolId);
}
