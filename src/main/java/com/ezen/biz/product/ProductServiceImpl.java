package com.ezen.biz.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.ProductDAO;
import com.ezen.biz.dto.ProductVO;

import utils.Criteria;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO pDao;
	
	
	@Override
	public List<ProductVO> getProductList() {
		return pDao.getProductList();
	}
	
	public ProductVO getProduct(ProductVO vo) {
		return pDao.getProduct(vo);
	}

	@Override
	public List<ProductVO> searchProduct(String search) {
		return pDao.getSearchProduct(search);
	}

	@Override
	public List<ProductVO> getProductListWithPaging(Criteria criteria) {
		return pDao.getProductListWithPaging(criteria);
	}

	@Override
	public int getCountProduct() {
		return pDao.getCountProduct();
	}

	@Override
	public void insertProduct(ProductVO vo) {
		pDao.insertProduct(vo);
		
	}

	@Override
	public void updateProduct(ProductVO vo) {
		pDao.updateProduct(vo);
	}

	@Override
	public void deleteProduct(int pseq) {
		pDao.deleteProduct(pseq);
		
	}
	
	
}
