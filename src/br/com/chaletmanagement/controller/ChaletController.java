package br.com.chaletmanagement.controller;

import java.util.List;

import br.com.chaletmanagement.context.DAO.ChaletDAO;
import br.com.chaletmanagement.context.DAOImplementation.ChaletDAOImplementation;
import br.com.chaletmanagement.model.Chalet;

public class ChaletController
{
    private ChaletDAO chaletDAO;

    public ChaletController()
    {
        chaletDAO = new ChaletDAOImplementation();
    }

    public String addChalet(Chalet chalet)
    {
        return chaletDAO.addChalet(chalet);
    }
    
    public String updateChalet(Chalet chalet)
    {
        return chaletDAO.updateChalet(chalet);
    }

    public String deleteChalet(Chalet chalet)
    {
        return chaletDAO.deleteChalet(chalet);
    }

    public List<Chalet> getAllChalets()
    {
        return chaletDAO.getAllChalets();
    }

    public Chalet searchByCode(String code)
    {
        return chaletDAO.searchByCode(code);
    }
    
    public Chalet searchById(Integer id)
    {
    	return chaletDAO.searchById(id);
    }
}