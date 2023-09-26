package upc.edu.pe.softroute.trackingstatus.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.pe.softroute.trackingstatus.domain.models.Package;
import upc.edu.pe.softroute.trackingstatus.domain.repository.PackageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    public List<Package> findAllPackages() {
        return packageRepository.findAll();
    }

    public Optional<Package> findPackageByTrackingNumber(Integer trackingNumber) {
        return packageRepository.findByTrackingNumber(trackingNumber);
    }

    public Package savePackage(Package packageObj) {
        return packageRepository.save(packageObj);
    }

    public Package updatePackage(Integer trackingNumber, Package packageObj) {
        if (packageRepository.existsByTrackingNumber(trackingNumber)) {
            // packageObj.setTrackingNumber(trackingNumber);
            return packageRepository.save(packageObj);
        } else {
            // Handle the case where the package does not exist
            return null;
        }
    }

    public void deletePackage(Integer trackingNumber) {
        Optional<Package> packageOptional = packageRepository.findByTrackingNumber(trackingNumber);
        packageOptional.ifPresent(packageRepository::delete);
    }
}
