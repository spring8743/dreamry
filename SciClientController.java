package com.sc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.domain.Example;

@Controller    // This means that this class is a Controller
public class SciClientController {
	@Autowired // This means to get the bean called userRepository
	private SciClientRepository sciClientRepository;
	
	/**
	 * getSciByBlock to feach the sci list by block
	 * @param block
	 * @return
	 */
	public List<SciClient> getSciByBlock(String block) {
		SciClient sci = new SciClient();
		sci.setLongNameBlock(block);
		Example example = Example.of(sci);
		
		return sciClientRepository.findAll(example);
		
	}
	
}
