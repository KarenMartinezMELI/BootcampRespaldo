package com.meli.tucasita.service;

import com.meli.tucasita.dto.district.DistrictDTO;
import com.meli.tucasita.exception.district.DistrictWNameAlreadyExistsException;
import com.meli.tucasita.exception.district.NoSuchDistrictNameException;
import com.meli.tucasita.model.District;
import com.meli.tucasita.repository.IDistrictRepository;
import com.meli.tucasita.util.Parse;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
public class DistrictServiceImp implements IDistrictService {

    IDistrictRepository districtRepository;

    //@Autowired
    public DistrictServiceImp(IDistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public DistrictDTO getDistrictByName(String name) {
        Optional<District> district = districtRepository.findByName(name);
        if(!district.isPresent()){
           throw new NoSuchDistrictNameException(name);
        }
        return Parse.parseDistrictToDTO(district.get());
    }

    @Override
    public List<DistrictDTO> getDistricts() {
        return districtRepository.getAll().stream().map(d->Parse.parseDistrictToDTO(d)).collect(Collectors.toList());
    }

    @Override
    public DistrictDTO addDistrict(DistrictDTO districtDTO) {
        if(districtRepository.findByName(districtDTO.getName()).isPresent()){
            throw new DistrictWNameAlreadyExistsException(districtDTO.getName());
        }
        districtRepository.add(Parse.parseDistrictDTOToDistrict(districtDTO));
        return districtDTO;
    }

    @Override
    public void removeDistrictByName(String name) {
        if(!districtRepository.findByName(name).isPresent()){
            throw new NoSuchDistrictNameException(name);
        }
        districtRepository.deleteByName(name);
    }
}
