package com.sc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "sci_client")
@EntityListeners(AuditingEntityListener.class)
public class SciClient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", updatable = false, nullable = false)
    private Long id;

	@Column(name = "sci_leid")
	private String sciLeid;

	@Column(name = "long_name_std")
    private String longNameStd;
    
	@Column(name = "country_of_incorporation")
    private String countryOfIncorporation;
    
	@Column(name = "long_name")
    private String longName;
    
	@Column(name = "segment")
    private String segment;
    
	@Column(name = "long_name_block")
    private String longNameBlock;

	public String getLongNameBlock() {
		return longNameBlock;
	}

	public void setLongNameBlock(String longNameBlock) {
		this.longNameBlock = longNameBlock;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSciLeid() {
		return sciLeid;
	}

	public void setSciLeid(String sciLeid) {
		this.sciLeid = sciLeid;
	}

	public String getLongNameStd() {
		return longNameStd;
	}

	public void setLongNameStd(String longNameStd) {
		this.longNameStd = longNameStd;
	}

	public String getCountryOfIncorporation() {
		return countryOfIncorporation;
	}

	public void setCountryOfIncorporation(String countryOfIncorporation) {
		this.countryOfIncorporation = countryOfIncorporation;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}
    
}
