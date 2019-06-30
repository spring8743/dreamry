package com.sc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.Example;



@Controller    // This means that this class is a Controller
public class EntityRulesController {
	
	@Autowired // This means to get the bean called userRepository
	private EntityRulesRepository entityRulesRepository;
	
    /**
     * get all the rules by ruleType
     * @param ruleType
     * @return
     */
	public List<EntityRules> getRule(String ruleType) {
		EntityRules rules = new EntityRules();
		rules.setRuleType(ruleType);
		Example example = Example.of(rules);
		
		//get the rules by ruleType 
		return entityRulesRepository.findAll(example);
		
	}

}
