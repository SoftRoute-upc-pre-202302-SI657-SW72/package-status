package upc.edu.pe.softroute.trackingstatus.service;

import upc.edu.pe.softroute.trackingstatus.entity.PackageEntity;
import upc.edu.pe.softroute.trackingstatus.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    public List<PackageEntity> getAll() {
        return packageRepository.findAll();
    }

    public PackageEntity getPackageByTrackingNumber(Integer trackingNumber) {
        return packageRepository.findByTrackingNumber(trackingNumber).orElse(null);
    }
    public PackageEntity save(PackageEntity packageEntity) {
        return packageRepository.save(packageEntity);
    }

    // Suponiendo que la entidad PackageEntity tiene un método setTrackingNumber
    public PackageEntity updatePackage(Integer trackingNumber, PackageEntity packageEntity) {
        if (packageRepository.existsByTrackingNumber(trackingNumber)) {
            packageEntity.setTrackingNumber(trackingNumber);
            return packageRepository.save(packageEntity);
        } else {
            // Handle the case where the package does not exist
            return null;
        }
    }

    public void delete(Integer trackingNumber) {
        packageRepository.findByTrackingNumber(trackingNumber).ifPresent(packageRepository::delete);
    }
}
