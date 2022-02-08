/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

/**
 *
 * @author Madu Peixoto
 */
import javax.swing.JOptionPane;
import java.sql.*;

public class Conexao {
    final private String driver = "com.mysql.jdbc.Driver"; // definição do driver mysql para acesso aos dados
    final private String url = "jdbc:mysql://localhost/clientes"; // acesso ao BD clientes no servidor
    final private String usuario = "root"; // usuario do mysql - usbwebserver
    final private String senha = "xampp18"; // senha do mysql
    private Connection conexao; // variável que armazenará a conexão aberta 
    public Statement statement; // variável para execução dos comandos sql dentro do ambiente Java
    public ResultSet resultset; // variável que armazenará o resultado da execução de um comando sql
    
    public boolean conecta(){
        boolean result = true;
        try{
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            JOptionPane.showMessageDialog(null, "Conexão estabelecida","Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
        }catch(ClassNotFoundException Driver){
            JOptionPane.showMessageDialog(null, "Driver não localizado"+Driver,"Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
            result = false;
        }catch (SQLException Fonte){
            JOptionPane.showMessageDialog(null, "Fonte de dados não localizada"+Fonte,"Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
            result = false;
        }
        return result;
    }
    
    public void desconecta(){
        try{
            conexao.close();
            JOptionPane.showMessageDialog(null, "Conexão com o banco fechada","Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException fecha){
            
        }
    }
    
    public void executaSQL(String sql){
        try{
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
        }catch (SQLException excecao){
            JOptionPane.showMessageDialog(null, "Erro no comando SQL! \n Erro: "+excecao,"Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
