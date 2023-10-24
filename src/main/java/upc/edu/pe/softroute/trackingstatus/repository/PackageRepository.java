package upc.edu.pe.softroute.trackingstatus.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.pe.softroute.trackingstatus.entity.PackageEntity;

public interface PackageRepository extends JpaRepository<PackageEntity, Integer> {
    Optional<PackageEntity> findByTrackingNumber(Integer trackingNumber);
    boolean existsByTrackingNumber(Integer trackingNumber);
}
