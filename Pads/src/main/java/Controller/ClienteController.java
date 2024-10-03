/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Cliente;
import Repository.ClienteDAO;
import exception.CpfUsedException;

/**
 *
 * @author thaia
 */
public class ClienteController {
    ClienteDAO clienteDAO;

    public ClienteController(){
        clienteDAO = new ClienteDAO();
    }

    public String validarCpf(String cpf){
        cpf = cpf.replaceAll("[.-]", "");
        String cpfRegex = "^[0-9]{11}$";
        if(!cpf.matches(cpfRegex)){
            throw new IllegalArgumentException("Cpf deve ter 11 dígitos.");
        }
        return cpf;
    }

    public void create(String nome, String cpf)  {
        cpf = validarCpf(cpf);
        if(clienteDAO.read(cpf) != null){
            throw new CpfUsedException("Cpf já está cadastrado.");
        }
        Cliente cliente = new Cliente(cpf, nome);
        clienteDAO.create(cliente);
    }

    public Cliente buscarClientePorCPF(String cpf){
        return clienteDAO.read(cpf);
    }

    public void excluirCliente(String cpf){
        Cliente cliente = clienteDAO.read(cpf);
        if(cliente != null){
            clienteDAO.remove(cpf);
        }
    }

    public void update(Cliente cliente){
        clienteDAO.update(cliente);
    }
    
}
