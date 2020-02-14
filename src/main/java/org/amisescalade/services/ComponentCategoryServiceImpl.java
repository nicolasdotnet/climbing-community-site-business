package org.amisescalade.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.amisescalade.dao.ComponentCategoryRepository;
import org.amisescalade.entity.ComponentCategory;
import org.amisescalade.entity.UserCategory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ComponentCategoryServiceImpl implements IComponentCategoryService {
	
	private static final Logger log = LogManager.getLogger(ComponentCategoryServiceImpl.class);
	
	@Autowired
	private ComponentCategoryRepository componentCategoryRepository;

	@Override
	public ComponentCategory register(String componentCategoryLabel) throws Exception {
		
		ComponentCategory categoryFind = componentCategoryRepository.findByComponentCategoryLabel(componentCategoryLabel);
		
		if (categoryFind != null) {
			
			log.error("La component catégorie existe déjà !");
			
			throw new Exception("La component catégorie existe déjà !");
			
		}
		
		ComponentCategory componentCategory = new ComponentCategory();
		
		componentCategory.setComponentCategoryLabel(componentCategoryLabel);
		componentCategory.setComponentCategoryDate(new Date());
		
		return componentCategoryRepository.save(componentCategory);
	}

	@Override
	public ComponentCategory edit(ComponentCategory componentCategory) throws Exception {
		
		Optional<ComponentCategory> categoryFind = componentCategoryRepository.findById(componentCategory.getComponentCategoryId());
		
		if (categoryFind.isEmpty()) { 
			
			log.error("Modification Impossible ! la component categorie "+ componentCategory.getComponentCategoryId()+" n'existe pas dans la base.");
		
			throw new Exception("La component catégorie n'existe pas !");
			
		}
		
		// vérification de la saisie avec Spring Validator ?
		
		return componentCategoryRepository.saveAndFlush(componentCategory);
	}
	
	@Override
	public ComponentCategory getComponentCategory(Long id) throws Exception {
		
		Optional<ComponentCategory> categoryFind = componentCategoryRepository.findById(id);

		if (categoryFind.isEmpty()) {

			log.error("Modification Impossible ! la categorie " + id
					+ " n'existe pas dans la base.");

			throw new Exception("La catégorie n'existe pas !");

		}
		return categoryFind.get();
	}

	@Override
	public List<ComponentCategory> getAllComponentCategory() {
		
		return componentCategoryRepository.findAll();
	}

	@Override
	public List<ComponentCategory> getComponentCategoryByLabel(String label) {
		
		return componentCategoryRepository.findByComponentCategoryLabelContainingIgnoreCase(label);
	}

	}

