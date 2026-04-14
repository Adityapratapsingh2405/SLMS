package com.java.slms.model;

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
}
