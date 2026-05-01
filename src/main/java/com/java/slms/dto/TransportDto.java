package com.java.slms.dto;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransportDto
{
    private String routeTitle;
    private String pickUpLocation;
    private String pickUpTime;
    private String dropTime;
    private String note; 
}
