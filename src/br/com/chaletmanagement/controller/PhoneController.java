package br.com.chaletmanagement.controller;

import java.util.List;

import br.com.chaletmanagement.context.DAO.PhoneDAO;
import br.com.chaletmanagement.context.DAOImplementation.PhoneDAOImplementation;
import br.com.chaletmanagement.model.Phone;

public class PhoneController
{
    private PhoneDAO phoneDAO;

    public PhoneController()
    {
        phoneDAO = new PhoneDAOImplementation();
    }

    public String addPhone(Phone phone)
    {
        return phoneDAO.addPhone(phone);
    }

    public String updatePhone(Phone phone)
    {
        return phoneDAO.updatePhone(phone);
    }

    public List<Phone> getAllPhones()
    {
        return phoneDAO.getAllPhones();
    }

    public Phone searchById(Integer phoneId)
    {
        return phoneDAO.searchById(phoneId);
    }

    public Phone searchByClientId(Integer clientId)
    {
        return phoneDAO.searchByClientId(clientId);
    }
}