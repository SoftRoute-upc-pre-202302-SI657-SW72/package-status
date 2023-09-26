package upc.edu.pe.softroute.trackingstatus.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.softroute.trackingstatus.domain.models.Package;
import upc.edu.pe.softroute.trackingstatus.domain.services.PackageService;
import upc.edu.pe.softroute.trackingstatus.dto.trackingDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/packages")
@CrossOrigin(origins = "*")
@Api(value = "Web Service RESTFul of Packages", tags = "Packages")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @GetMapping
    @ApiOperation(value = "List all Packages", notes = "Method to list all Packages")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Packages found"),
            @ApiResponse(code = 404, message = "Packages Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<List<trackingDTO>> findAllPackages() {
        try {
            List<Package> packages = packageService.findAllPackages();
            if (packages.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            List<trackingDTO> trackingDTOList = packages.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(trackingDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{trackingNumber}")
    @ApiOperation(value = "Find Package by Tracking Number", notes = "Method to find a Package by its Tracking Number")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Package found"),
            @ApiResponse(code = 404, message = "Package Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<trackingDTO> findPackageByTrackingNumber(@PathVariable Integer trackingNumber) {
        try {
            Optional<Package> packageOptional = packageService.findPackageByTrackingNumber(trackingNumber);
            if (!packageOptional.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            trackingDTO trackingDTO = convertToDTO(packageOptional.get());
            return new ResponseEntity<>(trackingDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @ApiOperation(value = "Create Package", notes = "Method to create a new Package")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Package created successfully"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<trackingDTO> createPackage(@RequestBody trackingDTO trackingDTO) {
        try {
            Package packageObj = convertToEntity(trackingDTO);
            Package createdPackage = packageService.savePackage(packageObj);
            return new ResponseEntity<>(convertToDTO(createdPackage), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos PUT, DELETE, etc. pueden ser agregados aquí siguiendo la misma estructura.

    private trackingDTO convertToDTO(Package packageObj) {
        trackingDTO dto = new trackingDTO(null, null);
        dto.setTrackingNumber(packageObj.getTrackingNumber());
        dto.setStatus(packageObj.getStatus());
        return dto;
    }

    private Package convertToEntity(trackingDTO dto) {
        Package packageObj = new Package();
        packageObj.setTrackingNumber(dto.getTrackingNumber());
        packageObj.setStatus(dto.getStatus());
        return packageObj;
    }
}
