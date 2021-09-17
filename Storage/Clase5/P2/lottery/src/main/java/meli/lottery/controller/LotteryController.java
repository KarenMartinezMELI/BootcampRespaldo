package meli.lottery.controller;

import meli.lottery.dto.lottery.LotteryRequestDTO;
import meli.lottery.dto.lottery.LotteryResponseDTO;
import meli.lottery.service.ILotteryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/lotteries")
public class LotteryController {

    ILotteryService lotteryService;

    LotteryController(ILotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("")
    public List<LotteryResponseDTO> getAllLotterys() {
        return lotteryService.getAllLotteries();
    }

    @PostMapping()
    public LotteryResponseDTO createLottery(@Valid @RequestBody LotteryRequestDTO lotteryRequestDTO) {
        return lotteryService.createLottery(lotteryRequestDTO);
    }

    @GetMapping("/{id}")
    public LotteryResponseDTO getLotteryById(@PathVariable(value = "id") Long lotteryId) {
        return lotteryService.getLotteryById(lotteryId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLottery(@PathVariable(value = "id") Long noteId) {
        lotteryService.deleteLottery(noteId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllLotteries() {
        lotteryService.deleteAllLotteries();
        return ResponseEntity.ok().build();
    }
}
