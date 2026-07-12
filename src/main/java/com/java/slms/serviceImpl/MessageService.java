package com.java.slms.serviceImpl;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.java.slms.dto.StudentResponseDto;
import com.java.slms.exception.ResourceNotFoundException;
import com.java.slms.model.EventMessage;
import com.java.slms.model.School;
import com.java.slms.repository.SchoolRepository;
import com.java.slms.service.StudentService;

@Service
public class MessageService 
{
	@Autowired
	private SchoolRepository schoolRepository;
	@Autowired
	private StudentService studService;
	@Autowired
	private RestTemplate restTemplate;
	
	private final String EVENT_TEMPLATE_ID = "1707178239303424757";
	private final String ENTITY_ID = "1701178196040987075";
	private final String SENDER_ID = "NEXTEG";
	private final String API_KEY = "Dmh0xYeIZU2aq0NDR5HpUw";
	private final String SMS_SERVER = "https://www.smsgatewayhub.com/api/mt/SendSMS";
	
	private final String EVENT_TEMPLATE = "	Dear Parents & Students, This is to inform you that our school is organizing "
			+ "a {#event#} on {#date#} at {#time#}. Venue: {#venue#} Students are requested to participate enthusiastically and "
			+ "report on time in proper school uniform. For any queries, please contact the school office. Regards, {#school#} NEXTEG";
	
	public void sendEventMessage(EventMessage data,Long schoolId) 
	{
		School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ResourceNotFoundException("School not found"));
		
//		String msg = EVENT_TEMPLATE.replace("{#event#}", data.getTitle());
//		msg = msg.replace("{#date#}", data.getDate());
//		msg = msg.replace("{#time#}", data.getTime());
//		msg = msg.replace("{#venue#}", data.getVenue());
//		msg = msg.replace("{#school#}", school.getSchoolName());
		String msg = EVENT_TEMPLATE
		        .replace("{#event#}", data.getTitle())
		        .replace("{#date#}", data.getDate())
		        .replace("{#time#}", data.getTime())
		        .replace("{#venue#}", data.getVenue())
		        .replace("{#school#}", school.getSchoolName());
		msg = URLEncoder.encode(msg,StandardCharsets.UTF_8);
		
		for(String pan : data.getStudents())
		{
			StudentResponseDto dto = studService.getStudentByPAN(pan, schoolId);
			String mobile = "91" + dto.getMobileNumber();
			
			URI uri  = UriComponentsBuilder
	                .fromUriString(SMS_SERVER)
	                .queryParam("APIKey", API_KEY)
	                .queryParam("senderid", SENDER_ID)
	                .queryParam("channel", "2")
	                .queryParam("DCS", 0)
	                .queryParam("flashsms", 0)
	                .queryParam("number", mobile)
	                .queryParam("text", "msg")
	                .queryParam("route", "clickhere")
	                .queryParam("EntityId", ENTITY_ID)
	                .queryParam("dlttemplateid", EVENT_TEMPLATE_ID)
	                .build(false)
	                .toUri();
			
			String s = uri.getRawQuery();
			System.out.println(s);
			
			ResponseEntity<Map> response = restTemplate.exchange(
			        uri,                 // ✅ URI object
			        HttpMethod.GET,
			        HttpEntity.EMPTY,    // no headers / body
			        Map.class
			);

			Map<String, Object> result = response.getBody();
			System.out.println(result);
		}	
	}
}
