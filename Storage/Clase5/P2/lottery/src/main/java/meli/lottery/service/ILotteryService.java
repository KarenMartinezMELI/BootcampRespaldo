package meli.lottery.service;



import meli.lottery.dto.lottery.LotteryRequestDTO;
import meli.lottery.dto.lottery.LotteryResponseDTO;

import java.util.List;

public interface ILotteryService {

    List<LotteryResponseDTO> getAllLotteries();

    LotteryResponseDTO createLottery(LotteryRequestDTO lotteryRequestDTO);

    LotteryResponseDTO getLotteryById(Long lotteryId);

    void deleteLottery(Long genreId);
    void deleteAllLotteries();
}
