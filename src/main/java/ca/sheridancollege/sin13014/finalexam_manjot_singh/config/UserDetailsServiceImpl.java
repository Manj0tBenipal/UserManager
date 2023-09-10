package ca.sheridancollege.sin13014.finalexam_manjot_singh.config;

import ca.sheridancollege.sin13014.finalexam_manjot_singh.beans.Programmer;
import ca.sheridancollege.sin13014.finalexam_manjot_singh.repos.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username){
        ca.sheridancollege.sin13014.finalexam_manjot_singh.beans.User user = repo.getUsersByUsername(username);
        if(user==null){
            System.out.print("user not found");
            throw new UsernameNotFoundException("user not found");
        }
        List<String> roles = repo.getRolesById(user.getUserId());
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        for(String role: roles){
            grantList.add((new SimpleGrantedAuthority(role)));
        }
        User springUser = new User(user.getUserName(), user.getEncryptedPassword(), grantList);
        return (UserDetails) springUser;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }}
