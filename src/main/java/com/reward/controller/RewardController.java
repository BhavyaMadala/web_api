package com.reward.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reward.dto.UserRewards;
import com.reward.service.RewardService;

@RestController
@RequestMapping("/rewardsGenerated")
public class RewardController {
	

	@Autowired
	private RewardService rewardsService;

	 @GetMapping(value = "/customers/{customerId}",produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Object> getPointsByCustomerId(@PathVariable("customerId") Long customerId){
	         UserRewards rewards = rewardsService.getPoints(customerId);
	         if(rewards==null)
	 	        return new ResponseEntity<>("Invalid Customer Id",HttpStatus.OK);

	        return new ResponseEntity<>(rewards,HttpStatus.OK);
	    }
  
}
