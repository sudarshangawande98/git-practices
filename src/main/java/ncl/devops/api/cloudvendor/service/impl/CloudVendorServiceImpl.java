package ncl.devops.api.cloudvendor.service.impl;

import ncl.devops.api.cloudvendor.exception.CloudVendorNotFoundException;
import ncl.devops.api.cloudvendor.model.CloudVendor;
import ncl.devops.api.cloudvendor.repository.CloudVendorRepository;
import ncl.devops.api.cloudvendor.service.CloudVendorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CloudVendorServiceImpl implements CloudVendorService {

    CloudVendorRepository cloudVendorRepository;

    public CloudVendorServiceImpl(CloudVendorRepository cloudVendorRepository) {
        this.cloudVendorRepository = cloudVendorRepository;
    }

    @Override
    public String createCloudVendor(CloudVendor cloudVendor) {
        //Bu
        cloudVendorRepository.save(cloudVendor);
        return "Cloud Vendor Save success";
    }

    @Override
    public String updateCloudVendor(CloudVendor cloudVendor) {
        cloudVendorRepository.save(cloudVendor);
        return "Cloud Vendor Data Update Success";
    }

    @Override
    public String deleteCloudVendor(String cloudVendorId) {
        cloudVendorRepository.deleteById(cloudVendorId);
        return "Cloud Vendor Delete Success";
    }

    //Custom Exception Handling
    @Override
    public CloudVendor getCloudVendor(String cloudVendorId) {
        if(cloudVendorRepository.findById(cloudVendorId).isEmpty())
            throw new CloudVendorNotFoundException("Requested Cloud Vendor Not Found");
        return cloudVendorRepository.findById(cloudVendorId).get();
    }

    @Override
    public List<CloudVendor> getAllCloudVendors() {
        return cloudVendorRepository.findAll();
    }

    @Override
    public String hello() {
        return "Hello";
    }
}
