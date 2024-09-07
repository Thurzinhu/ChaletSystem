package br.com.chaletmanagement.controller;

import java.util.List;

import br.com.chaletmanagement.context.ChaletDAO;
import br.com.chaletmanagement.context.ChaletDAOImplementation;
import br.com.chaletmanagement.model.Chalet;

public class ChaletController
{
    public String addChalet(Chalet chalet)
    {
        ChaletDAO dao = new ChaletDAOImplementation();
        return dao.addChalet(chalet);
    }
    
    public String updateChalet(Chalet chalet)
    {
        ChaletDAO dao = new ChaletDAOImplementation();
        return dao.updateChalet(chalet);
    }

    public String deleteChalet(Chalet chalet)
    {
        ChaletDAO dao = new ChaletDAOImplementation();
        return dao.deleteChalet(chalet);
    }

    public List<Chalet> getAllChalets()
    {
        ChaletDAO dao = new ChaletDAOImplementation();
        return dao.getAllChalets();
    }

    public Chalet searchById(String id)
    {
        ChaletDAO dao = new ChaletDAOImplementation();
        return dao.searchById(id);
    }
}