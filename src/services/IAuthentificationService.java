/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;

/**
 *
 * @author abirk
 */
public interface IAuthentificationService {
    public void signup(String first_name,String last_name,String username, String email, String password, String gender);
    public void login( String user_name,String email, String password);
    public boolean isUserExist(String user_name,String email); 
    public boolean checkEmailExists(String email);
    public boolean checkIfUserCanBeAdded(User u);
}
