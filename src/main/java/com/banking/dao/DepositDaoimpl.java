package com.banking.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.banking.model.Balance;

@Repository
public class DepositDaoimpl implements DipositDao {

	@Autowired
	SessionFactory sessionfactory;

	@Override
	public List<Balance> balance(String name) {

		List<Balance> balance = sessionfactory.getCurrentSession()
				.createQuery("select ac.balance from Account ac where ac.balance.name=:name").setParameter("name", name)
				.list();
		System.out.println(balance);

		return balance;
	}

	@Override
	public boolean updatebalance(int tbalance, int id) {

		sessionfactory.getCurrentSession().createQuery("update Balance bc set bc.ammount=:tbalance Where id=:id")
				.setParameter("tbalance", tbalance).setParameter("id", id).executeUpdate();
		return true;
	}

	@Override
	public Balance getBalanceById(int id) {

		return sessionfactory.getCurrentSession().get(Balance.class, id);

	}

	@Override
	public Balance getBalanceByAccNo(String acNo) {
		
		Balance balance =(Balance) sessionfactory.getCurrentSession().createQuery("Select ac.balance From Account ac Where ac.balance.accountNo=:acNo").setParameter("acNo", acNo).list().get(0);
				
		if(balance !=null) {
			
			return balance;
			
		}else {
			
			return null;
		}		
		
	}

}
