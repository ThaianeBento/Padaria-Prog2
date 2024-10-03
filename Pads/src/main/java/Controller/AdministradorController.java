/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Administrador;
import Repository.AdministradorDAO;
import exception.CpfUsedException;

/**
 *
 * @author thaia
 */
public class AdministradorController {
    AdministradorDAO administradorDAO;

    public AdministradorController(){
        administradorDAO = new AdministradorDAO();
    }

    public String validarCpf(String cpf){
        cpf = cpf.replaceAll("[.-]", "");
        String cpfRegex = "^[0-9]{11}$";
        if(!cpf.matches(cpfRegex)){
            throw new IllegalArgumentException("Cpf deve ter 11 dígitos. " + cpf);
        }
        return cpf;
    }

    public void cadastrarAdministrador(String nome, String cpf, String email, String senha){
        cpf = validarCpf(cpf);
        if(senha == null || senha.isEmpty()){
            throw new IllegalArgumentException("Senha não pode ser vazia.");
        }
        if(nome == null || nome.isEmpty()){
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        Administrador administrador = new Administrador(cpf, nome, senha);
        administradorDAO.create(administrador);
    }

    public Administrador buscarAdministradorPorCPF(String cpf){
        return administradorDAO.readByCPF(cpf);
    }

    public void excluirAdministrador(String cpf){
        Administrador administrador = administradorDAO.readByCPF(cpf);
        if(administrador != null){
            administradorDAO.removeByCPF(cpf);
        }
    }


    
}
