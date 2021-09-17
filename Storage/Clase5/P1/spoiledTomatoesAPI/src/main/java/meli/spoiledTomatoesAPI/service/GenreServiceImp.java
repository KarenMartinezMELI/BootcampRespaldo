package meli.spoiledTomatoesAPI.service;

import meli.spoiledTomatoesAPI.dto.genre.GenreRequestDTO;
import meli.spoiledTomatoesAPI.dto.genre.GenreResponseDTO;
import meli.spoiledTomatoesAPI.dto.miniserie.MiniSerieRequestDTO;
import meli.spoiledTomatoesAPI.dto.miniserie.MiniSerieResponseDTO;
import meli.spoiledTomatoesAPI.exception.ResourceNotFoundException;
import meli.spoiledTomatoesAPI.model.Genre;
import meli.spoiledTomatoesAPI.model.MiniSerie;
import meli.spoiledTomatoesAPI.repository.IGenreRepository;
import meli.spoiledTomatoesAPI.repository.IMiniSerieRepository;
import meli.spoiledTomatoesAPI.utils.ListMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImp implements IGenreService{


    IGenreRepository genreRepository;
    ModelMapper modelMapper;
    ListMapper listMapper;


    GenreServiceImp(IGenreRepository genreRepository,
                    ModelMapper modelMapper,
                    ListMapper listMapper) {
        this.genreRepository = genreRepository;
        this.listMapper = listMapper;

        Converter<Long, Genre> genreIdToGenreConverter = new AbstractConverter<Long, Genre>() {
            @Override
            protected Genre convert(Long genreId) throws ResourceNotFoundException {
                return genreRepository.findById(genreId)
                        .orElseThrow(() -> new ResourceNotFoundException("Genre", "id", genreId));
            }
        };

        this.modelMapper = modelMapper;
    }

    @Override
    public List<GenreResponseDTO> getAllGenres() {
        List<Genre> genres = genreRepository.findAll().stream().filter(x->x.getActive()>0).collect(Collectors.toList());
        return listMapper.mapList(genres, GenreResponseDTO.class);
    }

    @Override
    public GenreResponseDTO createGenre(GenreRequestDTO genreRequestDTO) {
        // Create new note
        Genre genre = modelMapper.map(genreRequestDTO, Genre.class);
        genre.setActive(1);
        Genre genreReq = genreRepository.save(genre);
        GenreResponseDTO resp =  modelMapper.map(genreReq, GenreResponseDTO.class);
        return resp;
    }

    @Override
    public GenreResponseDTO getGenreById(Long genreId) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("Genre", "id", genreId));
        verifyGenreActive(genre);
        return modelMapper.map(genre, GenreResponseDTO.class);
    }

    @Override
    public GenreResponseDTO updateGenre(Long genreId,
                                        GenreRequestDTO genreDTO) {

        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("Genre", "id", genreId));
        verifyGenreActive(genre);


        genre.setName(genreDTO.getName());
        genre.setRanking(genreDTO.getRanking());


        Genre updatedGenre = genreRepository.save(genre);
        return modelMapper.map(updatedGenre, GenreResponseDTO.class);
    }
    private void verifyGenreActive(Genre genre){
        if(genre.getActive()<1){
            throw new ResourceNotFoundException("Genre", "id", genre.getId());
        }
    }

    @Override
    public void deleteGenre(Long genreId) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("Genre", "id", genreId));
        genre.setActive(0);
        genreRepository.save(genre);
    }
}
