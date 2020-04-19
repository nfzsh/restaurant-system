package com.zsh.restaurantsystem.service;

import com.zsh.restaurantsystem.entity.Admin;
import com.zsh.restaurantsystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public void setAdmin(String name, String password, int authority) {
        Admin admin = new Admin(name,password,authority);
        adminRepository.save(admin);
    }
    public Admin getAdmin(String name){
        return adminRepository.findAdmin(name);
    }
    public List<Admin> getAllAdmin(){

        return adminRepository.findAll();
    }
    public void UpdateAdmin(Admin admin){
        if (admin.getPassword().equals("")){
            adminRepository.UpdateAdmin(admin.getName(),
                    admin.getAuthority(),admin.getId());
        }
        else
            adminRepository.UpdateAdmin(admin.getName(),
                    admin.getPassword(),
                    admin.getAuthority(),admin.getId());

    }
    public void DeleteAdmin(int id){
        adminRepository.deleteById(id);
    }

}
