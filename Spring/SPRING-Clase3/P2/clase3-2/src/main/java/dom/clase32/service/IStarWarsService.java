package dom.clase32.service;

import dom.clase32.dto.CharacterSWDTO;
import dom.clase32.model.CharacterSW;

import java.util.List;

public interface IStarWarsService {
        public List<CharacterSWDTO> returnCharacters(String name);
}
