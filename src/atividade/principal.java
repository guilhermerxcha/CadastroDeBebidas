package atividade;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class principal {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int opcaoMenuPrincipal;
        int opcaoContinuar;
        Scanner leitor = new Scanner(System.in);
        ArrayList<cliente> clientes = new ArrayList();
        cliente cliente = new cliente();
        
        do{
            System.out.println("===================== MENU PRINCIPAL =======================");
            System.out.println("1 - Cadastrar Bebidas");
            System.out.println("2 - Mostrar bebidas cadastradas");
            System.out.println("3 - Pesquisar Cadastro de bebidas Espec�fica");
            System.out.println("4 - Excluir Cadastro de bebida Espec�fica");
            System.out.println("5 - Alterar Cadastro de bebida Espec�fica");
            System.out.println("6 - Sair");
            opcaoMenuPrincipal = leitor.nextInt();
            switch (opcaoMenuPrincipal) {
            case 1:

                
                System.out.println("========= CADASTRAR bebidas ============");
                System.out.println("Digite o nome da bebida:");
                cliente.nome = leitor.next();
                System.out.println("Digite o pre�o");
                cliente.preco = leitor.nextInt();
                System.out.println("Digite a marca da bebida:");
                cliente.marca = leitor.next();
                System.out.println("Digite o c�digo da bebida:");
                cliente.codigo = leitor.next();
                System.out.println("Digite o c�digo de barra da bebida:");
                cliente.Codigobarra = leitor.nextInt();
                System.out.println("Digite a data de vencimento");
                cliente.Data = leitor.next();
                Connection con = conexaoBanco.obterConexao();
                String sql = "INSERT INTO cliente (nome, preco, codigo, marca, Codigobarra," +
                        " data) VALUES(?,?,?,?,?,?)";
                    
                try {
                    PreparedStatement preparador = con.prepareStatement(sql);
                    preparador.setString(1, cliente.getNome());
                    preparador.setInt(2, cliente.getPreco());
                    preparador.setString(3, cliente.getCodigo());
                    preparador.setString(4, cliente.getMarca());
                    preparador.setInt(5, cliente.getCodigobarra());
                    preparador.setString(6, cliente.getData());
                    
                    preparador.execute();
                    
                    preparador.close();
                    
                    System.out.println("Bebida cadastrado com sucesso!!!");
                    
                } catch (SQLException e) {
                    
                    e.printStackTrace();
                }

                clientes.add(cliente);
                
                

                do
                {
                System.out.println("CADASTRO REALIZADO!");

                System.out.println("Deseja cadastrar mais alguma bebida? Digite 1, SE N�O DIGITE 2");
                opcaoContinuar = leitor.nextInt();
                if(opcaoContinuar == 1){
                System.out.println("========= CADASTRAR bebidas ============");
                System.out.println("Digite o nome da bebida:");
                cliente.nome = leitor.next();
                System.out.println("Digite o pre�o da bebida");
                cliente.preco = leitor.nextInt();
                System.out.println("Digite a marca");
                cliente.marca = leitor.next();
                System.out.println("Digite o c�digo da bebida:");
                cliente.codigo = leitor.next();
                System.out.println("Digite o c�digo de barra da bebida:");
                cliente.Codigobarra = leitor.nextInt();
                System.out.println("Digite a data de validade");
                cliente.Data = leitor.next();
                clientes.add(cliente);
                 con = conexaoBanco.obterConexao();
                 sql = "INSERT INTO cliente (nome, preco, codigo, marca, codigobarra," +
                        " data) VALUES(?,?,?,?,?,?)";
                    
                try {
                    PreparedStatement preparador = con.prepareStatement(sql);
                    preparador.setString(1, cliente.getNome());
                    preparador.setInt(2, cliente.getPreco());
                    preparador.setString(3, cliente.getCodigo());
                    preparador.setString(4, cliente.getMarca());
                    preparador.setInt(5, cliente.getCodigobarra());
                    preparador.setString(6, cliente.getData());
                    
                    preparador.execute();
                    
                    preparador.close();
                    
                    System.out.println("Bebida cadastrado com sucesso!!!");
                    
                } catch (SQLException e) {
                    
                    e.printStackTrace();
                }
                }
                else{
                    break;
                }
                

                }
                
                while(opcaoContinuar == 1);
                
                
                break;
            case 2:
                System.out.println("========= MOSTRAR TODAS as bebidas ============");
                int i;

                sql = "SELECT * from cliente";
                    
                    try {
                         con = conexaoBanco.obterConexao();
                        PreparedStatement preparador = con.prepareStatement(sql);
                        ResultSet resultado = preparador.executeQuery();
                        
                    
                        while(resultado.next()){
                            cliente cliente1 = new cliente();
                            cliente1.setNome(resultado.getString("nome"));
                            cliente1.setPreco(resultado.getInt("pre�o"));
                            cliente1.setMarca(resultado.getString("marca"));
                            cliente1.setCodigo(resultado.getString("codigo"));
                            cliente1.setCodigobarra(resultado.getInt("codigo de barra"));
                            cliente1.setData(resultado.getString("data de vencimento"));
                            clientes.add(cliente1);
                        }
                        
                        for ( i=0; i < clientes.size(); i++){
                            System.out.println("Nome: " + clientes.get(i).getNome());
                            System.out.println("Pre�o: " + clientes.get(i).getPreco());
                            System.out.println("C�digo: " + clientes.get(i).getCodigo());
                            System.out.println("Marca: " + clientes.get(i).getMarca());
                            System.out.println("Codigo de barra: " + clientes.get(i).getCodigobarra());
                            System.out.println("Data de vencimento: " + clientes.get(i).getData());

                            System.out.println("============================================");
                        }
                        
                        
                        
                    } catch (SQLException e) {
                
                        e.printStackTrace();
                    }            
                break;
            case 3:
                System.out.println("========= PESQUISAR Bebidass============");
                
                System.out.println("Digite o nome : ");
                
                
                try {
                    String localizar = leitor.next();
                    sql = "SELECT * from cliente where nome = '"+ localizar +"'";
                     con = conexaoBanco.obterConexao();
                    PreparedStatement preparador = con.prepareStatement(sql);
                    ResultSet resultado = preparador.executeQuery();
                    
                
                    while(resultado.next()){
                        cliente cliente2 = new cliente();
                        cliente2.setNome(resultado.getString("nome"));
                        cliente2.setPreco(resultado.getInt("pre�o"));
                        cliente2.setMarca(resultado.getString("marca"));
                        cliente2.setCodigo(resultado.getString("c�digo"));
                        cliente2.setCodigobarra(resultado.getInt("c�digo de barra"));
                        cliente2.setData(resultado.getString("vencimento"));
                        clientes.add(cliente2);
                    }
                    
                    for ( i=0; i < clientes.size(); i++){
                        System.out.println("Nome: " + clientes.get(i).getNome());
                        System.out.println("pre�o: " + clientes.get(i).getPreco());
                        System.out.println("c�digo: " + clientes.get(i).getCodigo());
                        System.out.println("Marca: " + clientes.get(i).getMarca());
                        System.out.println("C�digo de barra: " + clientes.get(i).getCodigobarra());
                        System.out.println("Data de vencimento: " + clientes.get(i).getData());

                        System.out.println("============================================");
                    }
                    
                    
                    
                } catch (SQLException e) {
            
                    e.printStackTrace();
                }

                
                break;
            case 4:
                System.out.println("========= EXCLUIR bebidas============");
                String remover;
                System.out.println("Informe o Nome : ");
                remover = leitor.next();
                
                   
                   
                   try { 
                    
                    sql = "DELETE FROM cliente where nome = '"+ remover +"'";
                    con = conexaoBanco.obterConexao();
                   PreparedStatement preparador = con.prepareStatement(sql);
                   System.out.println("Removido com sucesso");

                   preparador.execute();
                   preparador.close();
                   }
                   catch (SQLException e) {
                	   System.out.println("Removido com sucesso");

                       e.printStackTrace();

                   }
                

                break;
            case 5:
                System.out.println("========= ALTERAR bebida============");
                try {
                String alterar;
                String nome = "Jo�o";
                String idade = "25";
                String telefone = "19181383";
                String sexo = "M";
                String celular = "99999999";
                String data = "09/10/2019";
                System.out.println("Digite o nome que deseja alterar.");
                alterar = leitor.next();
                con = conexaoBanco.obterConexao();
                sql = "UPDATE cliente SET nome = ?, idade = ?, telefone = ?, sexo = ?, celular = ?, data = ?" +
                        " WHERE nome = '" +alterar +"'";
                PreparedStatement preparador = con.prepareStatement(sql);
                preparador.setString(1, nome);
                preparador.setString(2, idade);
                preparador.setString(3, telefone);
                preparador.setString(4, sexo);
                preparador.setString(5, celular);
                preparador.setString(6, data);
                
                preparador.execute();
                preparador.close();
                System.out.println("Alterado com sucesso!");
                }
                catch (SQLException e) {

                    e.printStackTrace();

                }

                break;
            case 6:
                System.out.println("========= O PROGRAMA FOI ENCERRADO =========");
                
                break;
            }
            
        
        }while(opcaoMenuPrincipal!=6);
        
        leitor.close();
    }
    

}

