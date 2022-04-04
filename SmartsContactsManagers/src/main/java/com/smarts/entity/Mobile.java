package com.smarts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MOBILE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Mobile {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Integer mobileId;
	private String mobileNo;
	private String countryCd;
	private String type;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;

}


	