package com.axiata.voucherpool.demo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tbl_special_offer")
public class SpecialOffer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private Long version;

	private String name;
	private float percentDiscount;

	@CreationTimestamp
	private Date createDate;

	@CreationTimestamp
	private Date updateDate;

}
