package com.aikan.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aikan.entity.Catalog;
import com.aikan.mapping.CatalogMapper;
import com.aikan.service.CatalogService;

@Service("catalogServiceImpl")
public class CatalogServiceImpl implements CatalogService {
	
	@Autowired
	private CatalogMapper catalogMapper;
	@Override
	public int save(Catalog catalog) {
		int result=0;
		Catalog catalogExist=catalogMapper.selectOneById(catalog);
		if(catalogExist!=null){
			return -1;
		}
		result = catalogMapper.insert(catalog);
		return result;
	}

	@Override
	public List<Catalog> getAllChapter(Integer catalogId) {
		List<Catalog> catalogList=catalogMapper.selectAllChapter(catalogId);
		return catalogList;
	}

	
	@Override
	public int modify(Catalog catalog) {
		int result = 0;
		Catalog catalogExist = catalogMapper.selectOneById(catalog);
		if(catalogExist==null){
			return -1;
		}
		result = catalogMapper.update(catalog);
		return result;
	}

	@Override
	public Catalog getOneById(Catalog catalog) {
		
		return catalogMapper.selectOneById(catalog);
	}
	
	@Override
	public int removeOne(Catalog catalog) {
		int result=0;
		Catalog catalogExist=catalogMapper.selectOneById(catalog);
		if(catalogExist==null){
			return -1;
		}
		result = catalogMapper.deleteOne(catalog);
		return result;		
	}
	
	@Override
	public int removeManyChapter(Integer[] bookChapterIds, Integer catalogId) {
		int result=0;
		result=catalogMapper.deleteManyChapter(bookChapterIds,catalogId);
		if(result==0){
			//É¾³ýÊ§°Ü
			return 0;
		}
		return 1;
	}
}
