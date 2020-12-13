package com.axiata.voucherpool.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "tbl_voucher")
public class Voucher {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String code;

	@JsonIgnore
	@ManyToOne( cascade =  CascadeType.MERGE)
	private Recipient recipient;

	@OneToOne( cascade =  CascadeType.MERGE )
	private SpecialOffer specialOffer;

	private Date expiredDate;
	private boolean used;
	private Date usedDate;

	@CreationTimestamp
	private Date createDate;

	@CreationTimestamp
	private Date updateDate;

	@Version
	private Long version;

}
