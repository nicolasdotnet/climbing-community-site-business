package org.amisescalade.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.amisescalade.dao.TopoRepository;
import org.amisescalade.entity.Topo;
import org.amisescalade.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TopoServiceImpl implements ITopoService{
	
	private static final Logger log = LogManager.getLogger(TopoServiceImpl.class);
	
	@Autowired
	private TopoRepository topoRepository;

	@Override
	public Topo register (String topoArea, String topoTitle, String topoDescription, User topoOwner) throws Exception {
		
		// TODO check by user & by title for no register double ?
		
		Topo topo = new Topo();
		topo.setTopoArea(topoArea);
		topo.setTopoTitle(topoTitle);
		topo.setTopoDescription(topoDescription);
		topo.setTopotopoOwner(topoOwner);
		
		topo.setTopoDate(new Date());
                topo.setTopoStatus(true);
		
		return topoRepository.save(topo);
	}

	@Override
	public Topo edit(Topo topo) throws Exception {
		
		Optional<Topo> topoFind = topoRepository.findById(topo.getTopoId());
		
		if (!topoFind.isPresent()) {
			
			log.error("Modification Impossible ! le topo "+ topo.getTopoId()+" n'existe pas dans la base.");
			
			throw new Exception("Le topo n'existe pas !");	
		}
		
		topoFind.get().setTopoArea(topo.getTopoArea());
                topoFind.get().setTopoDescription(topo.getTopoDescription());
                topoFind.get().setTopoTitle(topo.getTopoTitle());
		
		return topoRepository.saveAndFlush(topoFind.get());
	}

	@Override
	public Topo getTopo(Long id) throws Exception {
		
		Optional<Topo> topoFind = topoRepository.findById(id);
		
		if (!topoFind.isPresent()) {
			
			log.error("Modification Impossible ! le topo "+ id +" n'existe pas dans la base.");
			
			throw new Exception("Le topo n'existe pas !");
			
			
		}
		return topoFind.get() ;
	}

	@Override
	public List<Topo> getAllTopos() {
		
		return topoRepository.findAll();
	}

	@Override
	public List<Topo>  getTopoByTitle(String title) throws Exception {
		
		return topoRepository.findByTopoTitleContainingIgnoreCase(title);
	}

    @Override
    public void delete(Long topoId) {
        
        topoRepository.deleteById(topoId);
        
    }

    @Override
    public Topo makeAvailable(Topo topo) throws Exception {
        
        		Optional<Topo> topoFind = topoRepository.findById(topo.getTopoId());
		
		if (!topoFind.isPresent()) {
			
			log.error("Modification Impossible ! le topo "+ topo.getTopoId()+" n'existe pas dans la base.");
			
			throw new Exception("Le topo n'existe pas !");	
		}
		
                topoFind.get().setTopoStatus(topo.getTopoStatus());
		
		return topoRepository.saveAndFlush(topoFind.get());
        
    }

    @Override
    public List<Topo> getAllToposByOwner(User ownerTopo) {
            return topoRepository.findByTopoOwner(ownerTopo);
        
    }

}
