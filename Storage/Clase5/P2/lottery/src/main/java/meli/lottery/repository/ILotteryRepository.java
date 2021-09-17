package meli.lottery.repository;

import meli.lottery.model.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILotteryRepository extends JpaRepository<Lottery, Long> {
}
