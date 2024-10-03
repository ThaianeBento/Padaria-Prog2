package Controller;

import Model.Cliente;
import Model.Produto;
import Model.Venda;
import Repository.VendaDAO;

import java.util.List;

public class VendaController {
    VendaDAO vendaDAO;

    public VendaController(){
        vendaDAO = new VendaDAO();
    }

    public void create(List<Produto> produtos, Cliente cliente){
        if(produtos.isEmpty()){
            throw new IllegalArgumentException("Venda deve ter pelo menos um produto.");
        }
        if(cliente == null){
            throw new IllegalArgumentException("Venda deve ter um cliente.");
        }
        vendaDAO.create(produtos, cliente);
    }

    public Venda buscarVendaPorId(long id){
        return vendaDAO.buscarPorId(id);
    }

    public void remove(long id){
        Venda venda = vendaDAO.buscarPorId(id);
        if(venda != null){
            vendaDAO.remove(id);
        }
    }


}
