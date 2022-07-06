package com.switchfully.eurder.itemgroup.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemGroupRepository extends JpaRepository<ItemGroup,Integer> {
}
