package service;

import java.util.List;

import domain.items.Items;
import domain.items.ItemsDao;

public class ItemsService {
	private ItemsDao itemsDao;

	public ItemsService() {
			this.itemsDao = new ItemsDao();
	}
	//전체리스트 목록 가져오기
	public List<Items> items_list(){
		System.out.println("서비스 진입");
		return itemsDao.findAll();
	}
	//상세보기
	public Items detail(String id) {
		return itemsDao.findById(id);
	}
	public int delete(String id) {
		return itemsDao.deleteById(id);
	}
	
	

}
