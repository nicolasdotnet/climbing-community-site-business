package org.amisescalade.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.amisescalade.dao.SectorRepository;
import org.amisescalade.entity.Sector;
import org.amisescalade.entity.Spot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SectorServiceImpl implements ISectorService{
	
	private static final Logger log = LogManager.getLogger(SectorServiceImpl.class);

	@Autowired
	private SectorRepository sectorRepository;

	@Override
	public Sector register(Sector sector) throws Exception {
		
		// check by title for no register double ?

		sector.setSectorDate(new Date());
		return sectorRepository.save(sector);
	}

	@Override
	public Sector edit(Sector sector) throws Exception {
		
		Optional<Sector> sectorFind = sectorRepository.findById(sector.getSectorId());

		if (sectorFind.isEmpty()) {

			log.error("Modification Impossible ! le sector " + sector.getSectorId() + " n'existe pas dans la base.");

			throw new Exception("Le sector n'existe pas !");

		}
		sector.setSectorDate(new Date());
		return sectorRepository.saveAndFlush(sector);
	}

	@Override
	public Sector displayOne(Long id) throws Exception {
		
		Optional<Sector> sectorFind = sectorRepository.findById(id);

		if (sectorFind.isEmpty()) {

			log.error("Modification Impossible ! le sector " + id + " n'existe pas dans la base.");

			throw new Exception("Le sector n'existe pas !");

		}
		return sectorFind.get();
	}

	@Override
	public List<Sector> displayAll() {
		
		return sectorRepository.findAll();
	}

	@Override
	public List<Sector> displayBySectorName(String sectorName) throws Exception {
		
		return sectorRepository.findBySectorNameContainingIgnoreCase(sectorName);
	}

	@Override
	public List<Sector> displayBySpot(Spot spot) throws Exception {
		
		return sectorRepository.findBySpot(spot);
	}

}