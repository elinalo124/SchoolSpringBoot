package com.elina.school.service.serviceImpl;

import com.elina.school.exception.NotFoundException;
import com.elina.school.model.Aptitude;
import com.elina.school.model.Course;
import com.elina.school.repository.AptitudeRepository;
import com.elina.school.service.AptitudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("aptitudeService")
@Transactional
public class AptitudeServiceImpl implements AptitudeService {
    private AptitudeRepository aptitudeRepository;
    @Autowired
    public void setAptitudeRepository(AptitudeRepository aptitudeRepository) {
        this.aptitudeRepository = aptitudeRepository;
    }

    @Override
    public void save(Aptitude aptitude) {
        aptitudeRepository.save(aptitude);
    }

    @Override
    public Aptitude findById(Long id) {
        Optional<Aptitude> optionalAptitude = aptitudeRepository.findById(id);

        if(optionalAptitude.isPresent())
            return optionalAptitude.get();
        else
            throw new NotFoundException("Aptitude Not Found");
    }

    @Override
    public List<Aptitude> findAll() {
        return (List<Aptitude>) aptitudeRepository.findAll();
    }

    @Override
    public void deleteById(Long aptitude_id) {
        if(aptitudeRepository.findById(aptitude_id).isPresent()){
            aptitudeRepository.deleteById(aptitude_id);
        }
    }
}
