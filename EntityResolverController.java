package com.sc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.wcohen.ss.*;

@Controller   
@RequestMapping(path="/sciClient") 
public class EntityResolverController {
	@Autowired 
	private EntityRulesController entityRulesController;
	
	@Autowired // This means to get the bean called userRepository
	private PartyRequestRepository partyRequestRepository;
	
	//threshold to determine two party name match or not
	Double threshold = 0.068;
	
	//ArrayList to store the upload csv files
	ArrayList<String[]> partyCsv = null;
	
	//Temperary folder to store the download csv file
	private String outputPath = "c://work//entity_resolution//party_sci_2019-07-01.csv";
	
	@Autowired 
	private SciClientController sciClientController;
	
	
	/**
	 * linkSciByName method is used for adhoc sci linkage, it will be expose as a public service, trigger by third party application such as OTP etc 
	 * @param party_name
	 * @param party_country  If you don't know the party_country, then input it as NULL
	 * @return
	 */
	@CrossOrigin
	@ResponseBody
	@RequestMapping(value="/linkSciByName", method = RequestMethod.GET)
	public  List<PartyRequest> linkSciByName(String party_name, String party_country) {
		//Call the partyCleanup method to do the party_name standardlize
		String party_name_std = partyCleanup(party_name);
		//Get the party_name_block, first 3 chars of the name. Use for query optimized when search the SCI data
		String party_name_block = party_name_std.substring(0, 3);
		
		//Initialize the result list
		List<PartyRequest> requestList = new ArrayList<PartyRequest>();
		
		//Get the sci client list by block
		List<SciClient> clientList= sciClientController.getSciByBlock(party_name_block);
		
		//For loop each client, and compare the party_name with each sci name
		for (SciClient client : clientList) {
			//Only compare the name if both party country and sci country are same, or party country is NULL means global match
			if (party_country.equalsIgnoreCase("NULL") || party_country.equalsIgnoreCase(client.getCountryOfIncorporation()) ) {
				//Call the JaroWinkler package to calculate the distance, use the standardlize name instead of original name
				Double distance = calculateDistance(party_name_std, client.getLongNameStd());
				
				//if the compare distance is less than threshold, that means both name can match
				if(distance <= threshold) {
					PartyRequest request = new PartyRequest();
					request.setPartyName(party_name);
					request.setPartyNameStd(party_name_std);
					request.setPartyCountry(party_country);
					request.setSciLeid(client.getSciLeid());
					request.setLongName(client.getLongName());
					request.setLongNameStd(client.getLongNameStd());
					request.setCountryOfIncorporation(client.getCountryOfIncorporation());
					request.setSegment(client.getSegment());
					request.setRequestDate(new Date());
					request.setConfidence(1- distance);
					requestList.add(request);
					//set each party request to database
					partyRequestRepository.save(request);
				}
			}
		}
		
		//return the result to third party api call
		return requestList;
		
	}
	
	/**
	 * This method is used to upload CSV file from front-end UI
	 * @param file
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@CrossOrigin
	@ResponseBody
    @RequestMapping(value="/uploadCsv", method = RequestMethod.POST)
    public void uploadCsv(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		 //Define variable for each line
		 String line;
		 //Store the upload csv to temperary Arraylist
		 partyCsv = new ArrayList<String[]>();
		 
		 //Read the upload csv file content
	     InputStream is = file.getInputStream();
	     BufferedReader br = new BufferedReader(new InputStreamReader(is));
	     //Skip the first line 'header'
	     br.readLine();
	     while ((line = br.readLine()) != null) {
	    	 //Since it's csv file, split the party_name and party_cuntry by comma
	    	 String[] myString= {line.split(",")[0], line.split(",")[1]};
	    	 partyCsv.add(myString);
	     }
		
    	 System.out.println("upload request successfully ");
    }
	
	/**
	 * This method is used when user click the submit button on the front-end UI
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@CrossOrigin
	@ResponseBody
	@RequestMapping(value="/submitCsv", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource>  submitCsv(HttpServletRequest request) throws IOException {
		//requestList will be used to store the request output
		List<PartyRequest> requestList = new ArrayList<PartyRequest>();
		//for loop the user upload csv file
		for(String[] line : partyCsv) {
			//Call the linkSciByName to get the request result
			List<PartyRequest> result = linkSciByName(line[0], line[1]);
			for(int i = 0; i < result.size(); i++) {
				//save each request result to database
				partyRequestRepository.save(result.get(i));
				//add to requestList and return to fron-end UI
				requestList.add(result.get(i));
			}
		}
		long finish = System.currentTimeMillis();
		//write request result to local folder first
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
		//write the header
		writer.write("PartyName, PartyCountry, SciLeid, SciName, SciCountry, MatchConfidence \n");
		for(PartyRequest req : requestList) {
			//write the content to csv
			writer.write(req.getPartyName() + "," + req.getPartyCountry() + "," + req.getSciLeid() + "," + req.getLongName() + "," + req.getCountryOfIncorporation() + "," + req.getConfidence() + "\n");
//			System.out.println(req.getPartyName() + "," + req.getPartyCountry() + "," + req.getSciLeid() + "," + req.getLongName() + "," + req.getCountryOfIncorporation() + "," + req.getConfidence());
		}
		writer.close();
		
		System.out.println("submit request successfully ");
		InputStreamResource resource = new InputStreamResource(new FileInputStream(new File(outputPath)));
		
		//set the http request header
		HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=party_sci.csv");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

	    return ResponseEntity.ok()
	            .headers(header)
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .body(resource);
		
	}
	
	/**
	 * calculateDistance to calculate the distance base on party_name and sci name
	 * @param leftStr
	 * @param rightStr
	 * @return
	 */
	private double calculateDistance(String leftStr, String rightStr) {
		JaroWinkler jaroWinkler = new JaroWinkler();
		Double distance = 1 - jaroWinkler.score(leftStr, rightStr);
		return distance;
	}
	
	
	/**
	 * partyCleanup to do remove the special character and standardlize the party name
	 * @param party_name
	 * @return
	 */
	private String partyCleanup(String party_name) {
		//call the formatSpecialChar
    	party_name = formatSpecialChar(party_name);
    	//apply different rules for the party name
    	party_name = getStandardName(party_name, entityRulesController.getRule("name_rules"));
    	party_name = getStandardName(party_name, entityRulesController.getRule("shorten_rules"));
    	party_name = getStandardName(party_name, entityRulesController.getRule("null_rules") );
    	
    	//replace the BLANK to space
    	party_name = party_name.replaceAll("BLANK", "").replaceAll("\\s+", " ").trim();
    	
    	return party_name;
    }
    
    /**
     * formatSpecialChar
     * @param party_name
     * @return
     */
    private  String formatSpecialChar(String party_name) {
    	String format_party_name = party_name.toUpperCase().replaceAll("[^a-zA-Z0-9\\s]", " ").replaceAll("\\s+", " ").trim();
    	return format_party_name;
    }
    
    /**
     * This method is used to get standard name from different rules
     * @param party_name
     * @param rule_name
     * @return
     */
    private  String getStandardName(String party_name, List<EntityRules> ruleList) {
    	String std_party_name =  new String();
    	Boolean findFlag = false;
    	
		for (EntityRules obj : ruleList) {
			//If we find the rule input str
			if (party_name.indexOf(obj.getInputStr()) > -1) {
				std_party_name = new String();
				//We need to split the party_name to get full match
				for (String subName : party_name.split(" ")) {
					if (subName.equalsIgnoreCase(obj.getInputStr())) {
						//replace the match words
						subName = obj.getStandardStr();
						findFlag = true;
					}
					std_party_name += subName + " ";
				}

				if (findFlag == true) {
					party_name = std_party_name;
				} else {
					std_party_name = new String();
				}
			}
		}

    	return party_name;
    }
}
