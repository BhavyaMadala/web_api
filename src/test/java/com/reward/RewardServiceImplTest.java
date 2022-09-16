package com.reward;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.reward.dto.UserRewards;
import com.reward.dto.UserTransaction;
import com.reward.service.RewardServiceImpl;
import com.reward.util.TransactionsData;  

@RunWith(SpringRunner.class)
@SpringBootTest
public class RewardServiceImplTest {

	@Mock
	private RewardServiceImpl pointServiceImpl;

	@Test
	public void test_getPointsByCustomerId()
	{
		UserRewards	points=new UserRewards (1l,123,0,0,0); 
		Mockito.when(pointServiceImpl.getPoints(1l)).thenReturn(points);
		UserRewards points1=pointServiceImpl.getPoints(1l);
		assertEquals(1l, points1.getCustomerId()); 		

	}

	@Test
	public void test_getPointsPerMonth()
	{	
		List<UserTransaction>	transactionList=new ArrayList<>();
		UserTransaction	transaction=new UserTransaction (1l,120l,Timestamp.valueOf("2022-08-09 00:00:00"),1l); 
		transactionList.add(transaction);
		Mockito.when(pointServiceImpl.getPointsPerMonth(transactionList)).thenReturn(2l);
		long points=pointServiceImpl.getPointsPerMonth(transactionList);
		assertEquals(2l, points); 		


	}
	
	@Test
	public void test_userRewards()
	{
		UserRewards	points=new UserRewards (1l,123,0,0,123); 
		assertEquals(1l, points.getCustomerId()); 		

	}
	@Test
	public void test_userRewardData()
	{
		UserRewards	points=new UserRewards (1l,123,10,12,145); 
		assertEquals(145l, points.getTotalPoints(),0); 		

	}
	@Test
	public void test_transactionData()
	{		List<UserTransaction> transactions=new ArrayList<>();

	UserTransaction	transactionsData=new UserTransaction (4l,220l,Timestamp.valueOf("2022-08-07 00:00:00"),1l); 
	UserTransaction	transactionsData1=new UserTransaction (4l,220l,Timestamp.valueOf("2022-08-07 00:00:00"),1l); 
	transactions.add(transactionsData1);
	transactions.add(transactionsData);
		assertEquals(2, transactions.size()); 		

	}
	@Test
	public void test_transactionData1()
	{		List<UserTransaction> transactions=new ArrayList<>();

	UserTransaction	transactionsData=new UserTransaction (4l,220l,Timestamp.valueOf("2022-08-07 00:00:00"),1l); 
	transactions.add(transactionsData);
		assertEquals(220, transactions.get(0).getTransactionAmount()); 		

	}

}
