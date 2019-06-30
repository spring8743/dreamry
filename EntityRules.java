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
@Table(name = "entity_resolver_rules")
@EntityListeners(AuditingEntityListener.class)
public class EntityRules {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "rule_id", updatable = false, nullable = false)
    private Long ruleId;
 
	@Column(name = "input_str")
	private String inputStr;

	@Column(name = "standard_str")
    private String standardStr;
    
	@Column(name = "rule_type")
    private String ruleType;

	public String getInputStr() {
		return inputStr;
	}

	public void setInputStr(String inputStr) {
		this.inputStr = inputStr;
	}

	public String getStandardStr() {
		return standardStr;
	}

	public void setStandardStr(String standardStr) {
		this.standardStr = standardStr;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
}
