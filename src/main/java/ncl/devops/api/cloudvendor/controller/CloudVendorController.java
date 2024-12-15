package ncl.devops.api.cloudvendor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ncl.devops.api.cloudvendor.model.CloudVendor;
import ncl.devops.api.cloudvendor.response.ResponseHandler;
import ncl.devops.api.cloudvendor.service.CloudVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloudVendor")
@Tag(name = "Cloud Vendor", description = "Cloud Vendor management APIs")
public class CloudVendorController {

    CloudVendorService cloudVendorService;

    public CloudVendorController() {
    }

    @Autowired
    public CloudVendorController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }

    //Default Response
    @GetMapping("{vendorId}")
    public CloudVendor getCloudVendorDetails(@PathVariable("vendorId") String vendorId){
        return cloudVendorService.getCloudVendor(vendorId);
    }

//    //Custom Response
//    @Operation(summary = "Retrieve a Vendor by Id", description = "Get a Vendor object by specifying its id.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = CloudVendor.class), mediaType = "application/json") }),
//            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
//            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
//    @GetMapping("{vendorId}")
//    public ResponseEntity<Object> getCloudVendorDetails(@PathVariable("vendorId") String vendorId){
//        return ResponseHandler.responseBuilder("Requested Vendor Details are given here",
//                HttpStatus.OK, cloudVendorService.getCloudVendor(vendorId));
//    }

    @Operation(summary = "Retrieve a All Vendor", description = "Get a All Vendor object.")
    @GetMapping()
    public List<CloudVendor> getAllCloudVendorDetails(){
        return cloudVendorService.getAllCloudVendors();
    }

    @Operation(summary = "Create Vendor", description = "Create Vendor object.")
    @PostMapping
    public String createCloudVendorDetails(@RequestBody CloudVendor cloudVendor){
        cloudVendorService.createCloudVendor(cloudVendor);
        return "Cloud Vendor Created Successfully";
    }

    @Operation(summary = "Update Cloud Vendor", description = "Update Vendor object.")
    @PutMapping
    public String updateCloudVendorDetails(@RequestBody CloudVendor cloudVendor){
        cloudVendorService.updateCloudVendor(cloudVendor);
        return "Cloud Vendor Updated Successfully";
    }

    @Operation(summary = "Delete Cloud Vendor", description = "Delete Vendor object.")
    @DeleteMapping("{vendorId}")
    public String deleteCloudVendorDetails(@PathVariable("vendorId") String vendorId){
        cloudVendorService.deleteCloudVendor(vendorId);
        return "Cloud Vendor Deleted Successfully";
    }

    @Operation(summary = "Hello", description = "Hello World Message")
    @GetMapping("/hello")
    public String hello() {
        return  "Hello World";
    }
}
