package upc.edu.pe.softroute.trackingstatus.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.pe.softroute.trackingstatus.domain.models.Package;;;

public interface PackageRepository extends JpaRepository<Package, String> {
    Optional<Package> findByTrackingNumber(Integer trackingNumber);
    boolean existsByTrackingNumber(Integer trackingNumber);


}
