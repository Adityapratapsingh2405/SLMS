package com.java.slms.service;

import com.java.slms.dto.SubjectDto;
import com.java.slms.dto.SubjectsBulkDto;
import com.java.slms.model.SelectedSubject;

import java.util.List;

public interface SubjectService
{

    SubjectDto addSubject(SubjectDto subjectDto, Long schoolId);

    List<SubjectDto> addSubjectsByClass(SubjectsBulkDto bulkDto, Long schoolId);

    List<SubjectDto> getAllSubjects(Long schoolId);

    SubjectDto getSubjectById(Long subjectId, Long schoolId);

    List<SubjectDto> getSubjectsByClassId(Long classId, Long schoolId);

    SubjectDto updateSubjectInfoById(Long subjectId, SubjectDto subjectDto, Long schoolId);

    void deleteSubject(Long subjectId, Long schoolId);

	void saveSelectedSubjects(List<SelectedSubject> records, Long schoolId);

	List<SelectedSubject> listSelectedSubjects(Long schoolId);
}
