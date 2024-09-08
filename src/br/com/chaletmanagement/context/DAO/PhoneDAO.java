package br.com.chaletmanagement.context.DAO;

import java.util.List;

import br.com.chaletmanagement.model.Phone;

public interface PhoneDAO {
	String addPhone(Phone phone);

	String updatePhone(Phone phone);

	List<Phone> getAllPhones();

	Phone searchById(Integer phoneId);

	Phone searchByClientId(Integer clienId);
}
