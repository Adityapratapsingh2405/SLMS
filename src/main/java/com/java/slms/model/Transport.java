package com.java.slms.model;

import com.java.slms.dto.TransportDto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transport extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String routeTitle;
    
    @Column(nullable = false)
    private String pickUpLocation;
    
    @Column(nullable = false)
    private String pickUpTime;
    
    @Column(nullable = false)
    private String dropTime;
    
    @Column(nullable = false)
    private String note;    
    
    @Column
    private Long school;

	public Transport(TransportDto ob) {
		super();
		this.routeTitle = ob.getRouteTitle();
		this.pickUpLocation = ob.getPickUpLocation();
		this.pickUpTime = ob.getPickUpTime();
		this.dropTime = ob.getDropTime();
		this.note = ob.getNote();
	}
    
    
}
