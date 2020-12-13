package com.axiata.voucherpool.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tbl_recipient")
public class Recipient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private Long version;
	private String name;
	private String email;

	@OneToMany( cascade =  CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "recipient")
	private List<Voucher> vouchers = new ArrayList<>();


}
