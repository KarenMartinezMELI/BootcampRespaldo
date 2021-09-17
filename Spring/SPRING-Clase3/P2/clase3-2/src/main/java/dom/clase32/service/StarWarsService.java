package dom.clase32.service;


import dom.clase32.dto.CharacterSWDTO;
import dom.clase32.dto.ErrorResponseDTO;
import dom.clase32.model.CharacterSW;
import dom.clase32.repository.ICharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class StarWarsService implements IStarWarsService{

    ICharacterRepository characterRepository;

    StarWarsService(ICharacterRepository characterRepository){
        this.characterRepository=characterRepository;
    }
    public List<CharacterSWDTO> returnCharacters(String name){
        List<CharacterSWDTO> characters = new ArrayList<>();
        CharacterSWDTO ch;
        if(name.strip()!="") {
            for (CharacterSW c : getCharacterRepository().findBySimilarName(name)) {
                ch = convertToDTO(c);
                characters.add(ch);
            }
        }else{
            throw new ErrorResponseDTO("Debe ingresar alguna palabra");
        }
        return characters;
    }

    private CharacterSWDTO convertToDTO(CharacterSW c){
        CharacterSWDTO ch=new CharacterSWDTO();
        ch.setBirthYear(c.getBirthYear());
        ch.setEyeColor(c.getEyeColor());
        ch.setGender(c.getGender());
        ch.setHeight(c.getHeight());
        ch.setHomeworld(c.getHomeworld());
        ch.setHairColor(c.getHairColor());
        ch.setSpecies(c.getSpecies());
        ch.setMass(c.getMass());
        ch.setName(c.getName());
        ch.setSkinColor(c.getSkinColor());
        return ch;
    }

    public ICharacterRepository getCharacterRepository() {
        return characterRepository;
    }
}
