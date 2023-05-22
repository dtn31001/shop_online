package com.vti.repository.Irepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vti.entity.Catalog;

@Repository
public interface ICatalogRepository extends JpaRepository<Catalog, Integer>{

	public Catalog findByName(String catalogName);

}
