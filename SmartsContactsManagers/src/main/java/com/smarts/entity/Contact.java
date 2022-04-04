package com.smarts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CONTACT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Integer contactId;
	
	private String firstName;
	private String lastName;
	
	@Column(unique = true)
	private String emailAddress;
	
	private String createdBy;
	private Date createdOn;
	
	private String updatedBy;
	private Date updatedOn;
	
	private String isActive;
	
	@OneToMany(targetEntity = Mobile.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "contactId", referencedColumnName = "contactId")
	private List<Mobile> mobile = new ArrayList<>();

}
