package dom.clase32.repository;

import dom.clase32.dto.ErrorResponseDTO;
import dom.clase32.model.CharacterSW;

import java.util.List;

public interface ICharacterRepository {

    public List<CharacterSW> getCharacters();
    public CharacterSW findByName(String name) throws ErrorResponseDTO;
    public List<CharacterSW> findBySimilarName(String name) throws ErrorResponseDTO;
}
