package com.example.thread.mstb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MstbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String branchId;
    private String branchName;
    private String cabangBpr;
    private String areaNameId;
    private String areaName;
    private String zipCode;
    private String objGroupDesc;
    private String objectId;
    private String job;
    private String pic;
    private String emailCrh;
    private String ccEmail1;
    private String ccEmail2;
    private String ccEmail3;
}
