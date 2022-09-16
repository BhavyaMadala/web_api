package com.reward.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.reward.dto.UserRewards;
import com.reward.dto.UserTransaction;
import com.reward.util.TransactionsData;

@Service
public class RewardServiceImpl implements RewardService {
	
	public  final static int firstReward=50;
	public  final static int secondReward=100;

	public static long calculatePoints(UserTransaction t) {
		if (t.getTransactionAmount() > firstReward && t.getTransactionAmount() <= secondReward) {
			return Math.round(t.getTransactionAmount() - firstReward);
		} else if (t.getTransactionAmount() > secondReward) {
			return Math.round(t.getTransactionAmount() - secondReward) * 2
					+ (secondReward - firstReward);
		} else
			return 0l;

	}


	private List<UserTransaction> getDataByTransaction(Long customerId,
			Timestamp lastMonthTimestamp, Timestamp from) {

		List<UserTransaction> transactionsList =TransactionsData.getAllTransactionData();
		List<UserTransaction> tList=new ArrayList<UserTransaction>();
		for (UserTransaction transaction : transactionsList) {
			if(transaction.getCustomerId()==customerId&&(transaction.getTransactionDate().getTime()>=lastMonthTimestamp.getTime()&&transaction.getTransactionDate().getTime()<=from.getTime()))

			{
				tList.add(transaction);
			}
		}

		return tList;
	}


	@Override
	public UserRewards getPoints(Long customerId) {

		Timestamp lastMonthTimestamp = Timestamp.valueOf(LocalDateTime.now().minusDays(30));
		Timestamp lastSecondMonthTimestamp = Timestamp.valueOf(LocalDateTime.now().minusDays(60));
		Timestamp lastThirdMonthTimestamp = Timestamp.valueOf(LocalDateTime.now().minusDays(90));

		 boolean isValidUser =checkValidCustomerId(customerId);
		 if(!isValidUser)
			 return null;
		
		List<UserTransaction> lastMonthTransactions = getDataByTransaction(customerId, lastMonthTimestamp, Timestamp.from(Instant.now()));
		List<UserTransaction> lastSecondMonthTransactions = getDataByTransaction(customerId, lastSecondMonthTimestamp, lastMonthTimestamp);
		List<UserTransaction> lastThirdMonthTransactions = getDataByTransaction(customerId, lastThirdMonthTimestamp,lastSecondMonthTimestamp);

		Long lastMonthRewardPoints = getPointsPerMonth(lastMonthTransactions);
		Long lastSecondMonthRewardPoints = getPointsPerMonth(lastSecondMonthTransactions);
		Long lastThirdMonthRewardPoints = getPointsPerMonth(lastThirdMonthTransactions);

		double total=lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints;
		UserRewards points = new UserRewards(customerId,lastMonthRewardPoints,lastSecondMonthRewardPoints,lastThirdMonthRewardPoints,total);
		return points;

	}



	private boolean checkValidCustomerId(Long customerId) {

		List<UserTransaction> transactionsList =TransactionsData.getAllTransactionData();
		for (UserTransaction transaction : transactionsList) {
			if(transaction.getCustomerId()==customerId)
             return true;			
		}

		return false;
	}


	public Long getPointsPerMonth(List<UserTransaction> transactions) {

		long totalPoints=0l;
		for (UserTransaction transaction : transactions) {
			Long points=calculatePoints(transaction);
			totalPoints=totalPoints+points;
		}
		return totalPoints;

	}


}
