package com.evilkissyou.auctionapp.repository;

import com.evilkissyou.auctionapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
