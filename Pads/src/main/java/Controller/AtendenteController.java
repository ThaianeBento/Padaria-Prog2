/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Atendente;
import Repository.AtendenteDAO;
import exception.CpfUsedException;

/**
 *
 * @author thaia
 */
public class AtendenteController {
    AtendenteDAO atendenteDAO;

    public AtendenteController(){
        atendenteDAO = new AtendenteDAO();
    }

    public String validarCpf(String cpf){
        cpf = cpf.replaceAll("[.-]", "");
        String cpfRegex = "^[0-9]{11}$";
        if(!cpf.matches(cpfRegex)){
            throw new IllegalArgumentException("Cpf deve ter 11 dígitos. " + cpf);
        }
        return cpf;
    }

    public void create(String nome, String cpf, String senha)  {
        cpf = validarCpf(cpf);
        if(atendenteDAO.readByCPF(cpf) != null){
            throw new CpfUsedException("Cpf já está cadastrado.");
        }
        if(senha == null || senha.isEmpty()){
            throw new IllegalArgumentException("Senha não pode ser vazia.");
        }
        if(nome == null || nome.isEmpty()){
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        Atendente atendente = new Atendente(senha, cpf, nome);
        atendenteDAO.create(atendente);
    }

    public Atendente buscarAtendentePorCPF(String cpf){
        Atendente atendente = atendenteDAO.readByCPF(cpf);
        if(atendente == null){
            throw new IllegalArgumentException("Atendente não encontrado.");
        }
        return atendente;
    }

    public void excluirAtendente(String cpf){
        Atendente atendente = atendenteDAO.readByCPF(cpf);
        if(atendente != null){
            atendenteDAO.remove(cpf);
        }
    }
    
}
