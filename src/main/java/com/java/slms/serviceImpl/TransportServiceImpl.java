package com.java.slms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.slms.model.Transport;
import com.java.slms.repository.TransportRepository;
import com.java.slms.service.TransportService;

@Service
public class TransportServiceImpl implements TransportService
{
	@Autowired
	private TransportRepository transRepo;

	@Override
	public boolean saveTransport(Transport ob,Long schoolId) {
		ob.setSchool(schoolId);
		transRepo.save(ob);
		return true;
	}

	@Override
	public boolean updateTransport(Transport ob) {
		transRepo.save(ob);
		return true;
	}

	@Override
	public boolean deleteTransport(Transport ob) {
		transRepo.delete(ob);
		return true;
	}

	@Override
	public List<Transport> listAll(Long schoolId) {
		return transRepo.findBySchool(schoolId);
	}

}
