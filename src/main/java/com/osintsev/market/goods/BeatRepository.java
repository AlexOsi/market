package com.osintsev.market.goods;

import org.springframework.data.jpa.repository.JpaRepository;

interface BeatRepository extends JpaRepository<BeatEntity, Long> {
    boolean existsByName(String name);
}
