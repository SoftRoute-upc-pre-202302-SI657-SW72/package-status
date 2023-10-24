package upc.edu.pe.softroute.trackingstatus.controllers;

import upc.edu.pe.softroute.trackingstatus.entity.PackageEntity;
import upc.edu.pe.softroute.trackingstatus.service.PackageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/packages")
@CrossOrigin(origins = "*")
@Api(value = "Package Microservice", description = "This API has a CRUD for Packages")
public class PackageController {

    @Autowired
    PackageService packageService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save a new Package", notes = "This method save a new Package")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Package created successfully"),
            @ApiResponse(code = 404, message = "Package not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<PackageEntity> savePackage(@Valid @RequestBody PackageEntity packageEntity) {
        try {
            PackageEntity packageNew = packageService.save(packageEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(packageNew);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all Packages", notes = "This method get all Packages")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Packages found"),
            @ApiResponse(code = 404, message = "Packages not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<PackageEntity>> getAll() {
        List<PackageEntity> packages = packageService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(packages);
    }

    @GetMapping(value = "/{trackingNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Package by Tracking Number", notes = "This method get Package by Tracking Number")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Package found"),
            @ApiResponse(code = 404, message = "Package not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<PackageEntity> getPackageByTrackingNumber(@PathVariable("trackingNumber") Integer trackingNumber) {
        try {
            PackageEntity packageEntity = packageService.getPackageByTrackingNumber(trackingNumber);
            if (packageEntity == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.status(HttpStatus.OK).body(packageEntity);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    // Otros métodos PUT, DELETE, etc. pueden ser agregados aquí siguiendo la misma estructura.

}
