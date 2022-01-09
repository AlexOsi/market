package com.osintsev.market.database.beat;

import org.springframework.data.jpa.repository.JpaRepository;

interface BeatRepository extends JpaRepository<BeatEntity, Long> {
}
