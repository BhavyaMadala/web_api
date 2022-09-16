package com.reward.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.reward.dto.UserTransaction;

public class TransactionsData {

	public static List<UserTransaction>  getAllTransactionData() {
		List<UserTransaction> transactions=new ArrayList<>();

		UserTransaction transaction1=new UserTransaction(1l,110l,new Timestamp(new Date().getTime()),1l);
		transactions.add(transaction1);

		UserTransaction transaction2=new UserTransaction(2l,982l,new Timestamp(new Date().getTime()),2l);
		transactions.add(transaction2);
		
		UserTransaction transaction3=new UserTransaction(3l,599l,new Timestamp(new Date().getTime()),3l);
		transactions.add(transaction3);

		UserTransaction transaction4=new UserTransaction(4l,220l,Timestamp.valueOf("2022-08-07 00:00:00"),1l);
		transactions.add(transaction4);

		UserTransaction transaction5=new UserTransaction(2l,281,Timestamp.valueOf("2022-08-07 00:00:00"),2l);
		transactions.add(transaction5);

		UserTransaction transaction6=new UserTransaction(3l,211l,Timestamp.valueOf("2022-08-07 00:00:00"),3l);
		transactions.add(transaction6);

		UserTransaction transaction7=new UserTransaction(4l,32l,Timestamp.valueOf("2022-07-09 00:00:00"),1l);
		transactions.add(transaction7);

		UserTransaction transaction8=new UserTransaction(2l,512l,Timestamp.valueOf("2022-07-09 00:00:00"),2l);
		transactions.add(transaction8);

		UserTransaction transaction9=new UserTransaction(3l,79l,Timestamp.valueOf("2022-07-09 00:00:00"),3l);
		transactions.add(transaction9);

		return transactions;

	}
}
