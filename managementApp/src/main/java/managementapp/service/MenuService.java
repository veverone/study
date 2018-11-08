package managementapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import managementapp.model.Employee;
import managementapp.model.Menu;

public interface MenuService {
	
	public List<Menu> findByMenuName(String name);
	
	public List<Menu> findByPriceBelowLimit(Long priceLimit);
	
	public Iterable<Menu> getAll();

}
