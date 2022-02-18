package com.osintsev.market.database.beat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BeatRepository extends JpaRepository<BeatEntity, Long> {
}
