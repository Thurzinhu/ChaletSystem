package br.com.chaletmanagement.context.DAO;

import java.util.List;

import br.com.chaletmanagement.model.Chalet;

public interface ChaletDAO {
	String addChalet(Chalet chalet);

	String updateChalet(Chalet chalet);

	String deleteChalet(Chalet chalet);

	List<Chalet> getAllChalets();

	Chalet searchByCode(String code);

	Chalet searchById(Integer chaletId);
}
