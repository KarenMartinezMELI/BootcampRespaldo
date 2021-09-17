package dom.clase32.repository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dom.clase32.dto.ErrorResponseDTO;
import dom.clase32.model.CharacterSW;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Repository
public class CharacterRepositoryImpl implements ICharacterRepository {

    private List<CharacterSW> characters;

    public CharacterRepositoryImpl(){
        characters=loadFile("starwars.json");
        //characters=loadFromJson("src/main/resources/starwars.json");
    }

    public List<CharacterSW> getCharacters(){
        return characters;
    }

    public CharacterSW findByName(String name) throws ErrorResponseDTO {

        return getCharacters().stream()
                .filter( chara-> chara.getName().equals(name))
                .findFirst().orElseThrow( () -> new ErrorResponseDTO("El personaje :"+name+":"+" no existe") );
    }

    public List<CharacterSW> findBySimilarName(String name) throws ErrorResponseDTO {

        return getCharacters().stream()
                .filter( chara-> chara.getName().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }
    private List<CharacterSW> loadFromJson(String fileName) {
        //Para esta sol. hay que modificar el archivo para que tenga un {data:[elementos] }
        List<CharacterSW> response = new ArrayList<>();
        try {
            String     text = Files.readString(Paths.get(fileName));
            JSONObject obj  = new JSONObject(text);
            JSONArray data = obj.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                response.add(
                        new CharacterSW(
                                data.getJSONObject(i).getString("name"),
                                data.getJSONObject(i).getString("height"),
                                data.getJSONObject(i).getString("mass"),
                                data.getJSONObject(i).getString("hair_color"),
                                data.getJSONObject(i).getString("eye_color"),
                                data.getJSONObject(i).getString("birth_year"),
                                data.getJSONObject(i).getString("gender"),
                                data.getJSONObject(i).getString("homeworld"),
                                data.getJSONObject(i).getString("species"),
                                data.getJSONObject(i).getString("skin_color")
                        )
                );
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

    private List<CharacterSW> loadFile(String fileName)
    {
        List<CharacterSW> ret = null;

        File file = null;

        try {
            file = ResourceUtils.getFile("classpath:"+fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        var objectMapper = new ObjectMapper();

        try {
            ret = objectMapper.readValue(file, new TypeReference<List<CharacterSW>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }


}
