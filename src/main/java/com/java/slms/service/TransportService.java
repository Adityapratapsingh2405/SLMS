package com.java.slms.service;

import java.util.List;

import com.java.slms.dto.UserRequest;
import com.java.slms.model.Admin;
import com.java.slms.model.Transport;

public interface TransportService
{
    boolean saveTransport(Transport ob,Long schoolId);
    boolean updateTransport(Transport ob);
    boolean deleteTransport(Transport ob);
    List<Transport> listAll(Long schoolId);
}