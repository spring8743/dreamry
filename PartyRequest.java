package com.sc;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "party_request")
@EntityListeners(AuditingEntityListener.class)
public class PartyRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "request_id", updatable = false, nullable = false)
	private Long requestId;
	
	@Column(name = "party_name")
	private String partyName;
	
	@Column(name = "party_name_std")
	private String partyNameStd;
	
	@Column(name = "party_country")
	private String partyCountry;
	
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
	
	@Column(name = "confidence")
	private Double confidence;
  
	public Double getConfidence() {
		return confidence;
	}

	public void setConfidence(Double confidence) {
		this.confidence = confidence;
	}

	@Column(name = "request_date")
    private Date requestDate;
	

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyNameStd() {
		return partyNameStd;
	}

	public void setPartyNameStd(String partyNameStd) {
		this.partyNameStd = partyNameStd;
	}

	public String getPartyCountry() {
		return partyCountry;
	}

	public void setPartyCountry(String partyCountry) {
		this.partyCountry = partyCountry;
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

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
}
