package com.vilcapoma.notesapp.repository;

import android.content.Context;
import android.service.autofill.UserData;
import android.widget.Toast;

import com.orm.SugarRecord;
import com.vilcapoma.notesapp.models.User;

import java.util.List;

public class UserRepository {
    public static List<User> list(){
        List<User> users = SugarRecord.listAll(User.class);
        return users;
    }

    public static User Read(Long id){
        User user = SugarRecord.findById(User.class,id);
        return user;
    }

    public static void Create(String username,String fullname,String email,String password){
        User user = new User(username,fullname,email,password);
        SugarRecord.save(user);
    }

    public static void Update(Long id,String username,String fullname,String email,String password){
        User user = SugarRecord.findById(User.class,id);
        user.setUsername(username);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);
        SugarRecord.save(user);
    }

    public static void Delete(Long id){
        User user = SugarRecord.findById(User.class,id);
        SugarRecord.delete(user);
    }

    public static User Login(Context context,String username, String password){
        for(User user: list()){
            if(user.getUsername().equalsIgnoreCase(username)&& user.getPassword().equals(password)){
                return user;
            }
        }
        Toast.makeText(context,"Usuarios incorrectos", Toast.LENGTH_SHORT).show();
        return null;
    }


}
