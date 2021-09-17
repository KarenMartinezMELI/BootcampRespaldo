package meli.lottery.service;

import meli.lottery.dto.lottery.LotteryRequestDTO;
import meli.lottery.dto.lottery.LotteryResponseDTO;
import meli.lottery.exception.NotEnoughStudentsException;
import meli.lottery.exception.ResourceNotFoundException;
import meli.lottery.model.Lottery;
import meli.lottery.model.Student;
import meli.lottery.repository.ILotteryRepository;
import meli.lottery.repository.IStudentRepository;
import meli.lottery.utils.ListMapper;
import org.modelmapper.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class LotteryServiceImp implements ILotteryService {

    ILotteryRepository lotteryRepository;
    IStudentRepository studentRepository;
    ModelMapper modelMapper;
    ListMapper listMapper;


    LotteryServiceImp(ILotteryRepository lotteryRepository,
                      IStudentRepository studentRepository,
                      ModelMapper modelMapper,
                      ListMapper listMapper) {
        this.lotteryRepository = lotteryRepository;
        this.studentRepository = studentRepository;
        this.listMapper = listMapper;

        Converter<Long, Student> studentIdToStudentConverter = new AbstractConverter<Long, Student>() {
            @Override
            protected Student convert(Long studentId) throws ResourceNotFoundException {
                return studentRepository.findById(studentId)
                        .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
            }
        };

        this.modelMapper = modelMapper;
    }

    @Override
    public List<LotteryResponseDTO> getAllLotteries() {
        List<Lottery> students = lotteryRepository.findAll();
        return listMapper.mapList(students, LotteryResponseDTO.class);
    }

    @Override
    public LotteryResponseDTO createLottery(LotteryRequestDTO lotteryRequestDTO) {
        int countS= lotteryRepository.findAll().stream().mapToInt(l -> (l.getStudents().size())).sum();
        if(studentRepository.count()==0||countS>=studentRepository.count()){
            throw new NotEnoughStudentsException("Lottery");
        }

        Lottery lottery = modelMapper.map(lotteryRequestDTO, Lottery.class);

        List<Student> students = studentRepository.findAllActive();
        Integer val=0;
        Random r = new Random();
        Student student=null;
        lottery.setStudents(new ArrayList<>());
        for(int max= 1;max<=lotteryRequestDTO.getMax();max++){
            val= r.nextInt(students.size());
            student = students.get(val);
            Student finalStudent = student;
            if(!lotteryRepository.findAll().stream().filter(x->x.getStudents().stream().filter(s->s.getId().equals(finalStudent.getId())).findFirst().isPresent()).collect(Collectors.toList()).isEmpty()
            ||!lottery.getStudents().stream().filter(s->s.getId().equals(finalStudent.getId())).collect(Collectors.toList()).isEmpty()){
             max--;
          }else{
                lottery.getStudents().add(student);
            }
        }

        Lottery lotteryReq = lotteryRepository.save(lottery);
        LotteryResponseDTO resp =  modelMapper.map(lotteryReq, LotteryResponseDTO.class);
        return resp;
    }

    @Override
    public LotteryResponseDTO getLotteryById(Long lotteryId) {
        Lottery lottery = lotteryRepository.findById(lotteryId)
                .orElseThrow(() -> new ResourceNotFoundException("Lottery", "id", lotteryId));
        return modelMapper.map(lottery, LotteryResponseDTO.class);
    }


    @Override
    public void deleteLottery(Long lotteryId) {
        Lottery lottery = lotteryRepository.findById(lotteryId)
                .orElseThrow(() -> new ResourceNotFoundException("lottery", "id", lotteryId));

        lotteryRepository.delete(lottery);
    }

    @Override
    public void deleteAllLotteries() {
        lotteryRepository.deleteAll();
    }
}
